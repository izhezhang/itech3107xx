package au.edu.federation.itech3107.studentattendance30395572.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

import au.edu.federation.itech3107.studentattendance30395572.activity.DetailActivity;
import au.edu.federation.itech3107.studentattendance30395572.database.Database;
import au.edu.federation.itech3107.studentattendance30395572.database.Lesson;
import au.edu.federation.itech3107.studentattendance30395572.databinding.ItemLessonBinding;

public class LessonAdapter extends AbsAdapter<ItemLessonBinding, Lesson> {

    public LessonAdapter(List<Lesson> lessons) {
        super(lessons);
    }

    @Override
    protected VH<ItemLessonBinding> createVH(LayoutInflater from, ViewGroup parent) {
        return new VH<>(ItemLessonBinding.inflate(from, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH<ItemLessonBinding> holder, @SuppressLint("RecyclerView") int position) {
        Lesson lesson = data.get(position);
        holder.itemViewBinding.tvName.setText(lesson.name);
        holder.itemViewBinding.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.deleteLesson(lesson);
                remove(position);
            }
        }); holder.itemViewBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                context.startActivity(new Intent(context, DetailActivity.class).putExtra("lesson",lesson));
            }
        });
    }
}
