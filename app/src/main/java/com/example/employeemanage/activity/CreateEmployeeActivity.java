package com.example.employeemanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeemanage.R;
import com.example.employeemanage.database.DBHelper;
import com.example.employeemanage.model.Account;
import com.example.employeemanage.model.Employee;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class CreateEmployeeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText edtUsername, edtPassword, edtEmail, edtFullName, edtPhone, edtAddress, edtIdentityNumber;
    RadioGroup rdGroupKind, rdGroupPosition, rdGroupGender;
    RadioButton selectedRdKind, rdEmployee,selectedRdPosition, selectedRdGender;
    Button btnAdd;
    View employeeForm;
    ImageView imgAva, btnCalendar;
    TextView tvBirthday;
    private String employeeBirthday;
    DBHelper dbHelper = new DBHelper(this);
    double hours =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);
        edtFullName = findViewById(R.id.edt_fullname);
        rdGroupKind = findViewById(R.id.rd_group_kind);
        rdEmployee = findViewById(R.id.rd_kind_employee);
        btnAdd = findViewById(R.id.btn_add_account);
        employeeForm = findViewById(R.id.employee_create_form);
        imgAva = findViewById(R.id.avatar_default);
        tvBirthday = findViewById(R.id.tv_birthday);
        btnCalendar = findViewById(R.id.btn_calendar);
        edtIdentityNumber = findViewById(R.id.edt_identity_number);
        edtAddress = findViewById(R.id.edt_address);
        rdGroupPosition = findViewById(R.id.rd_group_position);
        rdGroupGender = findViewById(R.id.rd_group_gender);

        rdGroupKind.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rdEmployee.isChecked()) {
                    employeeForm.setVisibility(View.VISIBLE);
                } else {
                    employeeForm.setVisibility(View.GONE);
                }
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rdEmployee.isChecked()) {
                    handleAddEmployeeAccount();
                } else {
                    handleAddAccount();
                }
            }
        });
    }

    private void handleAddEmployeeAccount() {
        selectedRdPosition = findViewById(rdGroupPosition.getCheckedRadioButtonId());
        String strPosition = selectedRdPosition.getText().toString();

        Integer position = null;
        if (strPosition == "Full time") {
            position = 0;
        } else if (strPosition == "Part time") {
            position = 1;
        } else {
            position = 2;       //manager
        }

        String strAddress = edtAddress.getText().toString();
        String strIdentity = edtIdentityNumber.getText().toString().trim();

        selectedRdGender = findViewById(rdGroupGender.getCheckedRadioButtonId());
        String strGender = selectedRdGender.getText().toString();

        Integer gender = null;
        if (strGender == "Male") {
            gender = 1;         //male
        } else {
            gender = 0;         //female
        }
        Long accountId = getIdAccountCreated();
        if (accountId == null) {
            Toast.makeText(this, "Can not create employee", Toast.LENGTH_SHORT).show();
        }
        if (employeeBirthday == null || gender == null || strIdentity == null) {
            Toast.makeText(this, "Please fill enough employee form", Toast.LENGTH_SHORT).show();
        } else {
            Double salary = null; //don vi: $/h
            if (position.equals(1)) {
                salary = 2d;
            } else if (position.equals(0)) {
                salary = 1.5d;
            } else {
                salary = 1d;
            }
            Employee employee = new Employee(employeeBirthday, position, accountId, strAddress, gender, strIdentity, salary, null, 0.0);

            Long id = dbHelper.createEmployee(employee);
            if (id == null) {
                Toast.makeText(this, "Can not create employee", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Create employee success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + (month + 1) + "/" + year;
        tvBirthday.setText(date);
        employeeBirthday = date;
    }

    private void handleAddAccount() {
        Long id = getIdAccountCreated();
        if (id != null) {
            Toast.makeText(this, "Create employee successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Fail to create employee", Toast.LENGTH_SHORT).show();
        }
    }

    private Long getIdAccountCreated() {
        String strUsername = edtUsername.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();
        String strEmail = edtEmail.getText().toString().trim();
        String strPhone = edtPhone.getText().toString().trim();
        String strFullName = edtFullName.getText().toString().trim();
        if (strUsername.equals("")) {
            Toast.makeText(this, "Please fill username", Toast.LENGTH_SHORT).show();
        } else if (strPassword.equals("")) {
            Toast.makeText(this, "Please fill password", Toast.LENGTH_SHORT).show();
        } else if (strFullName.equals("")) {
            Toast.makeText(this, "Please fill full name", Toast.LENGTH_SHORT).show();
        } else if (strEmail.equals("")) {
            Toast.makeText(this, "Please fill email", Toast.LENGTH_SHORT).show();
        } else if (strPhone.equals("")) {
            Toast.makeText(this, "Please fill phone", Toast.LENGTH_SHORT).show();
        }
        selectedRdKind = findViewById(rdGroupKind.getCheckedRadioButtonId());
        String strKind = selectedRdKind.getText().toString().trim();
        Integer kind = null;
        if (strKind.equals("Admin")) {
            kind = 1;
        } else {
            kind = 0;
        }
        if (strUsername.equals("") && dbHelper.findAccountByUsername(strUsername) != null) {
            Toast.makeText(this, "Username existed", Toast.LENGTH_SHORT).show();

        } else {
            //Chuyen tu ImageView sang byte[]
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAva.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
            byte[] avatar = byteArray.toByteArray();

            Account account = new Account(strUsername, strPassword, strFullName, strEmail, kind, strPhone, avatar);
            Long id = dbHelper.createAccount(account);
            return id;
        }
        return null;
    }
}