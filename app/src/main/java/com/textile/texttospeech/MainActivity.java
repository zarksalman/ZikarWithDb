package com.textile.texttospeech;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView voiceInput;
    private TextView speakButton;
    private RecyclerView recyclerView;
    private CommandDao commandDao;
    private CommandsViewModel commandsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_commands);
        speakButton = findViewById(R.id.btnSpeak);

        commandsViewModel = ViewModelProviders.of(this).get(CommandsViewModel.class);

        setAdaptersData();

        speakButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                askSpeechInput();
            }
        });

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

                    CommandsText commandsText = new CommandsText();
                    commandsText.setCommand(result.get(0));

                    commandsViewModel.insertCommandsText(commandsText);
                }
                break;
            }

        }
    }


    private void setAdaptersData() {

        commandsViewModel.getAllCommands().observe(this, new Observer<List<CommandsText>>() {
            @Override
            public void onChanged(List<CommandsText> commandsTexts) {

                CommandAdapter commandAdapter = new CommandAdapter(MainActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                commandAdapter.setData(commandsTexts);
                recyclerView.setAdapter(commandAdapter);
                commandAdapter.notifyDataSetChanged();
            }
        });
    }
}
