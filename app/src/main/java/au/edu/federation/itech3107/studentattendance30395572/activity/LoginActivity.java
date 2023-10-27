package au.edu.federation.itech3107.studentattendance30395572.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import au.edu.federation.itech3107.studentattendance30395572.database.Database;
import au.edu.federation.itech3107.studentattendance30395572.R;


public class LoginActivity extends AppCompatActivity {
    private EditText phoneEdit;
    private EditText pwdEdit;
    private TextView loginBtn;
    private ImageView backBtn;

    private TextView goRegisterBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        findViewsById();
        initListener();
    }

    private void initListener() {
        //Click to register
        goRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        //Click Login
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
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
     * Login Method
     */
    private void login() {
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
        if (Database.login(phone, pwd)) {
            Toast.makeText(this, "Login succeeded", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Incorrect account or password, please re-enter", Toast.LENGTH_SHORT).show();

        }
    }


    private void findViewsById() {
        phoneEdit = findViewById(R.id.phoneEdit);
        pwdEdit = findViewById(R.id.pwdEdit);
        loginBtn = findViewById(R.id.loginBtn);
        goRegisterBtn = findViewById(R.id.goRegisterBtn);
        backBtn = findViewById(R.id.backBtn);
    }

}