package com.textile.texttospeech;
/*
 * Created by SalmanHameed on 10/30/2019.
 */

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = CommandsText.class, version = 1)
public abstract class TextToSpeechDb extends RoomDatabase {

    private static TextToSpeechDb INSTANCE;
    private static AppPreference appPreference;

    private static Callback sRoomDatabaseCallback = new Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };

    public static TextToSpeechDb getInstance(Context context) {
        if (INSTANCE == null) {
            appPreference = AppPreference.getInstance(context);
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TextToSpeechDb.class, "text_to_speech")
                    .addCallback(sRoomDatabaseCallback)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public abstract CommandDao commandDao();

}
