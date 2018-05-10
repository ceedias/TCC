package br.com.tcc.tcc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.EditText;
import android.widget.ImageView;

import br.com.tcc.tcc.modelo.Pet;

/**
 * Created by cesardias on 26/03/18. e
 */

public class FormularioHelper {

    private EditText campoAnimal;
    private EditText campoEndereco;
    private EditText campoTelefone;
    private ImageView foto;


    private Pet pet;

    public FormularioHelper(FormularioActivity activity) {
        this.campoAnimal = (EditText) activity.findViewById(R.id.formulario_animal);
        this.campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        this.campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        this.foto = (ImageView) activity.findViewById(R.id.formulario_foto);
        pet = new Pet();
    }

    public Pet pegaPet() {

        pet.setAnimal(campoAnimal.getText().toString());
        pet.setEndereco(campoEndereco.getText().toString());
        pet.setTelefone(campoTelefone.getText().toString());
        pet.setCaminhoFoto((String) foto.getTag());

        return pet;
    }

    public void preencheForm(Pet pet) {
        campoAnimal.setText(pet.getAnimal());
        campoEndereco.setText(pet.getEndereco());
        campoTelefone.setText(pet.getTelefone());
        this.pet = pet;

        if (pet.getCaminhoFoto() != null) {
            carregaFoto(pet.getCaminhoFoto());
        }
    }

    public void carregaFoto(String caminhoFoto) {

        Bitmap bm = BitmapFactory.decodeFile(caminhoFoto);
        bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
        foto.setImageBitmap(bm);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
        foto.setTag(caminhoFoto);

    }


}
