package br.com.tcc.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

    Button botaoSalvar = (Button) findViewById(R.id.formulario_salvar);
    botaoSalvar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(FormularioActivity.this, "Pet Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
            Intent vaiPralista = new Intent(FormularioActivity.this,  ListaPetsActivity.class);
            startActivity(vaiPralista); // Aqui ele vai jogar pra lista 
        }
    });
    }

}
