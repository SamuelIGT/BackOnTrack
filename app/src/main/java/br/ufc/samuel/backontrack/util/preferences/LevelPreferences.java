package br.ufc.samuel.backontrack.util.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import br.ufc.samuel.backontrack.R;

/**
 * Created by samue on 12/01/2018.
 */

public class LevelPreferences {
    private Context context;
    private SharedPreferences preferences;

    public LevelPreferences(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setLevelDefaults(String[] value) {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(context.getString(R.string.LV1_Preferences_Key), value[0]);
        editor.putString(context.getString(R.string.LV2_Preferences_Key), value[1]);
        editor.putString(context.getString(R.string.LV3_Preferences_Key), value[2]);
        editor.commit();
    }

/*    public void setExerciseVideosPath(String path){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(context.getString(R.string.exercise_videos_path), path);
    }
    public String getExerciseVideosPath(){
        return preferences.getString(context.getString(R.string.exercise_videos_path), null);
    }*/

    public void setDefaults(boolean isFirstTime){
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(context.getString(R.string.isFirstTime_Preferences_Key), isFirstTime);
        editor.commit();
    }

    public String[] getLevelStatusPreferences() {


        String[] lvStatus = new String[3];
        lvStatus[0] = preferences.getString(context.getString(R.string.LV1_Preferences_Key), null);
        lvStatus[1] = preferences.getString(context.getString(R.string.LV2_Preferences_Key), null);
        lvStatus[2] = preferences.getString(context.getString(R.string.LV3_Preferences_Key), null);

        return lvStatus;
    }

    public boolean isFirstTime(){
        boolean isFirstTime;
        isFirstTime = preferences.getBoolean(context.getString(R.string.isFirstTime_Preferences_Key), true);

        return isFirstTime;
    }

}
