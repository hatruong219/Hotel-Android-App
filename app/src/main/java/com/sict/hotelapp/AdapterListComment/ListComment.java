package com.sict.hotelapp.AdapterListComment;

public class ListComment {
    String NameUserComment;
    String DateComment;
    String ContentComment;
    String ImgUserComment;
    public ListComment(){}

    public ListComment(String nameUserComment, String dateComment, String contentComment, String imgUserComment) {
        NameUserComment = nameUserComment;
        DateComment = dateComment;
        ContentComment = contentComment;
        ImgUserComment = imgUserComment;
    }

    public String getNameUserComment() {
        return NameUserComment;
    }

    public void setNameUserComment(String nameUserComment) {
        NameUserComment = nameUserComment;
    }

    public String getDateComment() {
        return DateComment;
    }

    public void setDateComment(String dateComment) {
        DateComment = dateComment;
    }

    public String getContentComment() {
        return ContentComment;
    }

    public void setContentComment(String contentComment) {
        ContentComment = contentComment;
    }

    public String getImgUserComment() {
        return ImgUserComment;
    }

    public void setImgUserComment(String imgUserComment) {
        ImgUserComment = imgUserComment;
    }
}
