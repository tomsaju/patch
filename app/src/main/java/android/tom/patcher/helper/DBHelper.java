package android.tom.patcher.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.tom.patcher.diary.Diary;
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


    public static final String TABLE_DIARY = "diary";

    public static final String DIARY_ID = "_id";
    public static final String DIARY_TITLE = "Title";
    public static final String DIARY_CONTENT = "Content";
    public static final String DIARY_DATE = "Date";
    public static final String DIARY_DAY = "day";
    public static final String DIARY_DAY_OF_WEEK = "dayOfWeek";
    public static final String DIARY_MONTH = "month";
    public static final String DIARY_YEAR = "year";

    //Table creation statements
    public static final String NOTES_TABLE_CREATE_STATEMENT = "CREATE TABLE "+TABLE_NOTES+" ( "+NOTE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            NOTE_TITLE +" TEXT ,"+
            NOTE_CONTENT + " TEXT ,"+
            NOTE_DEF_ID + " INTEGER ,"+
            NOTE_DATE + " TEXT );";

    public static final String DIARY_TABLE_CREATE_STATEMENT = "CREATE TABLE "+TABLE_DIARY+" ( "+DIARY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            DIARY_TITLE +" TEXT ,"+
            DIARY_CONTENT + " TEXT ,"+
            DIARY_DAY + " INTEGER ,"+
            DIARY_YEAR + " INTEGER ,"+
            DIARY_MONTH + " INTEGER ,"+
            DIARY_DAY_OF_WEEK + " TEXT );";


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
        sqLiteDatabase.execSQL(DIARY_TABLE_CREATE_STATEMENT);
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
///////////diary section

    //fields for table_notes
  //  public static final String TABLE_DIARY = "diary";




    public Cursor getAllDiaryCursor(){
        db = this.getWritableDatabase();

        Cursor cursor;
        cursor = db.query(TABLE_DIARY,null,null,null,null,null,null);

        return cursor;
    }

    public void insertintoDiaryTable(Diary diary, boolean newDiary){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if(!newDiary) {
            cv.put(DIARY_ID, diary.getId());
        }
        cv.put(DIARY_TITLE,diary.getTitle());
        cv.put(DIARY_CONTENT,diary.getContent());
        cv.put(DIARY_DAY,diary.getDay());
        cv.put(DIARY_DAY_OF_WEEK,diary.getDayOfWeek());
        cv.put(DIARY_MONTH,diary.getMonth());
        cv.put(DIARY_YEAR,diary.getYear());


        if(newDiary) {
            db.insert(TABLE_DIARY, null, cv);
        }else{
            db.update(TABLE_DIARY,cv,DIARY_ID+ " = "+diary.getId(),null);
        }

        db.close();
    }


    public Diary getDiaryForId(int id){
       Diary diary = new Diary();
        db = this.getWritableDatabase();
        Cursor cursor;

        cursor = db.query(TABLE_DIARY,null,DIARY_ID+" = "+id,null,null,null,null);

        if(cursor.getCount()>0){

            while(cursor.moveToNext()){
                String diaryContent = cursor.getString(cursor.getColumnIndexOrThrow(DIARY_CONTENT));
                String diaryTitle = cursor.getString(cursor.getColumnIndexOrThrow(DIARY_TITLE));
                int diaryId = cursor.getInt(cursor.getColumnIndexOrThrow(DIARY_ID));
                int  diaryDay = cursor.getInt(cursor.getColumnIndexOrThrow(DIARY_DAY));
                int diaryMonth = cursor.getInt(cursor.getColumnIndexOrThrow(DIARY_MONTH));
                int diaryYear = cursor.getInt(cursor.getColumnIndexOrThrow(DIARY_YEAR));
                String diaryDayofWeek = cursor.getString(cursor.getColumnIndexOrThrow(DIARY_DAY_OF_WEEK));

                diary.setTitle(diaryTitle);
                diary.setContent(diaryContent);
                diary.setDay(diaryDay);
                diary.setDayOfWeek(diaryDayofWeek);
                diary.setMonth(diaryMonth);
                diary.setId(diaryId);
                diary.setYear(diaryYear);
            }

        }
        return diary;
    }


}
