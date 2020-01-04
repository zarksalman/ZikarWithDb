package com.textile.texttospeech;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.textile.texttospeech.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView speakButton;
    private RecyclerView recyclerView;
    private ZikarViewModel zikarViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        zikarViewModel = ViewModelProviders.of(this).get(ZikarViewModel.class);

        setAdaptersData();
    }

    // Showing google speech input dialog

    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi speak something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    // Receiving speech input

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    Zikar zikar = new Zikar();
                    zikar.setZikar(result.get(0));

                    zikarViewModel.insertCommandsText(zikar);
                }
                break;
            }

        }
    }

    private void setAdaptersData() {

        zikarViewModel.getAllCommands().observe(this, new Observer<List<Zikar>>() {
            @Override
            public void onChanged(List<Zikar> zikars) {

                ZikarAdapter zikarAdapter = new ZikarAdapter(MainActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                zikarAdapter.setData(zikars);
                recyclerView.setAdapter(zikarAdapter);
                zikarAdapter.notifyDataSetChanged();
            }
        });
    }
}
