package com.sict.hotelapp.AdapterHome;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sict.hotelapp.Activity.DesHotel;
import com.sict.hotelapp.Api;
import com.sict.hotelapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    ArrayList<HotelHome> hotelHomes;

    public Adapter(Context context, ArrayList<HotelHome> hotelHomes) {
        this.context = context;
        this.hotelHomes = hotelHomes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from( parent.getContext() );
        View view = layoutInflater.inflate( R.layout.item_hotelhome, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.home_NameHotel.setText( hotelHomes.get( position ).getName_Hotel() );
        holder.home_AddressHotel.setText( hotelHomes.get( position ).getAddress_Hotel() );
        String Level_Hotel = String.valueOf( hotelHomes.get( position ).getLevel_Hotel() );
        holder.home_LevelHotel.setText( Level_Hotel+" sao" );
        Picasso.with(context).load( Api.api_link_img_hotel +hotelHomes.get( position).getImg_Hotel())
                .error(R.drawable.ic_launcher_background)
                .into(holder.home_ImgHotel);
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context, DesHotel.class );
                intent.putExtra("ID_Hotel", String.valueOf(hotelHomes.get( position ).getID_Hotel()));
                context.startActivity(intent);
            }
        } );
    }

    @Override
    public int getItemCount() {
        return hotelHomes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView home_ImgHotel;
        TextView home_NameHotel, home_LevelHotel, home_AddressHotel;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            home_ImgHotel = itemView.findViewById( R.id.home_ImgHotel );
            home_NameHotel = itemView.findViewById( R.id.home_NameHotel );
            home_LevelHotel = itemView.findViewById( R.id.home_LevelHotel );
            home_AddressHotel = itemView.findViewById( R.id.home_AddressHotel );

        }
    }
}
