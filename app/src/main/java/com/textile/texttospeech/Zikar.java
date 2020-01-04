package com.textile.texttospeech;
/*
 * Created by SalmanHameed on 10/30/2019.
 */


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "zikar")
public class Zikar {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;
    private String zikar;
    private String count;
    private String date;

    public Zikar() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZikar() {
        return zikar;
    }

    public void setZikar(String zikar) {
        this.zikar = zikar;
    }

    public String getCount() {
        return count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
