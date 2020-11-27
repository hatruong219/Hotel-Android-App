package com.sict.hotelapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.sict.hotelapp.Activity.ListHotelActivity;
import com.sict.hotelapp.AdapterHome.Adapter;
import com.sict.hotelapp.AdapterHome.HotelHome;
import com.sict.hotelapp.Api;
import com.sict.hotelapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    Context context;
    RecyclerView recycleview_homemb;
    RecyclerView recycleview_homemt;
    RecyclerView recycleview_homemn;
    View view;
    ViewFlipper viewlipper_homemb;
    ViewFlipper viewlipper_homemt;
    ViewFlipper viewlipper_homemn;
    TextView tvSelectAll1;
    TextView tvSelectAll2;
    TextView tvSelectAll3;
    Adapter adapter;
    ArrayList<HotelHome> hotelHomes_north = new ArrayList<>(  );
    ArrayList<HotelHome> hotelHomes_central = new ArrayList<>(  );
    ArrayList<HotelHome> hotelHomes_south = new ArrayList<>(  );


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_home, container, false );
        // Miền bắc
        recycleview_homemb = view.findViewById( R.id.recycleview_homemb );
        viewlipper_homemb = view.findViewById(R.id.viewlipper_homemb);
        ActionViewFlipper_mb();
        Getdata_mb();
        // Miền trung
        recycleview_homemt = view.findViewById( R.id.recycleview_homemt );
        viewlipper_homemt = view.findViewById(R.id.viewlipper_homemt);
        ActionViewFlipper_mt();
        Getdata_mt();
        // Miền nam
        recycleview_homemn = view.findViewById( R.id.recycleview_homemn );
        viewlipper_homemn = view.findViewById(R.id.viewlipper_homemn);
        ActionViewFlipper_mn();
        Getdata_mn();


        tvSelectAll1 = view.findViewById( R.id.tvSelectAll1 );
        tvSelectAll2 = view.findViewById( R.id.tvSelectAll2 );
        tvSelectAll3 = view.findViewById( R.id.tvSelectAll3 );


        tvSelectAll1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( view.getContext(), ListHotelActivity.class );
                intent.putExtra("ID_Area", 1);
                view.getContext().startActivity(intent);
            }
        } );
        tvSelectAll2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( view.getContext(), ListHotelActivity.class );
                intent.putExtra("ID_Area", 2);
                view.getContext().startActivity(intent);
            }
        } );
        tvSelectAll3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( view.getContext(), ListHotelActivity.class );
                intent.putExtra("ID_Area", 3);
                view.getContext().startActivity(intent);
            }
        } );
        // Inflate the layout for this fragment
        return view;
    }






// Miền bắc

    //lay du lieu api
    private void Getdata_mb() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( Request.Method.GET, Api.api_home_hotel_north, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d( "TAG", "onResponse: " + response.toString() );
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject spObject = response.getJSONObject( i );
                        HotelHome hotelHome = new HotelHome(  );
                        hotelHome.setID_Hotel( spObject.getInt( "ID_Hotel" ) );
                        hotelHome.setImg_Hotel( spObject.getString( "Img_Hotel" ) );
                        hotelHome.setName_Hotel( spObject.getString( "Name_Hotel" ) );
                        hotelHome.setLevel_Hotel( spObject.getInt( "Level_Hotel" ) );
                        hotelHome.setAddress_Hotel( spObject.getString( "Address_Hotel" ) );
                        hotelHomes_north.add( hotelHome );


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //xét layout cho các item
                recycleview_homemb.setHasFixedSize( true );
                recycleview_homemb.setLayoutManager( new GridLayoutManager(getContext(), 2, LinearLayoutManager.HORIZONTAL, false) );
                adapter = new Adapter( getActivity(), hotelHomes_north );
                recycleview_homemb.setAdapter( adapter );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d( "tag","onErrorResponse"+error.getMessage() );
            }
        } );
        queue.add( jsonArrayRequest );
    }


    //slide quang cao
    private void ActionViewFlipper_mb() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add( "https://static.tuoitre.vn/tto/i/s626/2017/07/10/mien-bac-bung-no-condotel-biet-thu-tren-nui-hut-von-dau-tu-1-1499659207.jpg" );
        mangquangcao.add( "https://media-cdn.tripadvisor.com/media/photo-s/0e/cd/a4/93/khach-s-n-mu-ng-thanh.jpg" );
        mangquangcao.add( "https://vinpearlresortvietnam.com/wp-content/uploads/2019/11/vinpearl-rivera-hai-phong-hotel-10.jpg" );
        mangquangcao.add( "https://cdn3.ivivu.com/2020/08/69689553_448764005727770_7188571533326999015_n-370x215.jpg" );
        for (int i=0; i<mangquangcao.size(); i++){
            ImageView imageview = new ImageView(getActivity());
            Glide.with( this )
                    .load( mangquangcao.get( i ) )
                    .apply( new RequestOptions()
                            .transform( new CenterCrop(), new RoundedCorners( 16 ) ) )
                    .into( imageview );
            imageview.setScaleType( ImageView.ScaleType.FIT_XY );
            viewlipper_homemb.addView(imageview);
        }
        viewlipper_homemb.setFlipInterval(5000);
        viewlipper_homemb.setAutoStart(true);
    }




//Miền Trung
//lay du lieu api
    private void Getdata_mt() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( Request.Method.GET, Api.api_home_hotel_central, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d( "TAG", "onResponse: " + response.toString() );
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject spObject = response.getJSONObject( i );
                        HotelHome hotelHome = new HotelHome(  );
                        hotelHome.setID_Hotel( spObject.getInt( "ID_Hotel" ) );
                        hotelHome.setImg_Hotel( spObject.getString( "Img_Hotel" ) );
                        hotelHome.setName_Hotel( spObject.getString( "Name_Hotel" ) );
                        hotelHome.setLevel_Hotel( spObject.getInt( "Level_Hotel" ) );
                        hotelHome.setAddress_Hotel( spObject.getString( "Address_Hotel" ) );
                        hotelHomes_central.add( hotelHome );


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //xét layout cho các item
                recycleview_homemt.setHasFixedSize( true );
                recycleview_homemt.setLayoutManager( new GridLayoutManager(getContext(), 2, LinearLayoutManager.HORIZONTAL, false) );
                adapter = new Adapter( getActivity(), hotelHomes_central );
                recycleview_homemt.setAdapter( adapter );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d( "tag","onErrorResponse"+error.getMessage() );
            }
        } );
        queue.add( jsonArrayRequest );
    }


    //slide quang cao
    private void ActionViewFlipper_mt() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add( "https://cdn3.ivivu.com/2020/08/69689553_448764005727770_7188571533326999015_n-370x215.jpg" );
        mangquangcao.add( "https://image.tinnhanhchungkhoan.vn/715x540/uploaded/thachbac/2017_10_20/boutiqueshophouse3_sqsd.jpg" );
        mangquangcao.add( "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTAdJbU2HhU8Em8ypxYLiWT3vj09pFxnXq4rw&usqp=CAU" );
        mangquangcao.add( "https://cdn3.ivivu.com/2020/08/69689553_448764005727770_7188571533326999015_n-370x215.jpg" );
        for (int i=0; i<mangquangcao.size(); i++){
            ImageView imageview = new ImageView(getActivity());
            Glide.with( this )
                    .load( mangquangcao.get( i ) )
                    .apply( new RequestOptions()
                            .transform( new CenterCrop(), new RoundedCorners( 16 ) ) )
                    .into( imageview );
            imageview.setScaleType( ImageView.ScaleType.FIT_XY );
            viewlipper_homemt.addView(imageview);
        }
        viewlipper_homemt.setFlipInterval(5000);
        viewlipper_homemt.setAutoStart(true);
    }


//Miền nam

    //lay du lieu api
    private void Getdata_mn() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( Request.Method.GET, Api.api_home_hotel_south, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d( "TAG", "onResponse: " + response.toString() );
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject spObject = response.getJSONObject( i );
                        HotelHome hotelHome = new HotelHome(  );
                        hotelHome.setID_Hotel( spObject.getInt( "ID_Hotel" ) );
                        hotelHome.setImg_Hotel( spObject.getString( "Img_Hotel" ) );
                        hotelHome.setName_Hotel( spObject.getString( "Name_Hotel" ) );
                        hotelHome.setLevel_Hotel( spObject.getInt( "Level_Hotel" ) );
                        hotelHome.setAddress_Hotel( spObject.getString( "Address_Hotel" ) );
                        hotelHomes_south.add( hotelHome );


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //xét layout cho các item
                recycleview_homemn.setHasFixedSize( true );
                recycleview_homemn.setLayoutManager( new GridLayoutManager(getContext(), 2, LinearLayoutManager.HORIZONTAL, false) );
                adapter = new Adapter( getActivity(), hotelHomes_south );
                recycleview_homemn.setAdapter( adapter );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d( "tag","onErrorResponse"+error.getMessage() );
            }
        } );
        queue.add( jsonArrayRequest );
    }


    //slide quang cao
    private void ActionViewFlipper_mn() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add( "https://image.tinnhanhchungkhoan.vn/715x540/uploaded/thachbac/2017_10_20/boutiqueshophouse3_sqsd.jpg" );
        mangquangcao.add( "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTAdJbU2HhU8Em8ypxYLiWT3vj09pFxnXq4rw&usqp=CAU" );
        mangquangcao.add( "https://cdn3.ivivu.com/2020/08/69689553_448764005727770_7188571533326999015_n-370x215.jpg" );
        mangquangcao.add( "https://image.tinnhanhchungkhoan.vn/715x540/uploaded/thachbac/2017_10_20/boutiqueshophouse3_sqsd.jpg" );
        for (int i=0; i<mangquangcao.size(); i++){
            ImageView imageview = new ImageView(getActivity());
            Glide.with( this )
                    .load( mangquangcao.get( i ) )
                    .apply( new RequestOptions()
                            .transform( new CenterCrop(), new RoundedCorners( 16 ) ) )
                    .into( imageview );
            imageview.setScaleType( ImageView.ScaleType.FIT_XY );
            viewlipper_homemn.addView(imageview);
        }
        viewlipper_homemn.setFlipInterval(5000);
        viewlipper_homemn.setAutoStart(true);
    }
}