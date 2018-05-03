package br.com.tcc.tcc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
            String sql = "CREATE TABLE PET (ID INTEGER PRIMARY KEY, NOME TEXT NOT NULL, ENDERECO TEXT NOT NULL, TELEFONE TEXT NOT NULL);";
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
        ContentValues dados = new ContentValues();
        dados.put("nome", pet.getAnimal());
        dados.put("endereco",pet.getEndereco());
        dados.put("telefone", pet.getTelefone());
        db.insert("PET",null,dados);

    }

    public List<Pet> buscaPets() {
        String sql = "SELECT * FROM PET;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        List<Pet> pets = new ArrayList<Pet>();
        while (c.moveToNext()) {
            Pet pet = new Pet();
            pet.setId(c.getLong(c.getColumnIndex("id")));
            pet.setAnimal(c.getString(c.getColumnIndex("nome")));
            pet.setEndereco(c.getString(c.getColumnIndex("endereco")));
            pet.setTelefone(c.getString(c.getColumnIndex("telefone")));

            pets.add(pet);
        }
        c.close();
            return pets;
    }
}
