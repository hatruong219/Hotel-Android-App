package com.sict.hotelapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRatingBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class DesHotel extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_des_hotel );
        Anhxa();
        getData();

        btnlistRoom.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String ID_Hotel = intent.getStringExtra("ID_Hotel" );

                Intent intent1 = new Intent( getApplicationContext(), ListRoomActivity.class );
                intent1.putExtra("ID_Hotel", ID_Hotel);
                startActivity(intent1);
            }
        } );
    }



    // hàm lấy data
    public  void getData(){
        RequestQueue queue = Volley.newRequestQueue(getApplication());
        StringRequest stringRequest = new StringRequest( Request.Method.POST, Api.api_des_hotel, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d( "hoaihoai", "onResponse: "+response );

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for(int i=0; i<1; i++)
                    {
                        JSONObject object=data.getJSONObject(i);
                        int ID_Hotel = object.getInt( "ID_Hotel" );
                        String Name_Hotel = object.getString( "Name_Hotel" );
                        String Address_Hotel = object.getString( "Address_Hotel" );
                        int LowPrice_Hotel = object.getInt( "LowPrice_Hotel" );
                        int  Level_Hotel = object.getInt( "Level_Hotel" );
                        String Img_Hotel = object.getString( "Img_Hotel" );
                        String Information_Hotel = object.getString( "Information_Hotel" );

                        //Fommat giá
                        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###" );
                        String Giafomat = decimalFormat.format( LowPrice_Hotel );
                        deshotel_LowPriceHotel.setText("Giá từ: " +Giafomat +" Vnđ");

                        deshotel_NameHotel.setText( Name_Hotel );
                        deshotel_AddressHotel.setText( Address_Hotel );
                        deshotel_InformationHotel.setHtml(Information_Hotel,new HtmlHttpImageGetter(deshotel_InformationHotel, null, true));
                        Picasso.with(context).load( Api.api_link_img_hotel+Img_Hotel)
                                .error(R.drawable.ic_launcher_background)
                                .into(deshotel_ImgHotel);
                        deshotel_LevelHotel.setRating(Level_Hotel);
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
                String ID_Hotel = intent.getStringExtra("ID_Hotel" );
                parrams.put("ID_Hotel", ID_Hotel);
                return parrams;
            }
        };
        queue.add(stringRequest);
    }
    ImageView deshotel_ImgHotel;
    TextView deshotel_AddressHotel, deshotel_LowPriceHotel, deshotel_NameHotel;
    HtmlTextView deshotel_InformationHotel;
    AppCompatRatingBar deshotel_LevelHotel;
    AppCompatButton btnlistRoom;
    public void Anhxa(){
        deshotel_NameHotel = (TextView) findViewById( R.id.deshotel_NameHotel );
        deshotel_ImgHotel = (ImageView) findViewById( R.id.deshotel_ImgHotel );
        deshotel_AddressHotel = (TextView) findViewById( R.id.deshotel_AddressHotel );
        deshotel_LowPriceHotel = (TextView) findViewById( R.id.deshotel_LowPriceHotel );
        deshotel_InformationHotel = (HtmlTextView) findViewById( R.id.deshotel_InformationHotel );
        deshotel_LevelHotel = (AppCompatRatingBar) findViewById( R.id.deshotel_LevelHotel );
        btnlistRoom = (AppCompatButton) findViewById( R.id.btnlistRoom );

    }
}