package com.example.employeemanage.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.employeemanage.R;
import com.example.employeemanage.database.DBHelper;
import com.example.employeemanage.model.Account;
import com.example.employeemanage.model.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;




public class AttendanceActivity extends AppCompatActivity {
    Button buttonCheckIn, buttonCheckOut;
    ImageView imageViewPhoto;
    int REQUEST_CODE_CAMERA;
    private ImageButton btnLogout;
    TextView tvFullname, tvSalary, tvTotalHours, tvHours, tvCheckIn;
    private DBHelper dbHelper = new DBHelper(this);
    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_attendance);

        buttonCheckIn = findViewById( R.id.btnCheckIn );
        buttonCheckOut = findViewById( R.id.btnCheckOut );
        imageViewPhoto = findViewById( R.id.imageViewPhoto );
        tvTotalHours = findViewById( R.id.tv_totalHours );
        tvHours = findViewById( R.id.tv_hours );
        tvSalary = findViewById(R.id.tv_salary);
        tvFullname = findViewById(R.id.tv_fullname);
        tvCheckIn = findViewById(R.id.tv_checkIn);
        btnLogout = findViewById(R.id.btn_logout);

        account = (Account) getIntent().getExtras().get("object_account");
        Employee employee = dbHelper.findEmployeeByAccountId(account.getId().toString());
        tvFullname.setText(account.getFullName());
        tvSalary.setText("Salary: "+employee.getSalary().toString()+"$");
        tvTotalHours.setText("Total Hours: "+employee.getHours().toString()+"h");
        if(account.getAvatar() != null){
            byte[] pic = account.getAvatar();
            Bitmap bitmap = BitmapFactory.decodeByteArray(pic, 0, pic.length);
            imageViewPhoto.setImageBitmap(bitmap);
        }
        if (employee.getDateTime()!=null) {
            tvCheckIn.setText("Checked In at "+employee.getDateTime());
        }
        buttonCheckIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
                String datetime = dateformat.format(c.getTime());
                tvCheckIn.setText("Checked In at "+datetime);
                if(account.getKind().equals(0)) {
                    Employee employee = dbHelper.findEmployeeByAccountId(account.getId().toString());
                    if (employee != null) {
                        employee.setDateTime(datetime);
                        dbHelper.updateEmployee(employee);
                        Toast.makeText(AttendanceActivity.this, "Check In Successful  ", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(AttendanceActivity.this, "Can not update dateTime ", Toast.LENGTH_SHORT).show();
                }
            }
        } );

        buttonCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
                String datetime2 = dateformat.format(c.getTime());
                Employee employee = dbHelper.findEmployeeByAccountId(account.getId().toString());
                String datetime1 = employee.getDateTime();
                try {
                    Date date1 = dateformat.parse(datetime1);
                    Date date2 = dateformat.parse(datetime2);
                    Double hours= ((double) getDiffTime(date1, date2))/60;
                    tvHours.setText("Hours in day: "+hours.toString()+"h");
                    Double totalHours= employee.getHours()+hours;
                    Double salary=0d;
                    if (account.getKind().equals(1)) {
                        salary=2*totalHours;
                    } else if (account.getKind().equals(0)) {
                        salary = 1.5d*totalHours;
                    } else {
                        salary = 1d*totalHours;
                    }
                    employee.setSalary(salary);
                    employee.setHours(totalHours);
                    employee.setDateTime("");
                    dbHelper.updateEmployee(employee);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tvSalary.setText("Salary: "+employee.getSalary().toString()+"$");
                tvTotalHours.setText("Total Hours: "+employee.getHours().toString()+"h");
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AttendanceActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
//        if (requestCode == REQUEST_CODE_CAMERA && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            //Check hardware device have camera or not
//            if (checkCameraHardware(AttendanceActivity.this)) {
//                Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
//                startActivityForResult( intent, REQUEST_CODE_CAMERA );
//            } else {
//                Toast.makeText( this, "This device don't have camre", Toast.LENGTH_SHORT ).show();
//            }
//
//        }
//    }
//
//    private boolean checkCameraHardware(Context context) {
//        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
//            // this device has a camera
//            return true;
//        } else {
//            // no camera on this device
//            return false;
//        }
//    }


    public static long getDiffTime(Date date1, Date date2){
        if (date2.getTime() - date1.getTime() < 0) {// if for example date1 = 22:00, date2 = 01:55.
            Calendar c = Calendar.getInstance();
            c.setTime(date2);
            c.add(Calendar.DATE, 1);
            date2 = c.getTime();
        }
        long ms = date2.getTime() - date1.getTime();
        return TimeUnit.MINUTES.convert(ms, TimeUnit.MILLISECONDS);
    }
}