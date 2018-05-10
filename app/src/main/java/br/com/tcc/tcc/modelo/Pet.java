package br.com.tcc.tcc.modelo;

import java.io.Serializable;

/**
 * Created by cesardias on 26/03/18.
 */

public class Pet implements Serializable{

    private Long Id;
    private String animal;
    private String endereco;
    private String telefone;
    private String caminhoFoto;



    public Pet() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    @Override
    public String toString()  {
        return getId() + " - " + getAnimal();
    }
}
