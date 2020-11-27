package com.sict.hotelapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRatingBar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.sict.hotelapp.MainActivity;
import com.sict.hotelapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BookingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_booking );
        Anhxa();
        GetData();
        bookingDatein.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        } );

        int ID_User = MainActivity.getID_User();
        btnaddbill.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ID_User>0){
                    RequestQueue queue = Volley.newRequestQueue(getApplication());
                    StringRequest stringRequest = new StringRequest( Request.Method.POST,
                            Api.api_addbill,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        Log.d( "change cart", "onResponse: "+response );
                                        if ( jsonObject.getBoolean("status") ){
                                            Intent intent = new Intent( getApplication(), BillOfMe.class );
                                            intent.putExtra("ID_User", ID_User);
                                            startActivity(intent);
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
                            int i = Integer.valueOf( String.valueOf( bookingDateout.getText() ) );
                            String Datein_Bill = String.valueOf( bookingDatein.getText() );
                            String Dateout_Bill = String.valueOf( bookingDateout.getText() ) ;
                            String NumberRoom_Bill = String.valueOf( bookingNum.getText() ) ;
                            String Price = String.valueOf( booking_PriceRoom.getText() ) ;
                            Intent intent = getIntent();
                            String ID_Room = intent.getStringExtra("ID_Room") ;
                            parrams.put("ID_Room", ID_Room);
                            parrams.put("Datein_Bill", Datein_Bill);
                            parrams.put("Dateout_Bill", Dateout_Bill);
                            parrams.put("NumberRoom_Bill", NumberRoom_Bill);
                            parrams.put("Price", Price);
                            parrams.put( "ID_User", String.valueOf( ID_User ) );
                            return parrams;
                        }
                    };
                    queue.add(stringRequest);
                }else {
                    Toast.makeText( getApplicationContext(), "Mời bạn đăng nhập hệ thống", Toast.LENGTH_SHORT );
                    Intent intent = new Intent( getApplication(), LoginActivity.class );
                    startActivity( intent );

                }
            }

        } );



    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    public void showDatePickerDialog1(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int m = month+1;
        String dates = year + "-" + m + "-" + dayOfMonth;

        bookingDatein.setText(dates);
    }


    private void GetData() {
        RequestQueue queue = Volley.newRequestQueue(getApplication());
        StringRequest stringRequest = new StringRequest( Request.Method.POST, Api.api_booking, new Response.Listener<String>() {
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
                        booking_NameRoom.setText( Name_Room );


                        String Kind_Room = object.getString( "Kind_Room" );
                        booking_KindRoom.setText( Kind_Room );

                        int Empty_Room = object.getInt( "Empty_Room" );
                        booking_EmptyRoom.setText("Có "+ Empty_Room+ " phòng" );

                        int Price_Room = object.getInt( "Price_Room" );
                        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###" );
                        String Giafomat = decimalFormat.format( Price_Room );
                        booking_PriceRoom.setText( Giafomat +" Vnđ");

                        String Img_Room = object.getString( "Img_Room" );
                        Picasso.with(context).load( Api.api_link_img_room +Img_Room)
                                .error(R.drawable.ic_launcher_background)
                                .into(booking_ImgRoom);

                        int Star_Room = object.getInt( "Star_Room" );
                        booking_StarRoom.setRating(Star_Room);
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




    ImageView booking_ImgRoom;
    TextView booking_NameRoom, booking_KindRoom, booking_PriceRoom, booking_EmptyRoom, bookingNum, bookingDatein;
    EditText bookingDateout;
    Button bookingSub, bookingAdd;
    AppCompatRatingBar booking_StarRoom;
    AppCompatButton btnaddbill;
    private void Anhxa() {
        booking_ImgRoom = (ImageView) findViewById( R.id.booking_ImgRoom );
        booking_NameRoom = (TextView) findViewById( R.id.booking_NameRoom );
        booking_KindRoom = (TextView) findViewById( R.id.booking_KindRoom );
        booking_PriceRoom = (TextView) findViewById( R.id.booking_PriceRoom );
        bookingDatein = (TextView) findViewById( R.id.bookingDatein );
        bookingDateout = (EditText) findViewById( R.id.bookingDateout );
        booking_EmptyRoom = (TextView) findViewById( R.id.booking_EmptyRoom );
        bookingNum = (TextView) findViewById( R.id.bookingNum );
        bookingSub = (Button) findViewById( R.id.bookingSub );
        bookingAdd = (Button) findViewById( R.id.bookingAdd );
        booking_StarRoom = (AppCompatRatingBar) findViewById( R.id.booking_StarRoom );
        btnaddbill = (AppCompatButton) findViewById( R.id.btnaddbill );

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}