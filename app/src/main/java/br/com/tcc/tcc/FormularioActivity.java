package br.com.tcc.tcc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import br.com.tcc.tcc.dao.PetDAO;
import br.com.tcc.tcc.modelo.Pet;

public class FormularioActivity extends AppCompatActivity {

    public static final int CODIGO_CAMERA = 567;
    private FormularioHelper helper;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        final Intent intent = getIntent();
        Pet pet = (Pet) intent.getSerializableExtra("pet");
        if (pet != null){
            helper.preencheForm(pet);
        }

    Button botaoFoto = (Button) findViewById(R.id.formulario_botao_foto);
        botaoFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 caminhoFoto = getExternalFilesDir(null) + "/" +System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT,
                        FileProvider.getUriForFile(FormularioActivity.this,
                                BuildConfig.APPLICATION_ID + ".provider", arquivoFoto));

                startActivityForResult(intentCamera, CODIGO_CAMERA);

            }
        });

        }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_CAMERA) {
            if (resultCode == RESULT_OK) {
                helper.carregaFoto(caminhoFoto);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater =  getMenuInflater();
       inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:

                Pet pet = helper.pegaPet();
                PetDAO dao = new PetDAO(this);

                if(pet.getId() != null) {
                    dao.altera(pet);
                }else {

                    dao.insere(pet);
                }
                dao.close();

                Toast.makeText(FormularioActivity.this,"O seu PET: " +pet.getAnimal()+ " foi Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
