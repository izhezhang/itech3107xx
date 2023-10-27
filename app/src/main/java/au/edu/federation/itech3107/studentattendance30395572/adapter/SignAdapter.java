package au.edu.federation.itech3107.studentattendance30395572.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import java.util.List;

import au.edu.federation.itech3107.studentattendance30395572.database.Database;
import au.edu.federation.itech3107.studentattendance30395572.database.Sign;
import au.edu.federation.itech3107.studentattendance30395572.database.Student;
import au.edu.federation.itech3107.studentattendance30395572.databinding.ItemStudentBinding;

public class SignAdapter extends AbsAdapter<ItemStudentBinding, Student> {
    private String date;

    public SignAdapter(List<Student> students, String date, String lessonName) {
        super(students);
        this.date = date;
        this.lessonName = lessonName;
    }

    private String lessonName;


    @Override
    protected VH<ItemStudentBinding> createVH(LayoutInflater from, ViewGroup parent) {
        return new VH<>(ItemStudentBinding.inflate(from, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VH<ItemStudentBinding> holder, int position) {
        Student student = data.get(position);
        holder.itemViewBinding.tvName.setText(student.name + "-" + student.stuId);
        holder.itemViewBinding.cbSign.setChecked(Database.hasSign(student.stuId, lessonName, date));
        holder.itemViewBinding.cbSign.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Sign sign = new Sign();
            sign.stuId = student.stuId;
            sign.lessonName = lessonName;
            sign.date = date;
            Database.sign(sign);
        });
    }
}
