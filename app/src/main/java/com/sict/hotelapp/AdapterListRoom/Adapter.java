package com.sict.hotelapp.AdapterListRoom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;
import com.sict.hotelapp.Activity.DesRoom;
import com.sict.hotelapp.Api;
import com.sict.hotelapp.R;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    ArrayList<ListRoom> listRooms;

    public Adapter(Context context, ArrayList<ListRoom> listRooms) {
        this.context = context;
        this.listRooms = listRooms;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from( parent.getContext() );
        View view = layoutInflater.inflate( R.layout.item_listroom, parent, false );
        return new Adapter.ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###" );
        holder.listroom_NameRoom.setText( listRooms.get( position ).getName_Room() );

        String Comment_Room = String.valueOf( listRooms.get( position ).getComment_Room() );
        holder.listroom_Comment.setText( "( " + Comment_Room+" )" );

        holder.listroom_KindRoom.setText( listRooms.get( position ).getKind_Room() );


        String Price_Room = decimalFormat.format( listRooms.get( position ).getPrice_Room() );
        holder.listroom_PriceRoom.setText( "Giá phòng: "+Price_Room+" Vnđ" );

        holder.listroom_StarRoom.setRating(listRooms.get(position).getStar_Room());

        Picasso.with(context).load( Api.api_link_img_room +listRooms.get( position).getImg_Room())
                .error(R.drawable.ic_launcher_background)
                .into(holder.listroom_ImgRoom);
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context.getApplicationContext(), DesRoom.class );
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ID_Room", String.valueOf(listRooms.get( position ).getID_Room()));
                context.startActivity(intent);
            }
        } );
    }

    @Override
    public int getItemCount() {
        return listRooms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView listroom_ImgRoom;
        AppCompatRatingBar listroom_StarRoom;
        TextView listroom_NameRoom, listroom_Comment, listroom_KindRoom, listroom_PriceRoom;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            listroom_ImgRoom = itemView.findViewById( R.id.listroom_ImgRoom );
            listroom_StarRoom = itemView.findViewById( R.id.listroom_StarRoom );
            listroom_NameRoom = itemView.findViewById( R.id.listroom_NameRoom );
            listroom_Comment = itemView.findViewById( R.id.listroom_Comment );
            listroom_KindRoom = itemView.findViewById( R.id.listroom_KindRoom );
            listroom_PriceRoom = itemView.findViewById( R.id.listroom_PriceRoom );
        }
    }
}
