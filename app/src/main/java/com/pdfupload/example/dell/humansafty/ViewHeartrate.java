package com.pdfupload.example.dell.humansafty;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewHeartrate extends AppCompatActivity {
ListView heartbeat;
    String numberurl="http://android.dhamalexim.com/HumanSafty/fetchheart.php";
    ArrayList<String> CountryName;
    ArrayAdapter<String> madapter;
    JSONArray message;
    SharedPreferences sp;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);
        fetch();
        sp=getApplicationContext().getSharedPreferences("login",MODE_PRIVATE);
        id =  sp.getString("stuid",null);
        CountryName=new ArrayList<String>();
        heartbeat = (ListView) findViewById(R.id.heartbeat);



    }

    private void fetch() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, numberurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {

                            j=new JSONObject(response);
                            message = j.getJSONArray("message");
                            parse(message);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressDialog.dismiss();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("personid",id);
                return params;
            }
        } ;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    private void parse(JSONArray jsonArray) {

        for (int i = 0; i < jsonArray.length(); i++) {


            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                CountryName.add(jsonObject.getString("HeartRating"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        heartbeat.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, CountryName));
    }

}
