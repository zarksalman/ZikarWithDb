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

@Database(entities = Zikar.class, version = 1)
public abstract class ZikarDb extends RoomDatabase {

    private static ZikarDb INSTANCE;
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

    public static ZikarDb getInstance(Context context) {
        if (INSTANCE == null) {
            appPreference = AppPreference.getInstance(context);
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ZikarDb.class, "text_to_speech")
                    .addCallback(sRoomDatabaseCallback)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public abstract ZikarDao commandDao();

}
