package android.tom.patcher.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.tom.patcher.MainActivity;
import android.tom.patcher.R;
import android.tom.patcher.Utility;

/**
 * Created by tom.saju on 1/11/2018.
 */

public class QuoteUpdaterService extends Service {

    private static final int NOTIFICATION_ID = 8; //Quote notification ID
    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent,startId);

        Context context = this.getApplicationContext();
        //Context mContext = getApplicationContext();
        SharedPreferences mPrefs = context.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
        String lastUpdated = mPrefs.getString("quotetime", "00:00");

        long currentTime = System.currentTimeMillis();
        String current = Utility.getFormattedDate(currentTime, "HH:mm");
        //check if last updated day is today. if yes get quote index from shared prefs and get quote from DB.
        //if not, get quote index from prefs, increase by 1 (if not Maximum,) and get quote from DB . save current millis and index
        //in prefs




      /*  SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(eulaKey, true);
        editor.commit();*/

        notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(this, MainActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Today's Quote");
        builder.setContentText("make no spleing mistuke");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

}
