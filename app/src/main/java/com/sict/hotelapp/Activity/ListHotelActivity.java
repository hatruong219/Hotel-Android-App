package com.sict.hotelapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sict.hotelapp.AdapterListHotel.Adapter;
import com.sict.hotelapp.AdapterListHotel.ListHotel;
import com.sict.hotelapp.Api;
import com.sict.hotelapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListHotelActivity extends AppCompatActivity {
    RecyclerView recycleview_listhotel;
    View view;
    Adapter adapter;
    Context context;
    ArrayList<ListHotel> listHotels = new ArrayList<>(  );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_hotel );
        getSupportActionBar().hide();

        //ánh xạ
        recycleview_listhotel = (RecyclerView) findViewById( R.id.recycleview_listhotel );
        recycleview_listhotel.setLayoutManager( new LinearLayoutManager( this ) );
        getDataListHotel();

    }

    private void getDataListHotel() {
        RequestQueue queue = Volley.newRequestQueue( getApplication() );
        StringRequest stringRequest = new StringRequest( Request.Method.POST,
                Api.api_home_all_hotel_north,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            JSONArray data = jsonObject.getJSONArray( "data" );
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject object = data.getJSONObject( i );
                                final ListHotel listHotel = new ListHotel(
                                        object.getInt( "ID_Hotel" ),
                                        object.getString( "Img_Hotel" ),
                                        object.getString( "Name_Hotel" ),
                                        object.getString( "Address_Hotel" ),
                                        object.getInt( "Level_Hotel" ),
                                        object.getInt( "LowPrice_Hotel" )
                                );

                                listHotels.add( listHotel );
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        recycleview_listhotel.setHasFixedSize( true );
                        recycleview_listhotel.setLayoutManager( new LinearLayoutManager( context ) );
                        adapter = new Adapter( getApplication(), listHotels );
                        recycleview_listhotel.setAdapter( (RecyclerView.Adapter) adapter );
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
                Intent intent = getIntent();
                String ID_Area = String.valueOf( intent.getIntExtra( "ID_Area", 0 ) );
                parrams.put( "ID_Area", ID_Area );
                return parrams;
            }
        };
        queue.add( stringRequest );
    }
}