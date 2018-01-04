package android.tom.patcher.notes;

import android.content.Context;
import android.database.Cursor;
import android.tom.patcher.R;
import android.tom.patcher.Utility;
import android.tom.patcher.helper.DBHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tom.saju on 1/3/2018.
 */

public class NoteListAdapter extends CursorAdapter {

    Context context;


    public NoteListAdapter(Context context, Cursor cursor) {
       super(context,cursor,0);
    }





    /*@Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         view = inflater.inflate(R.layout.notelistitemlayout,viewGroup,false);
        TextView title  =(TextView) view.findViewById(R.id.titleNote);
        TextView content = (TextView) view.findViewById(R.id.contentNote);
        TextView date = (TextView) view.findViewById(R.id.dateNote);

        title.setText(notelist.get(i).getTitle());
        content.setText(notelist.get(i).getContent());
        date.setText(notelist.get(i).getEdittedDate());

        return view;
    }*/

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.notelistitemlayout, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView title  =(TextView) view.findViewById(R.id.titleNote);
        TextView content = (TextView) view.findViewById(R.id.contentNote);
        TextView date = (TextView) view.findViewById(R.id.dateNote);

        String titleText = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NOTE_TITLE));
        String contentText = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NOTE_CONTENT));

        if(titleText.length()>30){
            title.setText(titleText.substring(0,30));
        }else{
            title.setText(titleText+" ");
        }

        if(contentText.length()>30){
            content.setText(contentText.substring(0,30).trim()+"...");
        }else{
            content.setText(contentText.trim());
        }

        //title.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NOTE_TITLE)));
        //content.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NOTE_CONTENT)));
        date.setText(Utility.getFormattedDate(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NOTE_DATE))),Utility.DEFAULT_DATE_FORMAT));
    }
}
