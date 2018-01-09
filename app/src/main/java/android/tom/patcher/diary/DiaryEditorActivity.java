package android.tom.patcher.diary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.tom.patcher.R;
import android.tom.patcher.Utility;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DiaryEditorActivity extends AppCompatActivity implements IDiaryEditorView {
    TextView day,month,year,dayOfWeek;
    EditText title,content;
    IDiaryEditorController diaryEditorController;
    int id;
    boolean newDiary;
    Diary diary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_editor);

        day = findViewById(R.id.dayNumber);
        month = findViewById(R.id.monthName);
        year = findViewById(R.id.yeartext);
        dayOfWeek = findViewById(R.id.dayofweek);
        title = findViewById(R.id.editTextTitle);
        content = findViewById(R.id.editTextContent);

        diaryEditorController = new DiaryEditorController(getBaseContext(),this);
        if(getIntent().getExtras()!=null){
            id=getIntent().getIntExtra("ID",0);
        }
        if(id!=0){
            newDiary = false;
        }else{
            newDiary = true;
        }





        if(newDiary){
            diary = new Diary();
            long currentTimeInMillis = Utility.getCurrentTimeInMillis();
            SimpleDateFormat dateFormat = new SimpleDateFormat(Utility.DEFAULT_DATE_FORMAT);
            Date date = new Date();
            try {
                 date = dateFormat.parse(Utility.getFormattedDate(currentTimeInMillis, Utility.DEFAULT_DATE_FORMAT));
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                int dayNum = date.getDate();
                String monthName = Utility.getMonthName(date.getMonth());
                int yearName = c.get(Calendar.YEAR);
                int dayOfWeekS = c.get(Calendar.DAY_OF_WEEK);
                diary.setYear(yearName);
                diary.setMonth(Integer.parseInt(Utility.getFormattedDate(currentTimeInMillis,"MM")));
                diary.setDay(dayNum);
                diary.setDayOfWeek(Utility.getDayOfWeek(dayOfWeekS));

                day.setText(String.valueOf(diary.getDay()));
                month.setText(monthName);
                year.setText(String.valueOf(diary.getYear()));
                dayOfWeek.setText(String.valueOf(diary.getDayOfWeek()));
            }catch(Exception e){
            e.printStackTrace();
            }

        }else {
            diary = diaryEditorController.getDiaryForId(String.valueOf(id));
            if(diary!=null) {
                title.setText(diary.getTitle());
                content.setText(diary.getContent());
                day.setText(String.valueOf(diary.getDay()));
                String monthName = Utility.getMonthName(diary.getMonth());
                month.setText(monthName);
                year.setText(String.valueOf(diary.getYear()));
                dayOfWeek.setText(String.valueOf(diary.getDayOfWeek()));
            }
        }


    }


    void saveDiary() {
        if (title.getText().toString() != null && !title.getText().toString().isEmpty() ||
                (content.getText().toString() != null && !content.getText().toString().isEmpty())) {

            String titleText = title.getText().toString();
            String contentText = content.getText().toString();
            if(newDiary) {
                Diary noteToSave = new Diary();
                noteToSave.setTitle(titleText);
                noteToSave.setContent(contentText);
               noteToSave.setDayOfWeek(dayOfWeek.getText().toString());
               noteToSave.setMonth(diary.getMonth());
               noteToSave.setYear(diary.getYear());
               noteToSave.setDay(diary.getDay());

                diaryEditorController.saveDiary(noteToSave, newDiary);
            }else{
                Diary noteToSave = new Diary();
                noteToSave.setTitle(titleText);
                noteToSave.setContent(contentText);
                noteToSave.setDayOfWeek(dayOfWeek.getText().toString());
                noteToSave.setMonth(diary.getMonth());
                noteToSave.setYear(diary.getYear());
                noteToSave.setDay(diary.getDay());
                diaryEditorController.saveDiary(noteToSave, newDiary);
            }
        }

    }
    @Override
    public void onDiaryInsertSuccess() {
    finish();
        Toast.makeText(this, "Diary Succesfully saved", Toast.LENGTH_SHORT).show();
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
                saveDiary();
                return true;
            case R.id.delete:
                Toast.makeText(getApplicationContext(),"delete", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
