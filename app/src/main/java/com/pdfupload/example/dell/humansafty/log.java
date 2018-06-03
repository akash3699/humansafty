package com.pdfupload.example.dell.humansafty;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class log extends AppCompatActivity {
    EditText login_id, login_pwd;
    Button login_button;
    TextView linktoregister;
    ProgressDialog progressDialog;
    String username, password;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);


        sp = getSharedPreferences("login", MODE_PRIVATE);


        login_id = (EditText) findViewById(R.id.login_id);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        login_button = (Button) findViewById(R.id.login_button);
        linktoregister = (TextView) findViewById(R.id.linktoregister);

        if (sp.contains("username") && sp.contains("password")) {
            startActivity(new Intent(log.this, NavigationDrawer.class));
            finish();   //finish current activity
        }

        linktoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(log.this, reg.class);
                startActivity(i);

            }
        });


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginBlock();


            }
        });
    }


    public void LoginBlock() {
        progressDialog = new ProgressDialog(log.this);
        progressDialog.setMessage("Please Wait ...Loading....");
        progressDialog.show();

        // String targetURL_Login="http://hospital.myindiamade.com/MoneyTransfer/login.php?apicall=login";

        String targetURL_Login = SERVER_ADDRESS + "/login.php?apicall=login";

        StringRequest stringRequestLogInBtn = new StringRequest(Request.Method.POST,
                targetURL_Login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            progressDialog.dismiss();
                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("url_login", jsonObject.getString("error"));
                            Log.d("message", jsonObject.getString("message"));
                            String message = jsonObject.getString("message");
                            String id = jsonObject.getString("id");

                            username = jsonObject.getString("Username");
                            password = jsonObject.getString("Password");
                            if (jsonObject.getString("error").equals("false")) {


                                Toast.makeText(log.this, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), NavigationDrawer.class);

                                SharedPreferences.Editor e = sp.edit();
                                e.putString("username", username);
                                e.putString("password", password);
                                e.putString("stuid", id);
                                e.commit();

                                startActivity(intent);
                            } else {
                                Toast.makeText(log.this, "" + message, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(), "Network Problem!", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Username", login_id.getText().toString());
                params.put("Password", login_pwd.getText().toString());


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequestLogInBtn);
    }


    @Override
    public void onBackPressed() {

        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(log.this, R.style.MyDialogTheme);
        alertDialogBuilder.setTitle("Are you sure want to Exit?");
        alertDialogBuilder.setMessage("Click yes to exit!").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


}