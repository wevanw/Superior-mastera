package com.example.lobster.superior.db;


import com.example.lobster.superior.model.Note;

import org.litepal.crud.DataSupport;

import java.util.List;


public class NoteLitepal {


    /**
     * add
     */
    public static void createNewNote(Note note) {
        Note newGroup = note;
        newGroup.save();
    }

    /**
     *
     *
     * @return
     */
    public static List<Note> queryNoteAll() {

        List<Note> noteList = DataSupport.findAll(Note.class);
        return noteList;
    }

}
