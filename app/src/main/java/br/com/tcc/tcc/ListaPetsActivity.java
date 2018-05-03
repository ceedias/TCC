package br.com.tcc.tcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.tcc.tcc.dao.PetDAO;
import br.com.tcc.tcc.modelo.Pet;

public class ListaPetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pets);

        PetDAO dao = new PetDAO(this);
        List<Pet> pets = dao.buscaPets();
        dao.close();

        ListView listaPets = (ListView) findViewById(R.id.lista_pets);
        ArrayAdapter<Pet> adapter = new ArrayAdapter<Pet>(this, android.R.layout.simple_list_item_1, pets);
        listaPets.setAdapter(adapter);
        final Button novoPet = (Button) findViewById(R.id.novo_pet);
        novoPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentvaiProFormulario = new Intent(ListaPetsActivity.this, FormularioActivity.class);
                startActivity(intentvaiProFormulario);
            }
        });
    }
}
