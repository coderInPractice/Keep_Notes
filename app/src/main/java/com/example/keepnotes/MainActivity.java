package com.example.keepnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.keepnotes.models.NotesDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NotesRecyclerAdapter madapter;
    FloatingActionButton add_fab;

    NotesDbHelper mainDB = new NotesDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.notes_recycler_view);
        add_fab = findViewById(R.id.add_fab);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Cursor mCursor = mainDB.getAllNotes();
        madapter = new NotesRecyclerAdapter(MainActivity.this ,mCursor);
        recyclerView.setAdapter(madapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int id = (int) viewHolder.itemView.getTag();
                mainDB.deleteNotesObjectFromDatabaseByID(id);
                madapter.swapCursor(mainDB.getAllNotes());
            }
        }).attachToRecyclerView(recyclerView);

    }

    public void fabClick(View view){
        Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        madapter.swapCursor(mainDB.getAllNotes());
    }
}