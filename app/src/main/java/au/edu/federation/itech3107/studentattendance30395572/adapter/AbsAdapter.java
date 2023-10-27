package au.edu.federation.itech3107.studentattendance30395572.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.List;

/**
 * 封装的抽象Adapter 简化Adapter的使用
 *
 * @param <T>
 * @param <Data>
 */
public abstract class AbsAdapter<T extends ViewBinding, Data> extends RecyclerView.Adapter<VH<T>> {
    public AbsAdapter(List<Data> data) {
        this.data = data;
    }

    public List<Data> data;
    //添加数据
    public void addData(List<Data> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
    //移除数据
    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public VH<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return createVH(LayoutInflater.from(parent.getContext()), parent);
    }

    protected abstract VH<T> createVH(LayoutInflater from, ViewGroup parent);


    @Override
    public int getItemCount() {
        return data.size();
    }
}
