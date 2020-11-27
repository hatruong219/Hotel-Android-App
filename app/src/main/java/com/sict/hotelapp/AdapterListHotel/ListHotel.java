package com.sict.hotelapp.AdapterListHotel;

public class ListHotel {
    private int ID_Hotel;
    private String Img_Hotel;
    private String Name_Hotel;
    private String Address_Hotel;
    private int Level_Hotel;
    private int LowPrice_Hotel;

    public ListHotel(){}
    public ListHotel(int ID_Hotel, String img_Hotel, String name_Hotel, String address_Hotel, int level_Hotel, int lowPrice_Hotel) {
        this.ID_Hotel = ID_Hotel;
        Img_Hotel = img_Hotel;
        Name_Hotel = name_Hotel;
        Address_Hotel = address_Hotel;
        Level_Hotel = level_Hotel;
        LowPrice_Hotel = lowPrice_Hotel;
    }

    public int getID_Hotel() {
        return ID_Hotel;
    }

    public void setID_Hotel(int ID_Hotel) {
        this.ID_Hotel = ID_Hotel;
    }

    public String getImg_Hotel() {
        return Img_Hotel;
    }

    public void setImg_Hotel(String img_Hotel) {
        Img_Hotel = img_Hotel;
    }

    public String getName_Hotel() {
        return Name_Hotel;
    }

    public void setName_Hotel(String name_Hotel) {
        Name_Hotel = name_Hotel;
    }

    public String getAddress_Hotel() {
        return Address_Hotel;
    }

    public void setAddress_Hotel(String address_Hotel) {
        Address_Hotel = address_Hotel;
    }

    public int getLevel_Hotel() {
        return Level_Hotel;
    }

    public void setLevel_Hotel(int level_Hotel) {
        Level_Hotel = level_Hotel;
    }

    public int getLowPrice_Hotel() {
        return LowPrice_Hotel;
    }

    public void setLowPrice_Hotel(int lowPrice_Hotel) {
        LowPrice_Hotel = lowPrice_Hotel;
    }
}
