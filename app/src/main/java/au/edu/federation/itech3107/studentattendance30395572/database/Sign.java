package au.edu.federation.itech3107.studentattendance30395572.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

public class Sign {
    public int id;
    public String lessonName;
    public String stuId;
    public String date;

    public Sign() {
    }

    @SuppressLint("Range")
    public Sign(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex("id"));
        lessonName = cursor.getString(cursor.getColumnIndex("lessonName"));
        stuId = cursor.getString(cursor.getColumnIndex("stuId"));
        date = cursor.getString(cursor.getColumnIndex("date"));
    }

    public ContentValues toCV() {
        ContentValues values = new ContentValues();
        values.put("lessonName", lessonName);
        values.put("date", date);
        values.put("stuId", stuId);
        return values;
    }
}
