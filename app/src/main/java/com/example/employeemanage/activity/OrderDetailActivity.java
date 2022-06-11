package com.example.employeemanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.employeemanage.R;
import com.example.employeemanage.adapter.OrderAdapter;
import com.example.employeemanage.adapter.OrderDetailAdapter;
import com.example.employeemanage.database.DBHelper;
import com.example.employeemanage.model.Order;
import com.example.employeemanage.model.OrderDetail;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {
    private ListView listView;
    private DBHelper dbHelper;
    private TextView ordersId;
    private TextView orderTime;
    private TextView totalMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        ordersId = findViewById(R.id.orders_detail_id_order);
        orderTime = findViewById(R.id.orders_detail_time_order);
        totalMoney = findViewById(R.id.orders_detail_total_money);
        listView = findViewById(R.id.list_orders_detail);
        Order order = (Order) getIntent().getExtras().get("object_orders");

        dbHelper = new DBHelper(this);
        ArrayList<OrderDetail> orderDetailList = dbHelper.getAllOrdersDetailByOrdersId(order.getId());
        listView.setAdapter(new OrderDetailAdapter(this,orderDetailList,dbHelper));
        ordersId.setText("Mã hóa đơn: " + order.getId().toString());
        orderTime.setText("Thời gian: " + order.getTimeOrder());
        totalMoney.setText("Tổng tiền: " + order.getTotalMoney().toString() + "$");
    }
}