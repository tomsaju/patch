package android.tom.patcher.diary;

import android.content.Context;
import android.database.Cursor;
import android.tom.patcher.R;
import android.tom.patcher.Utility;
import android.tom.patcher.helper.DBHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by tom.saju on 1/9/2018.
 */

public class DiaryListAdapter extends CursorAdapter {

    Context context;

    public DiaryListAdapter(Context context, Cursor c) {
        super(context, c);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.diarylistitemlayout, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView day  =(TextView) view.findViewById(R.id.dayTV);
        TextView month = (TextView) view.findViewById(R.id.monthTV);
        TextView year = (TextView) view.findViewById(R.id.yearTV);

        int dayNumber = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.DIARY_DAY));
        String monthName = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.DIARY_MONTH));
        int yearNumber = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.DIARY_YEAR));

      day.setText(String.valueOf(dayNumber)+" "+Utility.getMonthName(Integer.parseInt(monthName)));
      month.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.DIARY_DAY_OF_WEEK)));
      year.setText(String.valueOf(yearNumber));

        //title.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NOTE_TITLE)));
        //content.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NOTE_CONTENT)));

    }
}
