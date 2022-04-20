package com.example.test_master;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.simple.JSONObject;

import java.util.concurrent.ExecutionException;


public class TerminBuchen extends AppCompatActivity {

    private static String FILE_NAME = "test_file_name.txt";
    private AsyncFileWriter fileWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termin_buchen);


        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer kosten = 0;
                CheckBox checkboxreifen = findViewById(R.id.checkBox);
                boolean ischeckboxreifenchecked = checkboxreifen.isChecked();

                CheckBox checkboxoelwechsel = findViewById(R.id.checkBox2);
                boolean ischeckboxoelwechselchecked = checkboxoelwechsel.isChecked();

                CheckBox checkboxflussigkeiten = findViewById(R.id.checkBox3);
                boolean ischeckboxflussigkeitenchecked = checkboxflussigkeiten.isChecked();

                CheckBox checkboxbremsen = findViewById(R.id.checkBox4);
                boolean ischeckboxbremsenchecked = checkboxbremsen.isChecked();

                if (ischeckboxreifenchecked) {
                    kosten = kosten + 60;
                }
                if (ischeckboxoelwechselchecked) {
                    kosten = kosten + 40;
                }
                if (ischeckboxflussigkeitenchecked) {
                    kosten = kosten + 45;
                }
                if (ischeckboxbremsenchecked) {
                    kosten = kosten + 80;
                }
                TextView kostenfeld = findViewById(R.id.textView21);
                kostenfeld.setText(kosten.toString());
            }
        });


        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer kosten = 0;
                CheckBox checkboxreifen = findViewById(R.id.checkBox);
                boolean ischeckboxreifenchecked = checkboxreifen.isChecked();

                CheckBox checkboxoelwechsel = findViewById(R.id.checkBox2);
                boolean ischeckboxoelwechselchecked = checkboxoelwechsel.isChecked();

                CheckBox checkboxflussigkeiten = findViewById(R.id.checkBox3);
                boolean ischeckboxflussigkeitenchecked = checkboxflussigkeiten.isChecked();

                CheckBox checkboxbremsen = findViewById(R.id.checkBox4);
                boolean ischeckboxbremsenchecked = checkboxbremsen.isChecked();

                TextView kostenfeld = findViewById(R.id.textView21);
                String servicekosten = kostenfeld.getText().toString();

                RadioButton radioButtonOnlinepayment = findViewById(R.id.radioButton_onlinepayment);
                boolean isradioButtonOnlinepaymentchecked = radioButtonOnlinepayment.isChecked();
                RadioButton radioButtonBarVorOrt = findViewById(R.id.radioButton_barvorort);
                boolean isradioButtonBarVorOrtchecked = radioButtonBarVorOrt.isChecked();

                TextView zusatz = findViewById(R.id.editTextTextMultiLine);
                String mehrangaben = zusatz.getText().toString();

                RadioButton radioButtondayone = findViewById(R.id.radioButton3);
                boolean isradioButtondayonechecked = radioButtondayone.isChecked();
                RadioButton radioButtondaytwo = findViewById(R.id.radioButton4);
                boolean isradioButtondaytwochecked = radioButtondaytwo.isChecked();
                RadioButton radioButtondaythree = findViewById(R.id.radioButton5);
                boolean isradioButtondaythreechecked = radioButtondaythree.isChecked();

                RadioButton radioButtontimeone = findViewById(R.id.radioButton);
                boolean isradioButtontimeonechecked = radioButtontimeone.isChecked();
                RadioButton radioButtontimetwo = findViewById(R.id.radioButton2);
                boolean isradioButtontimetwochecked = radioButtontimetwo.isChecked();
                RadioButton radioButtontimethree = findViewById(R.id.radioButton6);
                boolean isradioButtontimethreechecked = radioButtontimethree.isChecked();




                //TODO: SAVE ALL DATA INTO JSON

                JSONObject obj = new JSONObject();
                obj.put("Name", "Crunchify.com");

                fileWriter = new AsyncFileWriter(TerminBuchen.this, FILE_NAME);


                fileWriter.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, obj.toString(), String.valueOf(Context.MODE_APPEND));

                AsyncFileReader fileReader = new AsyncFileReader(TerminBuchen.this, FILE_NAME);
                try {
                    String data = fileReader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}