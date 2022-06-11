package com.example.employeemanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeemanage.R;
import com.example.employeemanage.adapter.HomeAdapter;
import com.example.employeemanage.database.DBHelper;
import com.example.employeemanage.model.Account;
import com.example.employeemanage.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private ListView listView;
    private EditText edtSearch;
    private ImageButton btnRevenue,btnAdd,btnLogout,btnLoadHours;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbHelper = new DBHelper(this);

        edtSearch = findViewById(R.id.edt_search);
        listView = findViewById(R.id.list_employee);
        btnRevenue = findViewById(R.id.btn_revenue);
        btnAdd = findViewById(R.id.btn_add_employee);
        btnLogout = findViewById(R.id.btn_logout);
        btnLoadHours = findViewById(R.id.btn_loadHours);

        loadData();
        ArrayList<Employee> employee = dbHelper.getAllEmployee();
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    //Logic search
                    String strKeyWord = edtSearch.getText().toString().trim();
                    List<Account> accounts = dbHelper.searchAccount(strKeyWord);
                    listView.setAdapter(new HomeAdapter(HomeActivity.this,accounts));
                }
                return false;
            }
        });

        btnRevenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CreateEmployeeActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLoadHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.setHoursEmployee();
                loadData();
                Toast.makeText(HomeActivity.this, "Set Start Hour Success", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadData(){
        ArrayList<Account> account = dbHelper.getAllAccount();
        listView.setAdapter(new HomeAdapter(this,account));
    }
}