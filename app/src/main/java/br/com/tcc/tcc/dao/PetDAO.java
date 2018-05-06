package br.com.tcc.tcc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.tcc.tcc.modelo.Pet;

/**
 * Created by cesardias on 03/05/18.
 */

public class PetDAO extends SQLiteOpenHelper{


    public PetDAO(Context context) {
        super(context, "TCC", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE PET (id INTEGER PRIMARY KEY, animal TEXT NOT NULL, endereco TEXT NOT NULL, telefone TEXT NOT NULL);";
            db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS PET;";
        db.execSQL(sql);
        onCreate(db);

    }

    public void insere(Pet pet) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoPet(pet);

        db.insert("PET",null,dados);

    }

    @NonNull
    private ContentValues pegaDadosDoPet(Pet pet) {
        ContentValues dados = new ContentValues();
        dados.put("animal", pet.getAnimal());
        dados.put("endereco",pet.getEndereco());
        dados.put("telefone", pet.getTelefone());
        return dados;
    }

    public List<Pet> buscaPets() {
        String sql = "SELECT * FROM PET;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        List<Pet> pets = new ArrayList<Pet>();
        while (c.moveToNext()) {
            Pet pet = new Pet();
            pet.setId(c.getLong(c.getColumnIndex("id")));
            pet.setAnimal(c.getString(c.getColumnIndex("animal")));
            pet.setEndereco(c.getString(c.getColumnIndex("endereco")));
            pet.setTelefone(c.getString(c.getColumnIndex("telefone")));

            pets.add(pet);
        }
        c.close();

            return pets;
    }

    public void deleta(Pet pet) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {pet.getId().toString()};
        db.delete("Pet", "id= ?", params);
    }

    public void altera(Pet pet) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoPet(pet);

        String[] params = {pet.getId().toString()};
        db.update("PET", dados, "id = ?", params);
    }
}
