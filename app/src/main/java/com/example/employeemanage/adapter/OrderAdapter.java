package com.example.employeemanage.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.employeemanage.R;
import com.example.employeemanage.activity.OrderDetailActivity;
import com.example.employeemanage.activity.UpdateEmployeeActivity;
import com.example.employeemanage.model.Order;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private List<Order> listData;
    private LayoutInflater layoutInflater;
    private Context context;


    public OrderAdapter(Context aContext,  List<Order> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.order_item, null);
            holder = new ViewHolder();
            holder.tvEmployeeId = (TextView) convertView.findViewById(R.id.orders_employee_id);
            holder.tvCustomerName =(TextView) convertView.findViewById(R.id.orders_customer_name);
            holder.tvCustomerPhone = (TextView) convertView.findViewById(R.id.orders_customer_phone);
            holder.tvTimeOrdered = (TextView) convertView.findViewById(R.id.orders_time);
            holder.tvTotalMoney = (TextView) convertView.findViewById(R.id.orders_total_money);
            holder.tvId = (TextView) convertView.findViewById(R.id.orders_id);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Order order= this.listData.get(position);
        holder.tvCustomerName.setText("Tên khách hàng: " + order.getCustomerName());
        holder.tvEmployeeId.setText("Mã nhân viên : " + order.getEmployeeId().toString());
        holder.tvCustomerPhone.setText("Số điện thoại: " + order.getCustomerPhone());
        holder.tvTimeOrdered.setText("Time: " + order.getTimeOrder().trim());
        holder.tvTotalMoney.setText("Tổng tiền: " + order.getTotalMoney().toString() + '$');
        holder.tvId.setText(order.getId().toString());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_orders",order);
                intent.putExtras(bundle);
                context.startActivity(intent);
                //Toast.makeText(context, "You Clicked " + account.getFullName(), Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }
    static class ViewHolder {
        private TextView tvEmployeeId;
        private TextView tvCustomerName;
        private TextView tvCustomerPhone;
        private TextView tvTimeOrdered;
        private TextView tvId;
        private TextView tvTotalMoney;
    }
}
