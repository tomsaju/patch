package android.tom.patcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.tom.patcher.notes.NoteListActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {
ListView mainList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        mainList = (ListView) findViewById(R.id.homeList);
        String[] indexList = new String[]{"Notes"};

        ArrayAdapter listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,indexList);
        mainList.setAdapter(listAdapter);

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        Intent noteavt = new Intent(MainActivity.this, NoteListActivity.class);
                        startActivity(noteavt);
                        break;

                    default:
                }
                // Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
