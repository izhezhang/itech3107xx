package au.edu.federation.itech3107.studentattendance30395572.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.List;

public class Lesson implements Serializable {
    public String name;
    public List<String> date;

    @SuppressLint("Range")
    public Lesson(Cursor cursor) {
        this.name = cursor.getString(cursor.getColumnIndex("name"));
        this.date = new Gson().fromJson(cursor.getString(cursor.getColumnIndex("date")), new TypeToken<List<String>>() {
        }.getType());

    }

    @SuppressLint("Range")
    public Lesson() {

    }

    public ContentValues toCV() {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("date", new Gson().toJson(date));
        return values;
    }
}
