package android.tom.patcher.settings;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.tom.patcher.R;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_settings2);

        getFragmentManager().beginTransaction().replace(android.R.id.content,new PrefFragment()).commit();
    }

    public static class PrefFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener,Preference.OnPreferenceClickListener{

        Context context;
        SharedPreferences sharedPreferences;



        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            context = getActivity();
            addPreferencesFromResource(R.xml.preferences);
        }




        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen()
                    .getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
            sharedPreferences = getPreferenceScreen().getSharedPreferences();
            Preference quote_notification_time_preference = findPreference("quote_notification_time");
            quote_notification_time_preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Toast.makeText(context, "CLicked on item", Toast.LENGTH_SHORT).show();
                    ShowTimePicker(preference.getKey());
                return true;
                }
            });

            Preference query_notification_time_preference = findPreference("query_notification_time");
            query_notification_time_preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Toast.makeText(context, "CLicked on item", Toast.LENGTH_SHORT).show();
                    ShowTimePicker(preference.getKey());
                    return true;
                }
            });

        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen()
                    .getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }


        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("show_daily_quotes")){
                Preference showQuotePreference = findPreference(key);
                //showQuotePreference.setSummary(sharedPreferences.getBoolean(key,true));
                if(sharedPreferences.getBoolean(key,true)){
                    Toast.makeText(context, "Selected", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Unselected", Toast.LENGTH_SHORT).show();
                }
            }else if(key.equalsIgnoreCase("daily_mood_query")){
                if(sharedPreferences.getBoolean(key,true)){
                    Toast.makeText(context, "Selected", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Unselected", Toast.LENGTH_SHORT).show();
                }
            }else if(key.equalsIgnoreCase("query_notification_time")){
               // ShowTimePicker(sharedPreferences,key);
            }else if(key.equalsIgnoreCase("set_password_for_mystuff")){
                openSetPasswordPage();
            }
        }

        private void openSetPasswordPage() {

        }

        private void ShowTimePicker(String key) {

            if(key.equalsIgnoreCase("quote_notification_time")){
                String time =  sharedPreferences.getString("quote_time","08:00");
                String[] timeString = time.split ( ":" );
                int hour = Integer.parseInt ( timeString[0].trim() );
                int min = Integer.parseInt ( timeString[1].trim() );
                //Calendar mcurrentTime = Calendar.getInstance();
                //int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                //int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String selectedTime = selectedHour+":"+selectedMinute;
                       sharedPreferences.edit().putString("quote_time",selectedTime).commit();
                    }
                }, hour, min, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }else if(key.equalsIgnoreCase("query_notification_time")){
                String time =  sharedPreferences.getString("query_time","21:00");
                String[] timeString = time.split ( ":" );
                int hour = Integer.parseInt ( timeString[0].trim() );
                int min = Integer.parseInt ( timeString[1].trim() );
                //Calendar mcurrentTime = Calendar.getInstance();
                //int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                //int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String selectedTime = selectedHour+":"+selectedMinute;
                        sharedPreferences.edit().putString("query_time",selectedTime).commit();
                    }
                }, hour, min, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }


        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            String key = preference.getKey();
             if(key.equals("quote_notification_time")){
               // ShowTimePicker(sharedPreferences,key);
            }
        return true;
        }
    }
}


