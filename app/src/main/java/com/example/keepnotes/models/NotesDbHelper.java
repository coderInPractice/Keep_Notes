package com.example.keepnotes.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NotesDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "notes_db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "notes_table";
    public static final String NOTES_ID = "id";
    public static final String NOTES_TITLE_KEY = "title";
    public static final String NOTES_CONTENT_KEY = "contents";

    public NotesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_query = "create table " + TABLE_NAME + "(" +
                NOTES_ID + " integer primary key autoincrement, " +
                NOTES_TITLE_KEY + " text, " +
                NOTES_CONTENT_KEY + " text " + ")";

        db.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_NAME);
        onCreate(db);
    }

    public void addNotes(Notes notes){
        SQLiteDatabase db = getWritableDatabase();
        String insert_notes = "insert into " + TABLE_NAME +
                " values(null, '" + notes.getNotesTitle()
                + "', '" + notes.getNotesContents() +
                "')";

        db.execSQL(insert_notes);
        db.close();
    }

    public Cursor getAllNotes(){
        SQLiteDatabase db = getWritableDatabase();
        String get_all_query = "select * from " + TABLE_NAME;
        //db.close();
        return db.rawQuery(get_all_query,null);
    }

    public void updateNote(int id, String noteTitle, String noteContent){
        SQLiteDatabase db = getWritableDatabase();
        String modifyMartialArtSQLCommand = "update " + TABLE_NAME +
                " set " + NOTES_TITLE_KEY + " = '" + noteTitle +
                "', " + NOTES_CONTENT_KEY + " = '" + noteContent +
                "' " + "where " + NOTES_ID + " = " + id;
        db.execSQL(modifyMartialArtSQLCommand);
        db.close();
    }

    public void deleteNotesObjectFromDatabaseByID(int id) {


        SQLiteDatabase db = getWritableDatabase();
        String deleteMartialArtSQLCommand = "delete from " + TABLE_NAME +
                " where " + NOTES_ID + " = " + id;
        db.execSQL(deleteMartialArtSQLCommand);
        db.close();

    }
}
