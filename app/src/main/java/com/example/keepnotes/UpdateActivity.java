package com.example.keepnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.keepnotes.models.NotesDbHelper;

public class UpdateActivity extends AppCompatActivity {

    EditText modify_title, modify_content;
    Button modify_btn;
    NotesDbHelper updateDB = new NotesDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        modify_title = findViewById(R.id.update_title_et);
        modify_content = findViewById(R.id.contents_update_et);

        modify_btn = findViewById(R.id.update_btn);

        Intent myIntent = getIntent();

        final int id = myIntent.getIntExtra("id",0);
        String note_title = myIntent.getStringExtra("note_title");
        String note_content = myIntent.getStringExtra("content");

        modify_title.setText(note_title);
        modify_content.setText(note_content);

        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = modify_title.getText().toString();
                String newContent = modify_content.getText().toString();
                updateDB.updateNote(id,newTitle,newContent);
                finish();
            }
        });
    }
}