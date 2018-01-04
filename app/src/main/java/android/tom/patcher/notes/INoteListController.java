package android.tom.patcher.notes;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by tom.saju on 1/4/2018.
 */

public interface INoteListController {

    ArrayList<Note> getAllNotes();
    Cursor getAllNotesCursor();
}
