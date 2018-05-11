package br.com.tcc.tcc.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.tcc.tcc.ListaPetsActivity;
import br.com.tcc.tcc.R;
import br.com.tcc.tcc.modelo.Pet;

/**
 * Created by cesardias on 11/05/18.
 */

public class PetAdapter extends BaseAdapter {
    private final List<Pet> pets;
    private final Context context;

    public PetAdapter(Context context, List<Pet> pets) {
        this.context = context;
        this.pets = pets;
    }

    @Override
    public int getCount() {
        return pets.size();
    }

    @Override
    public Object getItem(int position) {
        return pets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pets.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Pet pet = pets.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView campoNome = (TextView) view.findViewById(R.id.item_nome);
        campoNome.setText(pet.getAnimal());

        TextView campoTelefone = (TextView) view.findViewById(R.id.item_telefone);
        campoTelefone.setText(pet.getTelefone());

        ImageView campoFoto = (ImageView) view.findViewById(R.id.item_foto);
        String caminhoFoto = pet.getCaminhoFoto();
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);


        }
        return view;
    }
}