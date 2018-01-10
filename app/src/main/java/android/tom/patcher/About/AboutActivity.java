package android.tom.patcher.About;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.tom.patcher.R;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {
Button contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        contact=(Button) findViewById(R.id.cobtn);

        contact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String[] TO = {"saltt2013@gmail.com"};


                Intent emailIntent=new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "feedback and suggestions");


                try{
                    startActivity(Intent.createChooser(emailIntent,"send mail..."));

                }
                catch (android.content.ActivityNotFoundException ex)
                {
                    Toast.makeText(getBaseContext(), "No email client installed.", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
}
