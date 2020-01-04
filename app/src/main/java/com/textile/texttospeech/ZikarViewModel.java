package com.textile.texttospeech;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ZikarViewModel extends AndroidViewModel {

    private LiveData<Zikar> commandsTextLiveData;
    private ZikarDao zikarDao;

    public ZikarViewModel(@NonNull Application application) {
        super(application);

        zikarDao = ZikarDb.getInstance(application).commandDao();
    }

    public void insertZikarText(Zikar zikar) {

        new InsertCommandToDb(zikarDao).execute(zikar);
    }

    public LiveData<List<Zikar>> getAllZikars() {

        return zikarDao.getAllZikars();
    }

    private class InsertCommandToDb extends AsyncTask<Zikar, Void, Void> {

        ZikarDao mZikarDao;

        private InsertCommandToDb(ZikarDao zikarDao) {
            mZikarDao = zikarDao;
        }

        @Override
        protected Void doInBackground(Zikar... zikars) {

            mZikarDao.insertZikar(zikars[0]);
            return null;
        }
    }

}