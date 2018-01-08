package android.tom.patcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.tom.patcher.notes.IUnsplashController;
import android.widget.ImageView;

public class UnsplashActivity extends AppCompatActivity implements IUnsplashView {
ImageView image;
IUnsplashController unsplashController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsplash);

        image = findViewById(R.id.imageview);
        unsplashController = new UnsplashController(this,this);

        unsplashController.getTodaysImage();

    }
}
