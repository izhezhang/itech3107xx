package au.edu.federation.itech3107.studentattendance30395572.database;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import au.edu.federation.itech3107.studentattendance30395572.App;


public class Database extends SQLiteOpenHelper {
    private static Database database = new Database();

    public Database() {
        super(App.getContext(), "my_.db", null, 1);
    }

    public static void addLesson(Lesson lesson) {
        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        writableDatabase.insert("lesson", null, lesson.toCV());
    }

    public static List<Lesson> getLesson() {
        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from lesson", null);
        ArrayList<Lesson> lessons = new ArrayList<>();
        while (cursor.moveToNext()) {
            lessons.add(new Lesson(cursor));
        }
        return lessons;
    }

    public static void deleteLesson(Lesson lesson) {
        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        writableDatabase.delete("lesson", "name = ?", new String[]{lesson.name});
    }

    public static boolean addStudent(Student student) {
        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        return writableDatabase.insert("student", null, student.toCV()) > 0;
    }

    public static List<Student> getStudent(Lesson lesson) {
        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from student where lessonName = ?", new String[]{lesson.name});
        ArrayList<Student> students = new ArrayList<>();
        while (cursor.moveToNext()) {
            students.add(new Student(cursor));
        }
        return students;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS user(" + "account text PRIMARY KEY," + "password text)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS lesson(" + "name text PRIMARY KEY," + "date text)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS student(" + "stuId text PRIMARY KEY," + "name text,lessonName text)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS sign(" + "id integer PRIMARY KEY AUTOINCREMENT," + "stuId text," + "lessonName text," + "date text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * 注册
     *
     * @param account  注册的账号
     * @param password 注册的密码
     * @return 是否注册成功
     */
    public static boolean register(String account, String password) {
        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("SELECT * FROM user WHERE account = ?", new String[]{account});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                cursor.close();
                return false;
            }
        }
        writableDatabase.execSQL("INSERT INTO user(account,password) VALUES(?,?)", new Object[]{account, password});
        return true;
    }

    /**
     * 签到
     *
     * @param sign
     */
    public static void sign(Sign sign) {
        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        String selection = " stuId = ? and date = ? and lessonName = ?";
        String[] args = new String[]{sign.stuId, sign.date, sign.lessonName};
        if (writableDatabase.rawQuery("select * from sign where" + selection, args).moveToNext()) {
            writableDatabase.delete("sign", selection, args);
        } else {
            writableDatabase.insert("sign", null, sign.toCV());
        }
    }

    /**
     * 是否签到
     *
     * @param stuId
     * @param lessonName
     * @param date
     * @return
     */
    public static boolean hasSign(String stuId, String lessonName, String date) {
        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        String selection = " stuId = ? and date = ? and lessonName = ?";
        String[] args = new String[]{stuId, date, lessonName};
        return writableDatabase.rawQuery("select * from sign where" + selection, args).moveToNext();
    }

    /**
     * 登录
     *
     * @param account  登陆的账号
     * @param password 登陆的密码
     * @return 是否登录成功
     */
    @SuppressLint("Range")
    public static boolean login(String account, String password) {
        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("SELECT * FROM user WHERE account = ? and password", new String[]{account});
        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }


}
