package com.textile.texttospeech;
/*
 * Created by SalmanHameed on 10/30/2019.
 */


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "commands")
public class CommandsText {

    @PrimaryKey
    @ColumnInfo(name = "id")
    Integer id;
    String command;

    public CommandsText() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
