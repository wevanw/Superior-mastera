package com.example.lobster.superior.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lobster.superior.R;
import com.example.lobster.superior.adapter.RecordAdapter;
import com.example.lobster.superior.db.NoteLitepal;
import com.example.lobster.superior.model.Note;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {

    private List<Note> noteList = new ArrayList<Note>();
    private RecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.record_recycler);
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        noteList = NoteLitepal.queryNoteAll();
        adapter = new RecordAdapter(this, noteList);
        recyclerView.setAdapter(adapter);
    }

}
