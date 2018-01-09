package android.tom.patcher.diary;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.tom.patcher.R;
import android.tom.patcher.helper.DBHelper;
import android.tom.patcher.notes.NoteEditorActivity;
import android.tom.patcher.notes.NoteListActivity;
import android.tom.patcher.notes.NoteListAdapter;
import android.tom.patcher.notes.NoteListController;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DiaryListActivity extends AppCompatActivity implements IDiaryListView {
    ListView noteListView;
    IDiaryListController diaryListController;
    Cursor diaryCursor;
    DiaryListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);
        noteListView = (ListView) findViewById(R.id.listviewdiary);
    }


    @Override
    protected void onResume() {
        super.onResume();
        diaryListController = new DiaryListController(getBaseContext(),this);
        diaryCursor = diaryListController.getAllDiaryCursor();

        adapter = new DiaryListAdapter(this,diaryCursor);
        noteListView.setAdapter(adapter);
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                diaryCursor.moveToPosition(i);
                int id = diaryCursor.getInt(diaryCursor.getColumnIndexOrThrow(DBHelper.DIARY_ID));
                Intent editor = new Intent(DiaryListActivity.this,DiaryEditorActivity.class);
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
                startActivity(new Intent(DiaryListActivity.this, DiaryEditorActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}