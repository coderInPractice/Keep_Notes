package com.example.keepnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.keepnotes.models.Notes;
import com.example.keepnotes.models.NotesDbHelper;

public class AddNoteActivity extends AppCompatActivity {

    EditText notes_title , notes_contents;
    Button addDB_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        notes_contents = findViewById(R.id.contents_et);
        notes_title  = findViewById(R.id.note_title_et);

        addDB_btn = findViewById(R.id.add_button);
        final NotesDbHelper notesDB = new NotesDbHelper(AddNoteActivity.this);

        addDB_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_et = notes_title.getText().toString();
                String content_et = notes_contents.getText().toString();

                if(title_et.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Empty note will no be saved. Enter note title!",Toast.LENGTH_LONG).show();
                }
                else{
                    Notes dbNotes = new Notes(0,title_et,content_et);

                    notesDB.addNotes(dbNotes);

                    Toast.makeText(AddNoteActivity.this,"Notes Added.",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
}