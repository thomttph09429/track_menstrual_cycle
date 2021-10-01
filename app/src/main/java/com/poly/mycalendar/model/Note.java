package com.poly.mycalendar.model;

public class Note {
    String  id;
    String dateNote;
    String typeOfNote;
    int positionItem;




    public Note(String id, String dateNote, String typeOfNote, int positionItem) {
        this.id = id;
        this.dateNote = dateNote;
        this.typeOfNote = typeOfNote;
        this.positionItem = positionItem;
    }

    public String getId() {
        return id;
    }


    public Note() {
    }



    public void setId(String id) {
        this.id = id;
    }

    public String getDateNote() {
        return dateNote;
    }

    public void setDateNote(String dateNote) {
        this.dateNote = dateNote;
    }

    public String getTypeOfNote() {
        return typeOfNote;
    }

    public void setTypeOfNote(String typeOfNote) {
        this.typeOfNote = typeOfNote;
    }

    public int getPositionItem() {
        return positionItem;
    }

    public void setPositionItem(int positionItem) {
        this.positionItem = positionItem;
    }

    public void getTitle(String title) {
    }
}
