package br.com.tcc.tcc;

import android.app.Activity;
import android.widget.EditText;

import br.com.tcc.tcc.modelo.Pet;

/**
 * Created by cesardias on 26/03/18.
 */

public class FormularioHelper {

    private EditText campoAnimal;
    private EditText campoEndereco;
    private EditText campoTelefone;

    public FormularioHelper(FormularioActivity activity) {
        this.campoAnimal = (EditText) activity.findViewById(R.id.formulario_animal);
        this.campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        this.campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
    }

    public Pet pegaPet() {
        Pet pet = new Pet();
        pet.setAnimal(campoAnimal.getText().toString());
        pet.setEndereco(campoEndereco.getText().toString());
        pet.setTelefone(campoTelefone.getText().toString());

        return pet;
    }
}