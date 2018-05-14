package br.com.tcc.tcc;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.LocaleList;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;


public class GPS implements GoogleApiClient.ConnectionCallbacks, LocationListener{

    private final GoogleApiClient client;
    private final GoogleMap map;

    public GPS(Context context, GoogleMap map) {
         client = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();

        client.connect();

        this.map = map;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest rq = new LocationRequest();
        rq.setSmallestDisplacement(50);
        rq.setInterval(1000);
        rq.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(client, rq, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng coord = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(coord);
        map.moveCamera(cameraUpdate);
    }
}
