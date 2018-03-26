package br.com.tcc.tcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaPetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pets);

        String[] pets= {"Cachorro", "Gato", "Papagaio", "Peixe"};
        ListView listaPets = (ListView) findViewById(R.id.lista_pets);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pets);
        listaPets.setAdapter(adapter);
    }
}
