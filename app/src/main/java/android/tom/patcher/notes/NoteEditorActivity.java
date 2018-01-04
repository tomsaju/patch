package android.tom.patcher.notes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.tom.patcher.R;
import android.tom.patcher.Utility;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteEditorActivity extends AppCompatActivity implements INoteEditorView {

    EditText title,content;
    Note note;
    int id;
    INoteEditorController noteEditorController;
    boolean newNote = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);


        title = (EditText) findViewById(R.id.noteTitle);
        content = (EditText) findViewById(R.id.noteContent);
        noteEditorController = new NoteEditorController(getBaseContext(),this);


        if(getIntent().getExtras()!=null){
            id=getIntent().getIntExtra("ID",0);
        }
        if(id!=0){
            newNote = false;
        }else{
            newNote = true;
        }

        note = noteEditorController.getNoteForId(String.valueOf(id));

        title.setText(note.getTitle());
        content.setText(note.getContent());



    }
    void saveNote() {
        if (title.getText().toString() != null && !title.getText().toString().isEmpty() ||
                (content.getText().toString() != null && !content.getText().toString().isEmpty())) {

            String titleText = title.getText().toString();
            String contentText = content.getText().toString();
            if(newNote) {
                Note noteToSave = new Note();
                noteToSave.setTitle(titleText);
                noteToSave.setContent(contentText);
                noteToSave.setEdittedDate(String.valueOf(Utility.getCurrentTimeInMillis()));
                noteEditorController.saveNote(noteToSave, newNote);
            }else{
                note.setTitle(titleText);
                note.setContent(contentText);
                note.setEdittedDate(String.valueOf(Utility.getCurrentTimeInMillis()));
                noteEditorController.saveNote(note, newNote);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.note_menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveNote();
                return true;
            case R.id.delete:
                Toast.makeText(getApplicationContext(),"delete", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onNoteInsertSuccess() {
        finish();
        Toast.makeText(this, "Note Saved.", Toast.LENGTH_SHORT).show();
    }
}
