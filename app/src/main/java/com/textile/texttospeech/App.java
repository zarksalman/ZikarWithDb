package com.textile.texttospeech;
/*
 * Created by SalmanHameed on 10/30/2019.
 */

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TextToSpeechDb.getInstance(getApplicationContext());

    }
}
