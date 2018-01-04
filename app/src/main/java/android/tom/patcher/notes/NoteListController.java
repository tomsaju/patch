package android.tom.patcher.notes;

import android.content.Context;
import android.database.Cursor;
import android.tom.patcher.helper.DBHelper;

import java.util.ArrayList;

/**
 * Created by tom.saju on 1/4/2018.
 */

public class NoteListController implements INoteListController {
    Context context;
    INoteListView view;

    public NoteListController(Context context, INoteListView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public ArrayList<Note> getAllNotes() {
        DBHelper dbHelper = new DBHelper(context,DBHelper.DB_NAME,null,DBHelper.DB_VERSION);
        return dbHelper.getAllNotes();
    }

    @Override
    public Cursor getAllNotesCursor() {
        DBHelper dbHelper = new DBHelper(context,DBHelper.DB_NAME,null,DBHelper.DB_VERSION);
        return dbHelper.getAllNotesCursor();
    }
}
