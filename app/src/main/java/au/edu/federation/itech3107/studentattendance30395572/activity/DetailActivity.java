package au.edu.federation.itech3107.studentattendance30395572.activity;

import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import au.edu.federation.itech3107.studentattendance30395572.adapter.SignAdapter;
import au.edu.federation.itech3107.studentattendance30395572.database.Database;
import au.edu.federation.itech3107.studentattendance30395572.database.Lesson;
import au.edu.federation.itech3107.studentattendance30395572.database.Student;
import au.edu.federation.itech3107.studentattendance30395572.databinding.ActivityDetailBinding;
import au.edu.federation.itech3107.studentattendance30395572.databinding.DialogStudentBinding;

public class DetailActivity extends BaseActivity<ActivityDetailBinding> {


    @Override
    protected ActivityDetailBinding getViewBinding() {
        return ActivityDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initListener() {
        viewBinding.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogStudentBinding binding = DialogStudentBinding.inflate(getLayoutInflater());
                AlertDialog show = new AlertDialog.Builder(DetailActivity.this).setView(binding.getRoot()).show();
                binding.tvSure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = binding.etName.getText().toString();
                        String stuId = binding.etStuId.getText().toString();
                        if (name.isEmpty() || stuId.isEmpty()) {
                            return;
                        }
                        Student student = new Student();
                        student.stuId = stuId;
                        student.name = name;
                        student.lessonName = lesson.name;
                        if (Database.addStudent(student)) {
                            show.dismiss();
                            viewBinding.rvStudent.setAdapter(new SignAdapter(Database.getStudent(lesson), (String) viewBinding.spinner.getSelectedItem(), lesson.name));
                        } else {
                            Toast.makeText(DetailActivity.this, "无法添加此人已存在", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
        });
    }

    private Lesson lesson;

    @Override
    protected void initData() {
        lesson = (Lesson) getIntent().getSerializableExtra("lesson");
        viewBinding.tvTitle.setText(lesson.name);
        viewBinding.spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, lesson.date));
        viewBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewBinding.rvStudent.setAdapter(new SignAdapter(Database.getStudent(lesson), (String) viewBinding.spinner.getSelectedItem(), lesson.name));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewBinding.rvStudent.setAdapter(new SignAdapter(Database.getStudent(lesson), (String) viewBinding.spinner.getSelectedItem(), lesson.name));

    }
}