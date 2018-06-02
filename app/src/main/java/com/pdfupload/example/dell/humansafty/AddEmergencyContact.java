package com.pdfupload.example.dell.humansafty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddEmergencyContact extends AppCompatActivity {
EditText family1,family2,family3,friend1,friend2,friend3;
    Button b1,b2,b3,b4,b5,b6,insert;
    SharedPreferences sp;
    String id,fa1,fa2,fa3,fr1,fr2,fr3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emergency_contact);

        sp=getApplicationContext().getSharedPreferences("login",MODE_PRIVATE);
        id =  sp.getString("stuid",null);

        family1 = (EditText) findViewById(R.id.family1);
        family2 = (EditText) findViewById(R.id.family2);
        family3 = (EditText) findViewById(R.id.family3);
        friend1 = (EditText) findViewById(R.id.friend1);
        friend2 = (EditText) findViewById(R.id.friend2);
        friend3 = (EditText) findViewById(R.id.friend3);
        b1 = (Button) findViewById(R.id.update1);
        b2 = (Button) findViewById(R.id.update2);
        b3 = (Button) findViewById(R.id.update3);
        b4 = (Button) findViewById(R.id.update4);
        b5 = (Button) findViewById(R.id.update5);
        b6 = (Button) findViewById(R.id.update6);

        insert = (Button) findViewById(R.id.button_insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedata();
            }
        });
fetchdata();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update1();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update2();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update3();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update4();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update5();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update6();
            }
        });
    }

    private void update6() {
        final ProgressDialog progressDialog = new ProgressDialog(AddEmergencyContact.this);
        progressDialog.setMessage("loading..Please Wait...");
        progressDialog.show();
        String targeturl="http://android.dhamalexim.com/HumanSafty/updatefriend3.php";

        Log.d("targeturl_employee",targeturl);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, targeturl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            progressDialog.dismiss();
                            JSONObject jsonObject=new JSONObject(response);

                            Log.d("url_app product",jsonObject.getString("error"));
                            Log.d("message",jsonObject.getString("message"));
                            String message=jsonObject.getString("message");

                            if (message.equals("Update Successfully..."))
                            {
                                Toast.makeText(AddEmergencyContact.this, ""+message, Toast.LENGTH_SHORT).show();

                            }
                            else
                            if (message.equals("User already registered"))
                            {
                                Toast.makeText(AddEmergencyContact.this, "Please Update Contacts", Toast.LENGTH_SHORT).show();
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
                params.put("friend3",friend3.getText().toString());
                params.put("userid",id);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void update5() {
        final ProgressDialog progressDialog = new ProgressDialog(AddEmergencyContact.this);
        progressDialog.setMessage("loading..Please Wait...");
        progressDialog.show();
        //String targeturl="http://hospital.myindiamade.com/MoneyTransfer/login.php?apicall=signup";
        String targeturl="http://android.dhamalexim.com/HumanSafty/updatefriend2.php";

        Log.d("targeturl_employee",targeturl);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, targeturl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            progressDialog.dismiss();
                            JSONObject jsonObject=new JSONObject(response);

                            Log.d("url_app product",jsonObject.getString("error"));
                            Log.d("message",jsonObject.getString("message"));
                            String message=jsonObject.getString("message");

                            if (message.equals("Update Successfully..."))
                            {
                                Toast.makeText(AddEmergencyContact.this, ""+message, Toast.LENGTH_SHORT).show();

                            }
                            else
                            if (message.equals("User already registered"))
                            {
                                Toast.makeText(AddEmergencyContact.this, "Please Update Contacts", Toast.LENGTH_SHORT).show();
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
                params.put("friend2",friend2.getText().toString());
                params.put("userid",id);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void update4() {
        final ProgressDialog progressDialog = new ProgressDialog(AddEmergencyContact.this);
        progressDialog.setMessage("loading..Please Wait...");
        progressDialog.show();
        //String targeturl="http://hospital.myindiamade.com/MoneyTransfer/login.php?apicall=signup";
        String targeturl="http://android.dhamalexim.com/HumanSafty/updatefriend1.php";

        Log.d("targeturl_employee",targeturl);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, targeturl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            progressDialog.dismiss();
                            JSONObject jsonObject=new JSONObject(response);

                            Log.d("url_app product",jsonObject.getString("error"));
                            Log.d("message",jsonObject.getString("message"));
                            String message=jsonObject.getString("message");

                            if (message.equals("Update Successfully..."))
                            {
                                Toast.makeText(AddEmergencyContact.this, ""+message, Toast.LENGTH_SHORT).show();

                            }
                            else
                            if (message.equals("User already registered"))
                            {
                                Toast.makeText(AddEmergencyContact.this, "Please Update Contacts", Toast.LENGTH_SHORT).show();
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
                params.put("friend1",friend1.getText().toString());
                params.put("userid",id);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void update3() {
        final ProgressDialog progressDialog = new ProgressDialog(AddEmergencyContact.this);
        progressDialog.setMessage("loading..Please Wait...");
        progressDialog.show();
        //String targeturl="http://hospital.myindiamade.com/MoneyTransfer/login.php?apicall=signup";
        String targeturl="http://android.dhamalexim.com/HumanSafty/updatefamily3.php";

        Log.d("targeturl_employee",targeturl);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, targeturl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            progressDialog.dismiss();
                            JSONObject jsonObject=new JSONObject(response);

                            Log.d("url_app product",jsonObject.getString("error"));
                            Log.d("message",jsonObject.getString("message"));
                            String message=jsonObject.getString("message");

                            if (message.equals("Update Successfully..."))
                            {
                                Toast.makeText(AddEmergencyContact.this, ""+message, Toast.LENGTH_SHORT).show();

                            }
                            else
                            if (message.equals("User already registered"))
                            {
                                Toast.makeText(AddEmergencyContact.this, "Please Update Contacts", Toast.LENGTH_SHORT).show();
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
                params.put("family3",family3.getText().toString());
                params.put("userid",id);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void update2() {
        final ProgressDialog progressDialog = new ProgressDialog(AddEmergencyContact.this);
        progressDialog.setMessage("loading..Please Wait...");
        progressDialog.show();
        //String targeturl="http://hospital.myindiamade.com/MoneyTransfer/login.php?apicall=signup";
        String targeturl="http://android.dhamalexim.com/HumanSafty/updatefamily2.php";

        Log.d("targeturl_employee",targeturl);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, targeturl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            progressDialog.dismiss();
                            JSONObject jsonObject=new JSONObject(response);

                            Log.d("url_app product",jsonObject.getString("error"));
                            Log.d("message",jsonObject.getString("message"));
                            String message=jsonObject.getString("message");

                            if (message.equals("Update Successfully..."))
                            {
                                Toast.makeText(AddEmergencyContact.this, ""+message, Toast.LENGTH_SHORT).show();

                            }
                            else
                            if (message.equals("User already registered"))
                            {
                                Toast.makeText(AddEmergencyContact.this, "Please Update Contacts", Toast.LENGTH_SHORT).show();
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
                params.put("family2",family2.getText().toString());
                params.put("userid",id);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void update1() {
        final ProgressDialog progressDialog = new ProgressDialog(AddEmergencyContact.this);
        progressDialog.setMessage("loading..Please Wait...");
        progressDialog.show();
        //String targeturl="http://hospital.myindiamade.com/MoneyTransfer/login.php?apicall=signup";
        String targeturl="http://android.dhamalexim.com/HumanSafty/updatefamily1.php";

        Log.d("targeturl_employee",targeturl);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, targeturl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            progressDialog.dismiss();
                            JSONObject jsonObject=new JSONObject(response);

                            Log.d("url_app product",jsonObject.getString("error"));
                            Log.d("message",jsonObject.getString("message"));
                            String message=jsonObject.getString("message");

                            if (message.equals("Update Successfully..."))
                            {
                                Toast.makeText(AddEmergencyContact.this, ""+message, Toast.LENGTH_SHORT).show();

                            }
                            else
                            if (message.equals("User already registered"))
                            {
                                Toast.makeText(AddEmergencyContact.this, "Please Update Contacts", Toast.LENGTH_SHORT).show();
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
                params.put("family1",family1.getText().toString());
                params.put("userid",id);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void fetchdata() {
        String targetURL_Login="http://android.dhamalexim.com/HumanSafty/insertfamilyfriends.php?apicall=login";

        StringRequest stringRequestLogInBtn = new StringRequest(Request.Method.POST,
                targetURL_Login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject=new JSONObject(response);

                            Log.d("url_login",jsonObject.getString("error"));
                            Log.d("message",jsonObject.getString("message"));
                            String message=jsonObject.getString("message");
                            String id1 = jsonObject.getString("id");

                            fa1 =jsonObject.getString("family1");
                            fa2=jsonObject.getString("family2");
                            fa3 =jsonObject.getString("family3");
                            fr1=jsonObject.getString("friend1");
                            fr2 =jsonObject.getString("friend2");
                            fr3=jsonObject.getString("friend3");
                            if (jsonObject.getString("error").equals("false")) {
                                family1.setText(fa1);
                                family2.setText(fa2);
                                family3.setText(fa3);
                                friend1.setText(fr1);
                                friend2.setText(fr2);
                                friend3.setText(fr3);

                            }
                            else {
                                Toast.makeText(AddEmergencyContact.this, ""+message, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "Network Problem!", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid",id);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequestLogInBtn);
    }

    private void savedata() {
        final ProgressDialog progressDialog = new ProgressDialog(AddEmergencyContact.this);
        progressDialog.setMessage("loading..Please Wait...");
        progressDialog.show();
        //String targeturl="http://hospital.myindiamade.com/MoneyTransfer/login.php?apicall=signup";
        String targeturl="http://android.dhamalexim.com/HumanSafty/insertfamilyfriends.php?apicall=signup";

        Log.d("targeturl_employee",targeturl);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, targeturl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            progressDialog.dismiss();
                            JSONObject jsonObject=new JSONObject(response);

                            Log.d("url_app product",jsonObject.getString("error"));
                            Log.d("message",jsonObject.getString("message"));
                            String message=jsonObject.getString("message");

                            if (message.equals("User registered successfully"))
                            {
                                Toast.makeText(AddEmergencyContact.this, "Family And Friends Add Successfully", Toast.LENGTH_SHORT).show();

                            }
                            else
                            if (message.equals("User already registered"))
                            {
                                Toast.makeText(AddEmergencyContact.this, "Please Update Contacts", Toast.LENGTH_SHORT).show();
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
                params.put("family1",family1.getText().toString());
                params.put("family2",family2.getText().toString());
                params.put("family3",family3.getText().toString());
                params.put("friend1",friend1.getText().toString());
                params.put("friend2",friend2.getText().toString());
                params.put("friend3",friend3.getText().toString());
                params.put("userid",id);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
