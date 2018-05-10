package br.com.tcc.tcc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.tcc.tcc.dao.PetDAO;
import br.com.tcc.tcc.modelo.Pet;

public class ListaPetsActivity extends AppCompatActivity {

    private ListView listaPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pets);

        listaPets = (ListView) findViewById(R.id.lista_pets);

        listaPets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Pet pet = (Pet) listaPets.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(ListaPetsActivity.this, FormularioActivity.class); //startActivity(intentVaiProFormulario); (Bug Fixed - Formulario sendo aberto)
                intentVaiProFormulario.putExtra("pet", pet);
                startActivity(intentVaiProFormulario);
            }
        });

        Button novoPet = (Button) findViewById(R.id.novo_pet);
        novoPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentvaiProFormulario = new Intent(ListaPetsActivity.this, FormularioActivity.class);
                startActivity(intentvaiProFormulario);
            }
        });

        registerForContextMenu(listaPets);


    }

    private void carregaLista() {
        PetDAO dao = new PetDAO(this);
        List<Pet> pets = dao.buscaPets();
        dao.close();


        ArrayAdapter<Pet> adapter = new ArrayAdapter<Pet>(this, R.layout.list_item, pets);
        listaPets.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Pet pet = (Pet) listaPets.getItemAtPosition(info.position);

        MenuItem itemSMS = menu.add("Enviar SMS pro Dono");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
        intentSMS.setData(Uri.parse("sms:" + pet.getTelefone()));
        itemSMS.setIntent(intentSMS);

        MenuItem itemMapa = menu.add("Visualizar no mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?q=" + pet.getEndereco()));
        itemMapa.setIntent(intentMapa);

        MenuItem itemLigar = menu.add("Ligar");
        itemLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (ActivityCompat.checkSelfPermission(ListaPetsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(ListaPetsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                } else {
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel:" + pet.getTelefone()));
                    startActivity(intentLigar);
                }
                return false;
            }
        });




       MenuItem deletar =  menu.add("Deletar");
       deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(MenuItem item) {


               PetDAO dao = new PetDAO(ListaPetsActivity.this);
               dao.deleta(pet);
               dao.close();

               carregaLista();
               //Toast.makeText(ListaPetsActivity.this, "Deletar Pet " +pet.getAnimal(), Toast.LENGTH_SHORT).show();
               return false;
           }
       });
    }
}
