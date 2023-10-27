package au.edu.federation.itech3107.studentattendance30395572.adapter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public class VH<T extends ViewBinding> extends RecyclerView.ViewHolder {
    public T itemViewBinding;

    public VH(T itemViewBinding) {
        super(itemViewBinding.getRoot());
        this.itemViewBinding = itemViewBinding;
    }
}
