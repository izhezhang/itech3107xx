package au.edu.federation.itech3107.studentattendance30395572.activity;

import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

import au.edu.federation.itech3107.studentattendance30395572.database.Database;
import au.edu.federation.itech3107.studentattendance30395572.adapter.LessonAdapter;
import au.edu.federation.itech3107.studentattendance30395572.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    private LessonAdapter adapter = new LessonAdapter(new ArrayList<>());

    @Override
    protected void initListener() {
        viewBinding.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddLessonActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.addData(Database.getLesson());
    }

    @Override
    protected void initData() {
        viewBinding.rvLesson.setAdapter(adapter);
    }
}