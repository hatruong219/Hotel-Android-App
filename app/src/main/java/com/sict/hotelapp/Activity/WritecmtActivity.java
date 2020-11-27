package com.sict.hotelapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRatingBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WritecmtActivity extends AppCompatActivity {

    ImageView imgWriteComment;
    TextView tvProductComment;
    AppCompatButton btnAddComment;
    AppCompatRatingBar rbWriteComment;
    EditText edtWriteComment;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_writecmt );
        getSupportActionBar().hide();
        Anhxa();

        int ID_User = MainActivity.getID_User();
        btnAddComment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ID_User >0 ){
                    RequestQueue queue = Volley.newRequestQueue(getApplication());
                    StringRequest stringRequest = new StringRequest( Request.Method.POST, Api.api_addcomment, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if ( jsonObject.getBoolean("status") ){
                                    Toast.makeText(getApplicationContext(), "Đã thêm thành công đánh giá về sản phẩm", Toast.LENGTH_SHORT).show();
                                    Intent intent = getIntent();
                                    String ID_Room = intent.getStringExtra( "ID_Room" );
                                    Intent intent1 = new Intent( getApplicationContext(), CommentActivity.class );
                                    intent1.putExtra( "ID_Room", ID_Room );
                                    startActivity( intent1 );

                                }else {
                                    Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
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
                            int star = (int) rbWriteComment.getRating();
                            String Content_Comment = String.valueOf( edtWriteComment.getText());
                            DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
                            dateFormatter.setLenient(false);
                            Date today = new Date();
                            String date = dateFormatter.format(today);

                            Intent intent = getIntent();
                            String ID_Room = intent.getStringExtra( "ID_Room" );
                            parrams.put("ID_Room", ID_Room);
                            parrams.put( "ID_User", String.valueOf( ID_User ) );
                            parrams.put( "Star_Room", String.valueOf( star ) );
                            parrams.put( "Content_Comment", Content_Comment );
                            return parrams;
                        }
                    };
                    queue.add(stringRequest);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Bạn chưa đăng nhập, vui lòng đăng nhập để mua sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }
        } );

    }

    private void Anhxa() {
        edtWriteComment = (EditText) findViewById( R.id.edtWriteComment );
        btnAddComment = (AppCompatButton) findViewById( R.id.btnAddComment );
        rbWriteComment = (AppCompatRatingBar) findViewById( R.id.rbWriteComment );
    }
}