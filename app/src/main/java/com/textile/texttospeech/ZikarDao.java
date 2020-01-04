package com.textile.texttospeech;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ZikarDao {

    @Query("SELECT * FROM Zikar")
    LiveData<List<Zikar>> getAllCommands();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCommands(Zikar... zikars);

    @Delete
    void deleteCommand(Zikar zikar);


    @Query("DELETE from Zikar")
    void deleteAllCommands();

}