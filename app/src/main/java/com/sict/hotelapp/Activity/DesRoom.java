package com.sict.hotelapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sict.hotelapp.Api;
import com.sict.hotelapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class DesRoom extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_des_room );
        Anhxa();
        Getdata();
        btnAddRoom.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String ID_Room = intent.getStringExtra("ID_Room" );
                Intent intent1 = new Intent( getApplicationContext(), BookingActivity.class );
                intent1.putExtra("ID_Room", ID_Room);
                startActivity(intent1);
            }
        } );
        desroom_Comment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String ID_Room = intent.getStringExtra("ID_Room" );
                Intent intent1 = new Intent( getApplicationContext(), CommentActivity.class );
                intent1.putExtra("ID_Room", ID_Room);
                startActivity(intent1);
            }
        } );
    }

    private void Getdata() {
        RequestQueue queue = Volley.newRequestQueue(getApplication());
        StringRequest stringRequest = new StringRequest( Request.Method.POST, Api.api_des_room, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d( "tata", "onResponse: "+response );

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for(int i=0; i<1; i++)
                    {
                        JSONObject object=data.getJSONObject(i);
                        int ID_Hotel = object.getInt( "ID_Hotel" );
                        int ID_Room = object.getInt( "ID_Room" );

                        String Name_Room = object.getString( "Name_Room" );
                        desroom_NameRoom.setText( Name_Room );

                        String Name_Hotel = object.getString( "Name_Hotel" );
                        desroom_NameHotel.setText( Name_Hotel );

                        String Kind_Room = object.getString( "Kind_Room" );
                        desroom_KindRoom.setText( Kind_Room );

                        int Comment = jsonObject.getInt( "comment" );
                        desroom_Comment.setText( "( XEM "+Comment+" NHẬN XÉT )" );

                        int Price_Room = object.getInt( "Price_Room" );
                        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###" );
                        String Giafomat = decimalFormat.format( Price_Room );
                        desroom_PriceRoom.setText( Giafomat +" Vnđ");

                        String Des_Room = object.getString( "Des_Room" );
                        desroom_DesRoom.setHtml(Des_Room,new HtmlHttpImageGetter(desroom_DesRoom, null, true));

                        String Img_Room = object.getString( "Img_Room" );
                        Picasso.with(context).load( Api.api_link_img_room +Img_Room)
                                .error(R.drawable.ic_launcher_background)
                                .into(desroom_ImgRoom);

                        int Star_Room = object.getInt( "Star_Room" );
                        desroom_StarRoom.setRating(Star_Room);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi " + error, Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parrams = new HashMap<>();
                Intent intent = getIntent();
                String ID_Room = intent.getStringExtra("ID_Room" );
                parrams.put("ID_Room", ID_Room);
                return parrams;
            }
        };
        queue.add(stringRequest);
    }


    ImageView desroom_ImgRoom;
    TextView desroom_NameRoom, desroom_NameHotel, desroom_Comment,desroom_KindRoom, desroom_PriceRoom;
    Button btnAddRoom, btnLikeRoom;
    HtmlTextView desroom_DesRoom;
    AppCompatRatingBar desroom_StarRoom;
    private void Anhxa() {
        desroom_ImgRoom = (ImageView) findViewById( R.id.desroom_ImgRoom );
        desroom_NameRoom = (TextView) findViewById( R.id.desroom_NameRoom );
        desroom_NameHotel = (TextView) findViewById( R.id.desroom_NameHotel );
        desroom_Comment = (TextView) findViewById( R.id.desroom_Comment );
        desroom_KindRoom = (TextView) findViewById( R.id.desroom_KindRoom );
        desroom_PriceRoom = (TextView) findViewById( R.id.desroom_PriceRoom );
        btnAddRoom = (Button) findViewById( R.id.btnAddRoom );
        btnLikeRoom = (Button) findViewById( R.id.btnLikeRoom );
        desroom_DesRoom = (HtmlTextView) findViewById( R.id.desroom_DesRoom );
        desroom_StarRoom = (AppCompatRatingBar) findViewById( R.id.desroom_StarRoom );

    }
}