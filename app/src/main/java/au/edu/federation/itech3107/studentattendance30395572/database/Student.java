package au.edu.federation.itech3107.studentattendance30395572.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

public class Student {
    public String name;
    public String stuId;
    public String lessonName;

    @SuppressLint("Range")
    public Student(Cursor cursor) {
        name = cursor.getString(cursor.getColumnIndex("name"));
        stuId = cursor.getString(cursor.getColumnIndex("stuId"));
        lessonName = cursor.getString(cursor.getColumnIndex("lessonName"));
    }

    public Student() {

    }

    public ContentValues toCV() {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("stuId", stuId);
        values.put("lessonName", lessonName);
        return values;
    }
}
