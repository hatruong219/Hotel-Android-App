package com.sict.hotelapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sict.hotelapp.AdapterListComment.Adapter;
import com.sict.hotelapp.AdapterListComment.ListComment;
import com.sict.hotelapp.Api;
import com.sict.hotelapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {

    RecyclerView recycleview_comment;
    AppCompatButton btnWriteComment;
    View view;
    Adapter adapter;
    Context context;
    ArrayList<ListComment> comments = new ArrayList<>(  );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_comment );
        getSupportActionBar().hide();


        //ánh xạ
        btnWriteComment = (AppCompatButton) findViewById( R.id.btnWriteComment );
        recycleview_comment = (RecyclerView) findViewById( R.id.recycleview_comment );
        recycleview_comment.setLayoutManager( new LinearLayoutManager( this ) );
        getDataListComment();

        btnWriteComment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String ID_Room = intent.getStringExtra( "ID_Room" );

                Intent intent1 = new Intent( getApplicationContext(), WritecmtActivity.class );
                intent1.putExtra( "ID_Room", ID_Room );
                startActivity( intent1 );
            }
        } );

    }

    private void getDataListComment() {
        RequestQueue queue = Volley.newRequestQueue(getApplication());
        StringRequest stringRequest = new StringRequest( Request.Method.POST,
                Api.api_listcomment,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            JSONArray data = jsonObject.getJSONArray( "data" );
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject object = data.getJSONObject( i );
                                final ListComment comment = new ListComment(
                                        object.getString( "Name_User" ),
                                        object.getString( "created_at" ),
                                        object.getString( "Content_Comment" ),
                                        object.getString( "Img_User" )
                                );


                                comments.add( comment );
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        recycleview_comment.setHasFixedSize( true );
                        recycleview_comment.setLayoutManager(new LinearLayoutManager( context ));
                        adapter = new Adapter( getApplicationContext(), comments );
                        recycleview_comment.setAdapter( (RecyclerView.Adapter) adapter );
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
                String ID_Room = intent.getStringExtra( "ID_Room" );
                parrams.put("ID_Room", ID_Room);
                return parrams;
            }
        };
        queue.add(stringRequest);
    }
}