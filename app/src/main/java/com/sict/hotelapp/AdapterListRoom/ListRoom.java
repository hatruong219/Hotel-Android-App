package com.sict.hotelapp.AdapterListRoom;

public class ListRoom {
    private int ID_Room;
    private String Img_Room;
    private String Name_Room;
    private String Kind_Room;
    private int Empty_Room;
    private int Price_Room;
    private int Star_Room;
    private int Comment_Room;

    public ListRoom(){}

    public ListRoom(int ID_Room, String img_Room, String name_Room, String kind_Room, int empty_Room, int price_Room, int star_Room, int comment_Room) {
        this.ID_Room = ID_Room;
        Img_Room = img_Room;
        Name_Room = name_Room;
        Kind_Room = kind_Room;
        Empty_Room = empty_Room;
        Price_Room = price_Room;
        Star_Room = star_Room;
        Comment_Room = comment_Room;
    }

    public int getID_Room() {
        return ID_Room;
    }

    public void setID_Room(int ID_Room) {
        this.ID_Room = ID_Room;
    }

    public String getImg_Room() {
        return Img_Room;
    }

    public void setImg_Room(String img_Room) {
        Img_Room = img_Room;
    }

    public String getName_Room() {
        return Name_Room;
    }

    public void setName_Room(String name_Room) {
        Name_Room = name_Room;
    }

    public String getKind_Room() {
        return Kind_Room;
    }

    public void setKind_Room(String kind_Room) {
        Kind_Room = kind_Room;
    }

    public int getEmpty_Room() {
        return Empty_Room;
    }

    public void setEmpty_Room(int empty_Room) {
        Empty_Room = empty_Room;
    }

    public int getPrice_Room() {
        return Price_Room;
    }

    public void setPrice_Room(int price_Room) {
        Price_Room = price_Room;
    }

    public int getStar_Room() {
        return Star_Room;
    }

    public void setStar_Room(int star_Room) {
        Star_Room = star_Room;
    }

    public int getComment_Room() {
        return Comment_Room;
    }

    public void setComment_Room(int comment_Room) {
        Comment_Room = comment_Room;
    }
}
