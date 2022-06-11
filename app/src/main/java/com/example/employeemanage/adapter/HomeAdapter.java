package com.example.employeemanage.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeemanage.R;
import com.example.employeemanage.activity.HomeActivity;
import com.example.employeemanage.activity.UpdateEmployeeActivity;
import com.example.employeemanage.database.DBHelper;
import com.example.employeemanage.model.Account;
import com.example.employeemanage.model.Employee;

import java.util.List;

public class HomeAdapter extends BaseAdapter {
    private List<Account> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public HomeAdapter(Context aContext, List<Account> listData) {
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
            convertView = layoutInflater.inflate(R.layout.employee_item, null);
            holder = new ViewHolder();
            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.img_avatar);
            holder.tvFullName = (TextView) convertView.findViewById(R.id.tv_fullname);
            holder.tvEmail = (TextView) convertView.findViewById(R.id.tv_email);
            holder.tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);
            holder.tvKind = (TextView) convertView.findViewById(R.id.tv_kind);
            holder.tvId = (TextView) convertView.findViewById(R.id.tv_id);
            holder.tvHours = (TextView) convertView.findViewById(R.id.tv_hours);
            holder.btnDelete = (ImageButton) convertView.findViewById(R.id.btn_delete);
            holder.btnEdit = (ImageButton) convertView.findViewById(R.id.btn_edit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DBHelper dbHelper =new DBHelper(context);
        Account account = this.listData.get(position);
        Employee employee = new Employee();
        if(account.getAvatar() != null){
            byte[] pic = account.getAvatar();
            Bitmap bitmap = BitmapFactory.decodeByteArray(pic, 0, pic.length);
            holder.imgAvatar.setImageBitmap(bitmap);
        }
        holder.tvFullName.setText(account.getFullName());
        holder.tvEmail.setText(account.getEmail());
        holder.tvPhone.setText(account.getPhone());
        if(account.getKind().equals(1)){
            holder.tvKind.setText("Admin");
            holder.tvHours.setText("Hours: None");
            holder.btnDelete.setVisibility(View.GONE);
        }
        else {
            holder.tvKind.setText("Employee");
            employee=dbHelper.findEmployeeByAccountId(account.getId().toString());
            holder.tvHours.setText("Hours: "+ employee.getHours().toString()+"h");
        }
        holder.tvId.setText(account.getId().toString());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateEmployeeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_account",account);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper =new DBHelper(context);
                dbHelper.deleteAccount(account.getId().toString());
                //remove item in listView
                listData.remove(getItem(position));
                notifyDataSetChanged();
                //Thong bao
                Toast.makeText(context, "Deleted Employee "+ account.getFullName() +" Success", Toast.LENGTH_LONG).show();
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateEmployeeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_account",account);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        private ImageView imgAvatar;
        private TextView tvFullName;
        private TextView tvEmail;
        private TextView tvPhone;
        private TextView tvKind;
        private TextView tvId;
        private TextView tvHours;
        private ImageButton btnDelete;
        private ImageButton btnEdit;
    }
}
