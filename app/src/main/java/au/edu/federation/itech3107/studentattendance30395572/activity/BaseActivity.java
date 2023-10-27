package au.edu.federation.itech3107.studentattendance30395572.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import au.edu.federation.itech3107.studentattendance30395572.R;

/**
 * Encapsulated BaseActivity for easy use ViewBinding
 *
 * @param <T>
 */
public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {
    protected T viewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = getViewBinding();
        setContentView(viewBinding.getRoot());
        initListener();
        initData();
        View backBtn = findViewById(R.id.backBtn);
        if (backBtn != null) {
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }

    protected abstract T getViewBinding();


    protected abstract void initListener();

    protected abstract void initData();


}
