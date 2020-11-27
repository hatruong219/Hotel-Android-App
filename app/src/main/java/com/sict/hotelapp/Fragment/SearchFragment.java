package com.sict.hotelapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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


    Context context;
    RecyclerView recycleview_search;
    SearchView editTextTimKiem;
    View view;
    Adapter adapter;
    ArrayList<ListRoom> listRooms = new ArrayList<>(  );
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate( R.layout.fragment_search, container, false );

        // anh xa chuaw
        recycleview_search = (RecyclerView) view.findViewById( R.id.recycleview_search );

        editTextTimKiem = (SearchView) view.findViewById( R.id.editTextTimKiem );

        search();


        return view;
    }
    private void getDataListRoom(String filter) {
        RequestQueue queue = Volley.newRequestQueue( getActivity() );
        StringRequest stringRequest = new StringRequest( Request.Method.POST,
                Api.api_search, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject( response );
                    JSONArray data = jsonObject.getJSONArray( "data11" );
                    JSONObject comment = jsonObject.getJSONObject( "comment" );
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

                recycleview_search.setHasFixedSize( true );
                recycleview_search.setLayoutManager( new LinearLayoutManager( context ) );
                adapter = new Adapter( getActivity(), listRooms );
                recycleview_search.setAdapter( (RecyclerView.Adapter) adapter );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( getContext(), "Lỗi " + error, Toast.LENGTH_SHORT ).show();
                error.printStackTrace();
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parrams = new HashMap<>();
                parrams.put( "key", filter );
                return parrams;

            }
        };
        queue.add( stringRequest );
    }


    // Tìm kiếm
    public void search() {

        editTextTimKiem.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getDataListRoom(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        } );

    }

}