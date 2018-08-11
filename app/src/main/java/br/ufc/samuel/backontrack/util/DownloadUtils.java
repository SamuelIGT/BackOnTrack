package br.ufc.samuel.backontrack.util;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;

/**
 * Created by samuel on 7/17/18.
 */

public class DownloadUtils {

    public static File getParentFile(@NonNull Context context, String finalPath) {
        final File saveDir;

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            String path = context.getExternalCacheDirs()[0].getAbsolutePath()+ finalPath;
            saveDir = new File(path);
            Log.d("ExternalParentFilePath:", "" + path);
        }else{
            String path = context.getFilesDir().getAbsolutePath()+ finalPath;
            saveDir = new File(path);
            Log.d("InternalParentFilePath:", "" + path);
        }

        return saveDir;
    }

    /*private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }*/

}
