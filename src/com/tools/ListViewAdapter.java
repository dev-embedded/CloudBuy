package com.tools;

import java.util.ArrayList;
import java.util.HashMap;

import com.cloudbuy.R;
import com.domain.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{
    // 填充数据的list
    private ArrayList<Order> list;
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer,Boolean> isSelected;
    // 上下文
    private Context context;
    // 用来导入布局
    private LayoutInflater inflater = null;
    
    // 构造器
    public ListViewAdapter(ArrayList<Order> list, Context context) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        isSelected = new HashMap<Integer, Boolean>();
        // 初始化数据
        initDate();
    }

    // 初始化isSelected的数据
    private void initDate(){
        for(int i=0; i<list.size();i++) {
            getIsSelected().put(i,false);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
            if (convertView == null) {
            // 获得ViewHolder对象
            holder = new ViewHolder();
            // 导入布局并赋值给convertview
            convertView = inflater.inflate(R.layout.delivery_list_item, parent, false);
            //convertView = inflater.inflate(R.layout.delivery_list_item, null);
            holder.tvOrderNo = (TextView) convertView.findViewById(R.id.listViewOrderNo);
            holder.tvOrderSum = (TextView) convertView.findViewById(R.id.listViewOrderPay);
            holder.tvAddress = (TextView) convertView.findViewById(R.id.listViewAddress);
            holder.cbIsSelected = (CheckBox) convertView.findViewById(R.id.listViewIsSelected);
            // 为view设置标签
            convertView.setTag(holder);
        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
            }


        // 设置list中TextView的显示
        holder.tvOrderNo.setText(String.valueOf(list.get(position).getOrderNo()));
        holder.tvOrderSum.setText("$"+String.valueOf(list.get(position).getOrderSum()));
        holder.tvAddress.setText(list.get(position).getAddress());
        // 根据isSelected来设置checkbox的选中状况
        holder.cbIsSelected.setChecked(getIsSelected().get(position));
        return convertView;
    }

    public static HashMap<Integer,Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {
    	ListViewAdapter.isSelected = isSelected;
    }

}


