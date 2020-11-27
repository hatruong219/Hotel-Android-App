package com.sict.hotelapp.AdapterListBill;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sict.hotelapp.Activity.DesRoom;
import com.sict.hotelapp.Api;
import com.sict.hotelapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    ArrayList<ListBill> listBills;

    public Adapter(Context context, ArrayList<ListBill> listBills) {
        this.context = context;
        this.listBills = listBills;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from( parent.getContext() );
        View view = layoutInflater.inflate( R.layout.itembillofme , parent, false  );
        return new Adapter.ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###" );
        holder.Name_Room.setText( listBills.get( position ).getName_Room() );
        holder.Kind_Room.setText( listBills.get( position ).getKind_Room() );
        holder.Datein_Bill.setText( listBills.get( position ).getDatein_Bill() );
        holder.Dateout_Bill.setText( listBills.get( position ).getDateout_Bill() );

        String num = String.valueOf( listBills.get( position ).getNumberRoom_Bill() );
        holder.NumberRoom_Bill.setText( num );

        String Price_Room = decimalFormat.format( listBills.get( position ).getPrice_Room() );
        holder.Price_Room.setText( "Giá phòng: "+Price_Room+" Vnđ" );

        String Total = decimalFormat.format( listBills.get( position ).getTotal_Bill() );
        holder.Total_Bill.setText( Total+" Vnđ" );

        Picasso.with(context).load( Api.api_link_img_room +listBills.get( position).getImg_Room())
                .error(R.drawable.ic_launcher_background)
                .into(holder.Img_Room);
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context.getApplicationContext(), DesRoom.class );
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ID_Room", String.valueOf(listBills.get( position ).getID_Room()));
                context.startActivity(intent);
            }
        } );
    }

    @Override
    public int getItemCount() {
        return listBills.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Img_Room;
        TextView Name_Room, Kind_Room, Price_Room, Datein_Bill, Dateout_Bill, Total_Bill, NumberRoom_Bill;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            Img_Room = (ImageView) itemView.findViewById( R.id.bill_ImgRoom );
            Name_Room = (TextView) itemView.findViewById( R.id.bill_NameRoom );
            Kind_Room = (TextView) itemView.findViewById( R.id.bill_KindRoom );
            Price_Room = (TextView) itemView.findViewById( R.id.bill_PriceRoom );
            Datein_Bill = (TextView) itemView.findViewById( R.id.bill_Datein );
            Dateout_Bill = (TextView) itemView.findViewById( R.id.bill_Dateout );
            Total_Bill = (TextView) itemView.findViewById( R.id.bill_Total );
            NumberRoom_Bill = (TextView) itemView.findViewById( R.id.bill_NumberRoom );

        }
    }
}
