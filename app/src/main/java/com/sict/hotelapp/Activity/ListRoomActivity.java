package com.sict.hotelapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sict.hotelapp.AdapterListHotel.ListHotel;
import com.sict.hotelapp.AdapterListRoom.Adapter;
import com.sict.hotelapp.AdapterListRoom.ListRoom;
import com.sict.hotelapp.Api;
import com.sict.hotelapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListRoomActivity extends AppCompatActivity {
    RecyclerView recycleview_listroom;
    View view;
    Adapter adapter;
    Context context;
    ArrayList<ListRoom> listRooms = new ArrayList<>(  );
    TextView listroom_Num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_room );
        getSupportActionBar().hide();

        //ánh xạ
        recycleview_listroom = (RecyclerView) findViewById( R.id.recycleview_listroom );
        recycleview_listroom.setLayoutManager( new LinearLayoutManager( this ) );
        listroom_Num = (TextView) findViewById( R.id.listroom_Num );
        getDataListRoom();
    }

    private void getDataListRoom() {
        RequestQueue queue = Volley.newRequestQueue( getApplication() );
        StringRequest stringRequest = new StringRequest( Request.Method.POST,
                Api.api_home_all_room, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            JSONArray data = jsonObject.getJSONArray( "data11" );
                            JSONObject comment = jsonObject.getJSONObject( "comment" );
                            int Num = jsonObject.getInt( "num" );
                            listroom_Num.setText( "KHÁCH SẠN CÓ "+Num+" LOẠI PHÒNG " );
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject object = data.getJSONObject( i );
                                final ListRoom listRoom = new ListRoom(
                                        object.getInt( "ID_Room" ),
                                        object.getString( "Img_Room" ),
                                        object.getString( "Name_Room" ),
                                        object.getString( "Kind_Room" ),
                                        object.getInt( "Empty_Room" ),
                                        object.getInt( "Price_Room" ),
                                        object.getInt( "Star_Room" ),
                                        (int) comment.getInt( String.valueOf( object.getInt( "ID_Room" ) ) )

                                );
                                listRooms.add( listRoom );
                            }
                            Log.d( "dsnsjfajs", "onResponse: "+response );

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        recycleview_listroom.setHasFixedSize( true );
                        recycleview_listroom.setLayoutManager( new LinearLayoutManager( context ) );
                        adapter = new Adapter( getApplication(), listRooms );
                        recycleview_listroom.setAdapter( (RecyclerView.Adapter) adapter );
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
                String ID_Hotel = intent.getStringExtra( "ID_Hotel" ) ;
                parrams.put( "ID_Hotel", ID_Hotel );
                return parrams;
            }
        };
        queue.add( stringRequest );
    }

}