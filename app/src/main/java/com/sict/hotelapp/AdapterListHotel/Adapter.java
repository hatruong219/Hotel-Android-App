package com.sict.hotelapp.AdapterListHotel;

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
    ArrayList<ListHotel> listHotels;

    public Adapter(Context context, ArrayList<ListHotel> listHotels) {
        this.context = context;
        this.listHotels = listHotels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from( parent.getContext() );
        View view = layoutInflater.inflate( R.layout.item_listhotel, parent, false );
        return new Adapter.ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###" );
        holder.listhotel_NameHotel.setText( listHotels.get( position ).getName_Hotel() );
        holder.listhotel_AddressHotel.setText( listHotels.get( position ).getAddress_Hotel() );
        String Level_Hotel = String.valueOf( listHotels.get( position ).getLevel_Hotel() );
        holder.listhotel_LevelHotel.setText( Level_Hotel+" sao" );
        String Price_Hotel = decimalFormat.format( listHotels.get( position ).getLowPrice_Hotel() );
        holder.listhotel_PriceHotel.setText( "Giá từ: "+Price_Hotel+" Vnđ" );
        Picasso.with(context).load( Api.api_link_img_hotel +listHotels.get( position).getImg_Hotel())
                .error(R.drawable.ic_launcher_background)
                .into(holder.listhotel_ImgHotel);
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context.getApplicationContext(), DesHotel.class );
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ID_Hotel", String.valueOf(listHotels.get( position ).getID_Hotel()));
                context.startActivity(intent);
            }
        } );
    }

    @Override
    public int getItemCount() {return listHotels.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView listhotel_ImgHotel;
        TextView listhotel_NameHotel, listhotel_LevelHotel, listhotel_AddressHotel, listhotel_PriceHotel;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            listhotel_ImgHotel = itemView.findViewById( R.id.listhotel_ImgHotel );
            listhotel_NameHotel = itemView.findViewById( R.id.listhotel_NameHotel );
            listhotel_LevelHotel = itemView.findViewById( R.id.listhotel_LevelHotel );
            listhotel_AddressHotel = itemView.findViewById( R.id.listhotel_AddressHotel );
            listhotel_PriceHotel = itemView.findViewById( R.id.listhotel_PriceHotel );

        }
    }
}
