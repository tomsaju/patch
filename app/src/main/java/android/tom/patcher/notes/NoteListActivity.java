package android.tom.patcher.notes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.tom.patcher.R;
import android.tom.patcher.helper.DBHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class NoteListActivity extends AppCompatActivity implements INoteListView {
ListView noteListView;
INoteListController noteListController;
Cursor noteCursor;
NoteListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        noteListView = (ListView) findViewById(R.id.listviewNotes);




    }


    @Override
    protected void onResume() {
        super.onResume();
        noteListController = new NoteListController(getBaseContext(),this);
        noteCursor = noteListController.getAllNotesCursor();

        adapter = new NoteListAdapter(this,noteCursor);
        noteListView.setAdapter(adapter);
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                noteCursor.moveToPosition(i);
                int id = noteCursor.getInt(noteCursor.getColumnIndexOrThrow(DBHelper.NOTE_ID));
                Intent editor = new Intent(NoteListActivity.this,NoteEditorActivity.class);
                editor.putExtra("ID",id);
                startActivity(editor);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notelist_menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNote:
                startActivity(new Intent(NoteListActivity.this,NoteEditorActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
