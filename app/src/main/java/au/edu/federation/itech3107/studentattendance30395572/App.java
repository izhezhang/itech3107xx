package au.edu.federation.itech3107.studentattendance30395572;

import android.app.Application;
import android.content.Context;


public class App extends Application {
    static App context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

    }

    public static Context getContext() {
        return context;
    }


}
