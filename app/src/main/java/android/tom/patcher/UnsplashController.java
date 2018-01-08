package android.tom.patcher;

import android.content.Context;
import android.tom.patcher.notes.IUnsplashController;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

/**
 * Created by tom.saju on 1/5/2018.
 */

public class UnsplashController implements IUnsplashController {
    private static final String TAG = "UnsplashController";
    IUnsplashView view;
    Context context;

    String url = "https://api.unsplash.com/photos/random/?client_id=664610f715790d9d6c02d96df30e2d9050338fa680994401c6368154d56fedc3";

    public UnsplashController(IUnsplashView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getTodaysImage() {
        AndroidNetworking.get(url)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                        Log.d(TAG, "onResponse: ");
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
