package com.textile.texttospeech;
/*
 * Created by SalmanHameed on 10/30/2019.
 */


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "commands")
public class Zikar {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;
    private String zikar;

    public Zikar() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommand() {
        return zikar;
    }

    public void setCommand(String command) {
        this.zikar = command;
    }
}
