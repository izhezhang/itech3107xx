package au.edu.federation.itech3107.studentattendance30395572.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import au.edu.federation.itech3107.studentattendance30395572.database.Database;
import au.edu.federation.itech3107.studentattendance30395572.database.Lesson;
import au.edu.federation.itech3107.studentattendance30395572.databinding.ActivityAddLessonBinding;

public class AddLessonActivity extends BaseActivity<ActivityAddLessonBinding> {
    @Override
    protected ActivityAddLessonBinding getViewBinding() {
        return ActivityAddLessonBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //Initialize the date picker and set the date change listener
        viewBinding.dpStart.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        });

        viewBinding.tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd E",Locale.US);
                String name = viewBinding.etName.getText().toString();
                List<String> date = new ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    date.add(format.format(calendar.getTime()));
                    calendar.add(Calendar.DAY_OF_WEEK, 7);
                }
                if (name.isEmpty()) {
                    Toast.makeText(AddLessonActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                    return;
                }
                Lesson lesson = new Lesson();
                lesson.name = name;
                lesson.date = date;
                Database.addLesson(lesson);
            }
        });
    }
}
