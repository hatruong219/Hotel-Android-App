package com.sict.hotelapp.AdapterListBill;

public class ListBill {
    private int ID_Room;
    private String Img_Room;
    private String Name_Room;
    private String Kind_Room;
    private int Price_Room;
    private String Datein_Bill;
    private String Dateout_Bill;
    private int Total_Bill;
    private int NumberRoom_Bill;
    public ListBill(){}

    public ListBill(int ID_Room, String img_Room, String name_Room, String kind_Room, int price_Room, String datein_Bill, String dateout_Bill, int total_Bill, int numberRoom_Bill) {
        this.ID_Room = ID_Room;
        Img_Room = img_Room;
        Name_Room = name_Room;
        Kind_Room = kind_Room;
        Price_Room = price_Room;
        Datein_Bill = datein_Bill;
        Dateout_Bill = dateout_Bill;
        Total_Bill = total_Bill;
        NumberRoom_Bill = numberRoom_Bill;
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

    public int getPrice_Room() {
        return Price_Room;
    }

    public void setPrice_Room(int price_Room) {
        Price_Room = price_Room;
    }

    public String getDatein_Bill() {
        return Datein_Bill;
    }

    public void setDatein_Bill(String datein_Bill) {
        Datein_Bill = datein_Bill;
    }

    public String getDateout_Bill() {
        return Dateout_Bill;
    }

    public void setDateout_Bill(String dateout_Bill) {
        Dateout_Bill = dateout_Bill;
    }

    public int getTotal_Bill() {
        return Total_Bill;
    }

    public void setTotal_Bill(int total_Bill) {
        Total_Bill = total_Bill;
    }

    public int getNumberRoom_Bill() {
        return NumberRoom_Bill;
    }

    public void setNumberRoom_Bill(int numberRoom_Bill) {
        NumberRoom_Bill = numberRoom_Bill;
    }
}
