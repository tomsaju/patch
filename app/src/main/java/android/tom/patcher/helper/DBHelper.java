package android.tom.patcher.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.tom.patcher.model.Mood;
import android.tom.patcher.notes.Note;

import java.util.ArrayList;

/**
 * Created by tom.saju on 12/15/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    SQLiteDatabase db = this.getWritableDatabase();
    public static String DB_NAME = "playground_DB";
    public static int DB_VERSION = 1;

    //Table names
    public static String TABLE_NOTES = "Notes_Table";
    public static String TABLE_MOOD = "Mood_Table";

    //fields for table_notes
    public static final String NOTE_ID = "ID";
    public static final String NOTE_TITLE = "Title";
    public static final String NOTE_CONTENT = "Content";
    public static final String NOTE_DATE = "Date";
    public static final String NOTE_DEF_ID = "_id";


    //fields for table mood
    public static final String MOOD = "Mood";
    public static final String DATE = "Date";


    //Table creation statements
    public static final String NOTES_TABLE_CREATE_STATEMENT = "CREATE TABLE "+TABLE_NOTES+" ( "+NOTE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            NOTE_TITLE +" TEXT ,"+
            NOTE_CONTENT + " TEXT ,"+
            NOTE_DEF_ID + " INTEGER ,"+
            NOTE_DATE + " TEXT );";


    public static final String MOOD_TABLE_CREATE_STATEMENT = "CREATE TABLE "+TABLE_MOOD+" ( "+
            MOOD +" TEXT ,"+
            DATE + " INTEGER );";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NOTES_TABLE_CREATE_STATEMENT);

        sqLiteDatabase.execSQL(MOOD_TABLE_CREATE_STATEMENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<Note> getAllNotes(){
        db = this.getWritableDatabase();
        ArrayList<Note> notes = new ArrayList<>();
        Cursor cursor;
        cursor = db.query(TABLE_NOTES,null,null,null,null,null,null);
        if(cursor.moveToNext()){
            Note note = new Note();
            note.setId(cursor.getString(cursor.getColumnIndexOrThrow(NOTE_ID)));
            note.setContent(cursor.getString(cursor.getColumnIndexOrThrow(NOTE_CONTENT)));
            note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(NOTE_TITLE)));
            note.setEdittedDate(cursor.getString(cursor.getColumnIndexOrThrow(NOTE_DATE)));
            notes.add(note);
        }
        return notes;
    }

    public void saveMood(Mood mood){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MOOD,mood.getMood());
        cv.put(DATE,mood.getDate());
        db.insert(TABLE_MOOD, null, cv);
        db.close();
    }

    public Mood getMood(String date){
        db = this.getWritableDatabase();
        Mood mood = new Mood();
        Cursor cursor;
        cursor = db.query(TABLE_NOTES,null,DATE+"= "+date,null,null,null,null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                mood.setDate(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow(DATE))));
                mood.setMood(cursor.getString(cursor.getColumnIndexOrThrow(MOOD)));
            }
        }
        return mood;
    }

    public ArrayList<Mood> getAllMoodAfter(Long startDate){
        ArrayList<Mood> moodlist = new ArrayList<>();

        Cursor cursor;
        cursor = db.query(TABLE_NOTES,null,DATE+" >= "+startDate,null,null,null,null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                Mood mood = new Mood();
                mood.setDate(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow(DATE))));
                mood.setMood(cursor.getString(cursor.getColumnIndexOrThrow(MOOD)));
                moodlist.add(mood);
            }
        }
    return moodlist;
    }


    public Cursor getAllNotesCursor(){
        db = this.getWritableDatabase();

        Cursor cursor;
        cursor = db.query(TABLE_NOTES,null,null,null,null,null,null);

        return cursor;
    }

    public void insertintoNotesTable(Note note,boolean newNote){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NOTE_ID,note.getId());
        cv.put(NOTE_CONTENT,note.getContent());
        cv.put(NOTE_DATE,note.getEdittedDate());
        cv.put(NOTE_TITLE,note.getTitle());
        if(newNote) {
            db.insert(TABLE_NOTES, null, cv);
        }else{
            db.update(TABLE_NOTES,cv,NOTE_ID+ " = "+note.getId(),null);
        }

        db.close();
    }


    public Note getNoteForId(int id){
        Note note = new Note();
        db = this.getWritableDatabase();
        Cursor cursor;

        cursor = db.query(TABLE_NOTES,null,NOTE_ID+" = "+id,null,null,null,null);

        if(cursor.getCount()>0){

            while(cursor.moveToNext()){
                String noteContent = cursor.getString(cursor.getColumnIndexOrThrow(NOTE_CONTENT));
                String noteTitle = cursor.getString(cursor.getColumnIndexOrThrow(NOTE_TITLE));
                String noteId = cursor.getString(cursor.getColumnIndexOrThrow(NOTE_ID));
                String noteDate = cursor.getString(cursor.getColumnIndexOrThrow(NOTE_DATE));

                note.setTitle(noteTitle);
                note.setContent(noteContent);
                note.setEdittedDate(noteDate);
                note.setId(noteId);
            }

        }
        return note;
    }



}
