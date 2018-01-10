package android.tom.patcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.tom.patcher.diary.DiaryEditorActivity;
import android.tom.patcher.diary.DiaryListActivity;
import android.tom.patcher.settings.SettingsActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.androidnetworking.AndroidNetworking;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {
ListView mainList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        mainList = (ListView) findViewById(R.id.homeList);
        String[] indexList = new String[]{"Notes","Unsplash","Diary","Settings"};
        AndroidNetworking.initialize(getApplicationContext());

        ArrayAdapter listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,indexList);
        mainList.setAdapter(listAdapter);

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        Intent noteavt = new Intent(MainActivity.this, DiaryEditorActivity.class);
                        startActivity(noteavt);
                        break;
                    case 1:
                        Intent notevt = new Intent(MainActivity.this, UnsplashActivity.class);
                        startActivity(notevt);
                        break;
                    case 2:
                        Intent diaryavt = new Intent(MainActivity.this, DiaryListActivity.class);
                        startActivity(diaryavt);
                        break;
                    case 3:
                        Intent settavt = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(settavt);
                    default:
                }
                // Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
