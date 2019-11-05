package com.textile.texttospeech;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CommandsViewModel extends AndroidViewModel {

    private LiveData<CommandsText> commandsTextLiveData;
    private CommandDao commandDao;

    public CommandsViewModel(@NonNull Application application) {
        super(application);

        commandDao = TextToSpeechDb.getInstance(application).commandDao();
    }

    public void insertCommandsText(CommandsText commandsText) {

        new InsertCommandToDb(commandDao).execute(commandsText);
    }

    public LiveData<List<CommandsText>> getAllCommands() {

        return commandDao.getAllCommands();
    }

    private class InsertCommandToDb extends AsyncTask<CommandsText, Void, Void> {

        CommandDao mCommandDao;

        private InsertCommandToDb(CommandDao commandDao) {
            mCommandDao = commandDao;
        }

        @Override
        protected Void doInBackground(CommandsText... commandsTexts) {

            mCommandDao.insertCommands(commandsTexts[0]);
            return null;
        }
    }

}