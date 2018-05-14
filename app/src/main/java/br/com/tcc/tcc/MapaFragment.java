package br.com.tcc.tcc;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.com.tcc.tcc.dao.PetDAO;
import br.com.tcc.tcc.modelo.Pet;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng posicaoDaEscola = pegaCoordenadaDoEndereco("Rua das Esmeraldas 649, Santo Andre");
        if (posicaoDaEscola != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoDaEscola, 17);
            googleMap.moveCamera(update);
        }

        PetDAO dao = new PetDAO(getContext());
        for (Pet pet : dao.buscaPets()) {
            LatLng coordenada = pegaCoordenadaDoEndereco(pet.getEndereco());
            if (coordenada != null) {
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenada);
                marcador.title(pet.getAnimal());
                marcador.snippet(pet.getTelefone());
                googleMap.addMarker(marcador);
            }
        }

        dao.close();
        new GPS(getContext(), googleMap);
    }

    private LatLng pegaCoordenadaDoEndereco(String endereco) {
        try {
            Geocoder geocoder = new Geocoder (getContext());
            List<Address> resultados =
                    geocoder.getFromLocationName(endereco, 1);
            if (!resultados.isEmpty()) {
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
