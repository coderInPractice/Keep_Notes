package com.example.keepnotes.models;

public class Notes {

    private int notesID;
    private String notesTitle;
    private String notesContents;

    public Notes(int id,String notesTitle, String notesContents) {
        notesID = id;
        this.notesTitle = notesTitle;
        this.notesContents = notesContents;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public String getNotesContents() {
        return notesContents;
    }

    public void setNotesContents(String notesContents) {
        this.notesContents = notesContents;
    }

    public int getNotesID() {
        return notesID;
    }

    public void setNotesID(int notesID) {
        this.notesID = notesID;
    }
}
