package com.example.employeemanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeemanage.model.Order;
import com.example.employeemanage.model.OrderDetail;
import com.example.employeemanage.R;
import com.example.employeemanage.database.DBHelper;
import com.example.employeemanage.model.OrderDetail;
import com.example.employeemanage.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailAdapter extends BaseAdapter{
    private List<OrderDetail> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private DBHelper dbHelper;

    public OrderDetailAdapter(Context aContext,  List<OrderDetail> listData, DBHelper dbHelper) {
        this.context = aContext;
        this.listData = listData;
        this.dbHelper = dbHelper;
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
            convertView = layoutInflater.inflate(R.layout.order_detail_item, null);
            holder = new ViewHolder();
            holder.productName = convertView.findViewById(R.id.product_name);
            holder.quantity = convertView.findViewById(R.id.product_quantity);
            holder.price = convertView.findViewById(R.id.price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        OrderDetail orderDetail= this.listData.get(position);
        Product product = dbHelper.findProductById(orderDetail.getProductId());

        holder.productName.setText(product.getName());
        holder.price.setText(product.getPrice().toString() + "$");
        holder.quantity.setText("x" +orderDetail.getQuantity().toString());
        return convertView;
    }
    static class ViewHolder {
        private TextView productName;
        private TextView quantity;
        private TextView price;
    }
}
