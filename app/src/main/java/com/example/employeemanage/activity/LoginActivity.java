package com.example.employeemanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.employeemanage.R;
import com.example.employeemanage.database.DBHelper;
import com.example.employeemanage.model.Account;

public class LoginActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 10;
    EditText loginText, passwordText;
    Button btnLogin;

    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginText = (EditText) findViewById(R.id.login);
        passwordText = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.buttonLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = loginText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();
                if( username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please fill username or password", Toast.LENGTH_SHORT).show();
                } else{
                    Account account = dbHelper.findAccountByUsername(username);
                    if(account == null){
                        Toast.makeText(LoginActivity.this, "Username not correct", Toast.LENGTH_SHORT).show();
                    } else if(!account.getPassWord().equals(password)) {
                        Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    } else{
                            if (account.getKind().equals(1)){
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(LoginActivity.this,AttendanceActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("object_account",account);
                                intent.putExtras(bundle);
                                startActivityForResult(intent,MY_REQUEST_CODE);
                            }
                        }
                    }
                }
        });


    }
}