package com.pdfupload.example.dell.humansafty;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pdfupload.example.dell.humansafty.LocationUtil.LocationHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

public class SearchPerson extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerDragListener,GoogleMap.OnMapLongClickListener{

    public GoogleMap mMap;

    EditText SearchPerson;
    Button b1;
    private Location mLastLocation;
    String currentLocation;



    LocationHelper locationHelper;
    private static final String TAG1 = "DashBoard";
    private GoogleApiClient googleApiClient;
    //send message
    double Latitude,Longitude;
    RequestQueue requestQueue;
    public static final String DISPLAY_URL ="http://android.dhamalexim.com/HumanSafty/fetchlocation.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_person);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.place_Search_fragment);
        mapFragment.getMapAsync(this);
        SearchPerson = (EditText) findViewById(R.id.editSearchperson);
        b1 = (Button)findViewById(R.id.button_searchperson);
        ButterKnife.bind(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  for(;;)
                {*/
                fetchlocation();
                moveOnMap();
               /* }*/
            }
        });

    }

    private void fetchlocation() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,DISPLAY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //  Log.d("data", "" + data);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray message = jsonObject.getJSONArray("message");
                    parseData(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SearchPerson.this, "Error Listerner", Toast.LENGTH_SHORT).show();
                    }
                })

        {
            protected Map<String,String> getParams()
            {

                Map<String,String> params=new HashMap<String, String>();

                params.put("id", SearchPerson.getText().toString());
                return params;
            }
        };

        // RequestQueue requestQueue = Volley.newRequestQueue(this);

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(SearchPerson.this);
            //  Build.logError("Setting a new request queue");
        }
        requestQueue.add(stringRequest);
    }

    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {

            JSONObject jsonObject = null;

            try {
                jsonObject = array.getJSONObject(i);
                Latitude =    Double.parseDouble(jsonObject.getString("Latitude"));
                Longitude =  Double.parseDouble(jsonObject.getString("Longitude"));
                moveOnMap();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveOnMap() {

        LatLng latLng = new LatLng(Latitude, Longitude);
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(true)
                .title("Marker in India"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMapLongClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng india = new LatLng(Latitude, Longitude);

        mMap.addMarker(new MarkerOptions().position(india)
                .title("Marker in India"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(india));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        //   mMap.setOnMarkerDragListener(this);
        // mMap.setOnMapLongClickListener(this);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        Latitude = marker.getPosition().latitude;
        Longitude = marker.getPosition().longitude;

        moveOnMap();
    }




    @Override
    public void onMapLongClick(LatLng latLng) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
    }
}