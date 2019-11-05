package com.textile.texttospeech;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CommandDao {

    @Query("SELECT * FROM commands")
    LiveData<List<CommandsText>> getAllCommands();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCommands(CommandsText... commandsTexts);

    @Delete
    void deleteCommand(CommandsText commandsText);


    @Query("DELETE from commands")
    void deleteAllCommands();

}