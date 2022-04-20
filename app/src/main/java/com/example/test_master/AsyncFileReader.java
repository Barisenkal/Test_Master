package com.example.test_master;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class AsyncFileReader extends AsyncTask<Void, Void, String> {

    private final Context context;

    private final String filename;

    public AsyncFileReader(Context context, String filename) {
        this.context = context;
        this.filename = filename;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(Void... voids) {
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(filename);
        } catch (FileNotFoundException e) {
            return "";
        }
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            return "";
        }

        return stringBuilder.toString();
    }
}
