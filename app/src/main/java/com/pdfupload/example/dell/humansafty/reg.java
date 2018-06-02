package com.pdfupload.example.dell.humansafty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.pdfupload.example.dell.humansafty.constant.Constant.SERVER_ADDRESS;

public class reg extends AppCompatActivity {
    EditText name, email, mobile, pass, ccpass;
    TextView linktologin, token1;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        token1 = (TextView) findViewById(R.id.token);
        token1.setText(SharedPreference.getInstance(this).getDeviceToken());

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email1);
        mobile = (EditText) findViewById(R.id.mobile);
        pass = (EditText) findViewById(R.id.pass);
        ccpass = (EditText) findViewById(R.id.ccpass);

        linktologin = (TextView) findViewById(R.id.linktologin);
        linktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(reg.this, log.class);
                startActivity(i);
            }
        });
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecords();

            }
        });
    }

    private void saveRecords2() {


        String targeturl = SERVER_ADDRESS + "HumanSafty/insertlocation.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, targeturl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            JSONObject jsonObject = new JSONObject(response);

                            Log.d("url_app product", jsonObject.getString("error"));
                            Log.d("message", jsonObject.getString("message"));
                            String message = jsonObject.getString("message");

                            if (message.equals("Successfully..")) {
                                Toast.makeText(reg.this, message, Toast.LENGTH_SHORT).show();
                            } else if (message.equals("Something went wrong")) {
                                Toast.makeText(reg.this, message, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Latitude", "18.5579394");
                params.put("Longitude", "73.9224244");

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    private void saveRecords() {

        if (name.getText().toString().equals("")) {
            name.setError("Enter User Name");
        } else if (email.getText().toString().equals("")) {
            email.setError("Enter  Email Address");
        } else if (mobile.getText().toString().equals("")) {
            mobile.setError("Enter Mobile No");
        } else if (mobile.getText().length() != 10) {
            mobile.setError("Enter Mobile No");
        } else if (pass.getText().toString().equals("")) {
            pass.setError("Enter Password");
        } else if (ccpass.getText().toString().equals("")) {
            ccpass.setError("Enter Re-Password");
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(reg.this);
            progressDialog.setMessage("loading..Please Wait...");
            progressDialog.show();
            //String targeturl="http://hospital.myindiamade.com/MoneyTransfer/login.php?apicall=signup";
            String targeturl = SERVER_ADDRESS + "HumanSafty/login.php?apicall=signup";
            final String
                    token = SharedPreference.getInstance(this).getDeviceToken();
            Log.d("targeturl_employee", targeturl);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, targeturl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                progressDialog.dismiss();
                                JSONObject jsonObject = new JSONObject(response);

                                Log.d("url_app product", jsonObject.getString("error"));
                                Log.d("message", jsonObject.getString("message"));
                                String message = jsonObject.getString("message");

                                if (message.equals("User registered successfully")) {
                                    Toast.makeText(reg.this, message, Toast.LENGTH_SHORT).show();
                                    saveRecords2();
                                } else if (message.equals("User already registered")) {
                                    Toast.makeText(reg.this, message, Toast.LENGTH_SHORT).show();
                                    //  saveRecords2();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Username", name.getText().toString());
                    params.put("Email", email.getText().toString());
                    params.put("Mobile_No", mobile.getText().toString());
                    params.put("token", token);
                    params.put("Password", pass.getText().toString());
                    params.put("Re_enter_Password", ccpass.getText().toString());

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), log.class);
        startActivity(intent);
        super.onBackPressed();


    }
}


   /* private Button register;
    private EditText editTextEmail;
    private ProgressDialog progressDialog;
    TextView tokenshow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        register = (Button) findViewById(R.id.register);
        tokenshow = (TextView) findViewById(R.id.tokenshow);
    }

    public void sendToken(View view) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering Device...");
        progressDialog.show();

        final String token = SharedPreference.getInstance(this).getDeviceToken();
        final String email = editTextEmail.getText().toString();
        tokenshow.setText(token);

        if (token == null) {
            progressDialog.dismiss();
            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(reg.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(reg.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("token", token);
                return params;
            }
        };
        FcmVolley.getInstance(this).addToRequestQueue(stringRequest);
    }
}
*/