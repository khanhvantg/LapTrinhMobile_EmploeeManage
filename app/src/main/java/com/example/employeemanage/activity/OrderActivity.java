package com.example.employeemanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.employeemanage.R;
import com.example.employeemanage.adapter.OrderAdapter;
import com.example.employeemanage.database.DBHelper;
import com.example.employeemanage.model.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private ListView listView;
    private DBHelper dbHelper;
    RadioGroup rdSearch;
    RadioButton rdSearchToday;
    EditText searchOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        searchOrders = findViewById(R.id.orders_search);
        rdSearch = findViewById(R.id.rd_orders);
        rdSearchToday = findViewById(R.id.rd_orders_today);
        listView = findViewById(R.id.list_orders);

        dbHelper = new DBHelper(this);
        rdSearch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(rdSearchToday.isChecked()){
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                    String strDate = formatter.format(date);

                    String[] splitStr = strDate.split("\\s+");
                    List<Order> orderList = dbHelper.getAllOrdersByTimeOrder(splitStr[0]);
                    listView.setAdapter(new OrderAdapter(OrderActivity.this,orderList));
                }else{
                    loadData();
                }
            }
        });

        searchOrders.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    String strKeyWord = searchOrders.getText().toString().trim();
                    List<Order> orderList = dbHelper.searchOrders(strKeyWord);
                    listView.setAdapter(new OrderAdapter(OrderActivity.this,orderList));
                }
                return false;
            }
        });

        loadData();
    }

    public void loadData(){
        ArrayList<Order> orderList = dbHelper.getAllOrders();
        listView.setAdapter(new OrderAdapter(this,orderList));
    }
}