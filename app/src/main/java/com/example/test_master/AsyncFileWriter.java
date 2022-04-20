package com.example.test_master;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AsyncFileWriter extends AsyncTask<String, Void, Boolean> {

    private final Context context;

    private final String filename;

    public AsyncFileWriter(Context context, String filename) {
        this.context = context;
        this.filename = filename;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Boolean doInBackground(String... params) {
        String fileContents = params[0];
        String mode = params[1];
        try (FileOutputStream fos = context.openFileOutput(filename, Integer.parseInt(mode))) {
            fos.write(fileContents.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
