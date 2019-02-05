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


public class MainActivity extends AppCompatActivity implements Response.Listener<JSONArray>,Response.ErrorListener {

    TextView txt_nombre;
    TextView txt_apellido;
    TextView txt_id;
    Button btn_prueba;

    String SPREAD_SHEET_ID;
    String urlscriptlectura;
    String urlscriptescritura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_nombre=findViewById(R.id.txt_nombre);
        txt_apellido=findViewById(R.id.txt_apellido);
        txt_id=findViewById(R.id.txt_id);
        btn_prueba=findViewById(R.id.btn_prueba);


        SPREAD_SHEET_ID= "1gYHj7yLh6ioC1W1t0IngaxtIkV2S35euJAwAJ0mQLwY";
        urlscriptlectura="https://script.google.com/macros/s/AKfycbye1OqkVZ7dhXoH8TeB-wvRcGOeURFTHSbpRIMU755U_rJkmLk/exec?spreadsheetId="+SPREAD_SHEET_ID+"&sheet";
        urlscriptescritura="https://script.google.com/macros/s/AKfycbwK7r70VhTpV2i8LzpoDIzbW6th3VohMDtjW7kIMMywjVKbkJBY/exec?spreadsheetId="+SPREAD_SHEET_ID+"&sheet";

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

    public  void onclick(View v){

        tomardatos();

    }


    void tomardatos(){
        RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, urlscriptlectura,null, this,this );
        queue.add(jsonArrayRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("onErrorResponse",error.toString());
        Toast.makeText(getApplicationContext(), "error al correctar", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONArray response) {

        Log.e("onResponse",response.toString());
        Toast.makeText(getApplicationContext(), "conectado", Toast.LENGTH_LONG).show();
        try {
            for(int i=0;i<response.length();i++){

                JSONObject prueba=response.getJSONObject(i);
                String name=prueba.getString("nombre");
                int id=prueba.getInt("id");
                String apellido=prueba.getString("apellido");

                txt_nombre.append(name+" , "+ apellido+" , "+String.valueOf(id)+"\n\n");

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
