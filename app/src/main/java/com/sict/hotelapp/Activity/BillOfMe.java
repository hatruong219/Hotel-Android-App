package com.sict.hotelapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sict.hotelapp.AdapterListBill.Adapter;
import com.sict.hotelapp.AdapterListBill.ListBill;
import com.sict.hotelapp.AdapterListRoom.ListRoom;
import com.sict.hotelapp.Api;
import com.sict.hotelapp.MainActivity;
import com.sict.hotelapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BillOfMe extends AppCompatActivity {
    RecyclerView recycleview_bill, recycleview_listbill;
    Adapter adapter;
    Context context;
    ArrayList<ListBill> listBills1 = new ArrayList<>(  );
    ArrayList<ListBill> listBills2 = new ArrayList<>(  );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bill_of_me );
        getSupportActionBar().hide();
        recycleview_bill = (RecyclerView) findViewById( R.id.recycleview_bill );
        GetData1();
        recycleview_listbill = (RecyclerView) findViewById( R.id.recycleview_listbill );
        GetData2();

    }

    private void GetData1() {
        RequestQueue queue = Volley.newRequestQueue( getApplication() );
        StringRequest stringRequest = new StringRequest( Request.Method.POST,
                Api.api_billofme, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject( response );
                    JSONArray data = jsonObject.getJSONArray( "data" );
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject object = data.getJSONObject( i );
                        final ListBill listBill1 = new ListBill(
                                object.getInt( "ID_Room" ),
                                object.getString( "Img_Room" ),
                                object.getString( "Name_Room" ),
                                object.getString( "Kind_Room" ),
                                object.getInt( "Price_Room" ),
                                object.getString( "Datein_Bill" ),
                                object.getString( "Dateout_Bill" ),
                                object.getInt( "Total_Bill" ),
                                object.getInt( "NumberRoom_Bill" )

                        );
                        listBills1.add( listBill1 );
                    }
                    Log.d( "dsnsjfajs", "onResponse: "+response );

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                recycleview_bill.setHasFixedSize( true );
                recycleview_bill.setLayoutManager( new LinearLayoutManager( context ) );
                adapter = new Adapter( getApplication(), listBills1 );
                recycleview_bill.setAdapter( (RecyclerView.Adapter) adapter );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( getApplicationContext(), "Lỗi " + error, Toast.LENGTH_SHORT ).show();
                error.printStackTrace();
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parrams = new HashMap<>();
                int ID_User = MainActivity.getID_User();
                parrams.put( "ID_User", String.valueOf( ID_User ) );
                return parrams;
            }
        };
        queue.add( stringRequest );
    }
    private void GetData2() {
        RequestQueue queue = Volley.newRequestQueue( getApplication() );
        StringRequest stringRequest = new StringRequest( Request.Method.POST,
                Api.api_listbillofme, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject( response );
                    JSONArray data = jsonObject.getJSONArray( "data" );
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject object = data.getJSONObject( i );
                        final ListBill listBill2 = new ListBill(
                                object.getInt( "ID_Room" ),
                                object.getString( "Img_Room" ),
                                object.getString( "Name_Room" ),
                                object.getString( "Kind_Room" ),
                                object.getInt( "Price_Room" ),
                                object.getString( "Datein_Bill" ),
                                object.getString( "Dateout_Bill" ),
                                object.getInt( "Total_Bill" ),
                                object.getInt( "NumberRoom_Bill" )

                        );
                        listBills2.add( listBill2 );
                    }
                    Log.d( "dsnsjfajs", "onResponse: "+response );

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                recycleview_listbill.setHasFixedSize( true );
                recycleview_listbill.setLayoutManager( new LinearLayoutManager( context ) );
                adapter = new Adapter( getApplication(), listBills2 );
                recycleview_listbill.setAdapter( (RecyclerView.Adapter) adapter );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( getApplicationContext(), "Lỗi " + error, Toast.LENGTH_SHORT ).show();
                error.printStackTrace();
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parrams = new HashMap<>();
                int ID_User = MainActivity.getID_User();
                parrams.put( "ID_User", String.valueOf( ID_User ) );
                return parrams;
            }
        };
        queue.add( stringRequest );
    }
}