package au.edu.federation.itech3107.studentattendance30395572.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import au.edu.federation.itech3107.studentattendance30395572.database.Database;
import au.edu.federation.itech3107.studentattendance30395572.R;


public class RegisterActivity extends AppCompatActivity {
    private View backBtn;
    private EditText phoneEdit;
    private EditText pwdEdit;
    private TextView registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewsById();
        initListener();
    }

    private void initListener() {

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    /**
     * 注册方法
     */

    private void register() {
        String phone = phoneEdit.getText().toString().trim();
        String pwd = pwdEdit.getText().toString().trim();


        if (phone.isEmpty()) {
            phoneEdit.setError("Please enter the account number");
            return;
        }

        if (pwd.isEmpty()) {
            pwdEdit.setError("Please enter password");
            return;
        }
        if (!pwd.equals(pwdEdit.getText().toString())) {
            Toast.makeText(this, "The two passwords entered are inconsistent！", Toast.LENGTH_SHORT).show();
        }
        if (Database.register(phone, pwd)) {
            Toast.makeText(this, "Successful registration, please log in", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "The account has been registered.", Toast.LENGTH_SHORT).show();
        }
    }

    private void findViewsById() {
        backBtn = findViewById(R.id.backBtn);
        phoneEdit = findViewById(R.id.phoneEdit);
        pwdEdit = findViewById(R.id.pwdEdit);
        registerBtn = findViewById(R.id.registerBtn);
    }


}