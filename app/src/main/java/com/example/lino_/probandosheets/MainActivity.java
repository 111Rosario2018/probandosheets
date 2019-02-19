package com.example.lino_.probandosheets;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;


public class MainActivity extends AppCompatActivity  {

    TextView txt_nombre;
    TextView txt_apellido;
    TextView txt_id;
    Button btn_prueba;
    Button btn_tomardatos;

    String SPREAD_SHEET_ID;
    String urlscriptlectura;
    String urlscriptescritura;

    public static final String APP_SCRIPT_WEB_APP_URL = "https://script.google.com/macros/s/AKfycbzTQfznVatC3rAP0n4uzp_tPSqLCbn5EgbLGNpG9MkLt0ZAG2I/exec";
    public static final String ADD_USER_URL = APP_SCRIPT_WEB_APP_URL;
    public static final String LIST_USER_URL = APP_SCRIPT_WEB_APP_URL+"?action=readAll";

    public static final String KEY_ID = "uId";
    public static final String KEY_NAME = "uName";
    public static final String KEY_IMAGE = "uImage";
    public  static final String KEY_ACTION = "action";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_nombre = findViewById(R.id.txt_nombre);
        txt_apellido = findViewById(R.id.txt_apellido);
        txt_id = findViewById(R.id.txt_id);
        btn_prueba = findViewById(R.id.btn_prueba);
        btn_tomardatos=findViewById(R.id.btn_tomardatos);

        SPREAD_SHEET_ID = "1gYHj7yLh6ioC1W1t0IngaxtIkV2S35euJAwAJ0mQLwY";
        urlscriptlectura = "https://script.google.com/macros/s/AKfycbye1OqkVZ7dhXoH8TeB-wvRcGOeURFTHSbpRIMU755U_rJkmLk/exec?spreadsheetId=" + SPREAD_SHEET_ID + "&sheet";
        //urlscriptescritura="https://script.google.com/macros/s/AKfycbwK7r70VhTpV2i8LzpoDIzbW6th3VohMDtjW7kIMMywjVKbkJBY/exec?spreadsheetId="+SPREAD_SHEET_ID+"&sheet";
        urlscriptescritura = "https://script.google.com/macros/s/AKfycbx9fUHMLr3u3_WsgfntNRDOvoWESavhYBLILbwCmwxMSm1Zo3R7/exec";
        //tomardatos();
        //addItemToSheet();


    }

/*
    private void   addItemToSheet() {

         final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");
         final String name = "josefo";
         final String brand = "Pedorrines";




        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlscriptescritura,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "funca", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("id","20");
                parmas.put("nombre","pepe");
                parmas.put("apellido",brand);

                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);


    }

*/


    ///////////////////////////////////////////////////////////

    public void onclick(View v) {
        txt_nombre.setText("");
        addUser();

    }

    public void mostrardatos(View v){
        tomardatos();
    }

public void addUser(){
    JSONObject objeto=new JSONObject();

    JSONArray rowsArray=new JSONArray();
    JSONArray row1=new JSONArray();
    JSONArray row2=new JSONArray();


    try {

        objeto.put("hojaid","1gYHj7yLh6ioC1W1t0IngaxtIkV2S35euJAwAJ0mQLwY");
        objeto.put("sheetName","Sheet1");
    } catch (JSONException e) {
        e.printStackTrace();
    }


    row1.put("6");
    row1.put("jose");
    row1.put("meljide");


    row2.put("8");
    row2.put("jacinto");
    row2.put("papanatas");

    rowsArray.put(row1);
    rowsArray.put(row2);

    try {
        objeto.put("rows",rowsArray);
    } catch (JSONException e) {
        e.printStackTrace();
    }

    Log.e("objetojson creado: ",objeto.toString());


    JsonObject pro=new JsonObject();
    pro.addProperty("ID",8);
    pro.addProperty("Nombre","Ernestino");
    pro.addProperty("Apellido","josefono");


    Log.e("otro json",pro.toString());




    RequestQueue queue=Volley.newRequestQueue(this);

    JsonObjectRequest llamada=new JsonObjectRequest(urlscriptescritura,objeto, new Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Toast.makeText(getApplicationContext(), "funca", Toast.LENGTH_LONG).show();

            Log.e("objetojson creado: ",response.toString());

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), "no funca", Toast.LENGTH_LONG).show();
            Log.e("error de mierda",error.toString());
        }
    });

    queue.add(llamada);

    }









    void tomardatos(){
        RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, urlscriptlectura, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(getApplicationContext(), "funca", Toast.LENGTH_LONG).show();
                Log.e("NO FUNCIONA",response.toString());
               // txt_nombre.setText("");

                try {
                    for(int i=0;i<response.length();i++){

                        JSONObject prueba=response.getJSONObject(i);
                        String name=prueba.getString("Nombre");
                        int id=prueba.getInt("ID");
                        String apellido=prueba.getString("Apellido");

                        txt_nombre.append(String.valueOf(id)+" - "+name+"  "+ apellido+"\n\n");




                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "no funca"+error.toString(), Toast.LENGTH_LONG).show();
                //Log.e("NO FUNCIONA",error.toString());
            }
        });
        queue.add(jsonArrayRequest);
    }



}




