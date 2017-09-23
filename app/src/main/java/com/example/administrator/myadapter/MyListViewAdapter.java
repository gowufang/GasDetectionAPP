//package com.example.administrator.myadapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.example.administrator.entity.Data;
//import com.example.administrator.gasdetectionapp.R;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2017/7/7.
// */
//public class MyListViewAdapter extends BaseAdapter {
//    private List<Data> mData;
//    private Context mContext;
//
//    public MyListViewAdapter(List<Data> mdata, Context mcontext) {
//        mData = mdata;
//        mContext = mcontext;
//    }
//
//    @Override
//    public int getCount() {
//        return mData.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.gas_data_listview_item,parent,false);
//            viewHolder = new ViewHolder();
//            viewHolder.Id_value = (TextView) convertView.findViewById(R.id.Id_value);
//            viewHolder.EquipmentNumber_value = (TextView) convertView.findViewById(R.id.EquipmentNumber_value);
//            viewHolder.AcquisitionTime_value = (TextView) convertView.findViewById(R.id.AcquisitionTime_value);
//            viewHolder.Longitude_value = (TextView) convertView.findViewById(R.id.Longitude_value);
//            viewHolder.Latitude_value = (TextView) convertView.findViewById(R.id.Latitude_value);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        viewHolder.Id_value.setText(mData.get(position).getId_value_content());
//        viewHolder.EquipmentNumber_value.setText(mData.get(position).getDeviceId_value_content());
//        viewHolder.AcquisitionTime_value.setText(mData.get(position).getAcquisitionTime_value_content());
//        viewHolder.Longitude_value.setText(mData.get(position).getLongitude_value_content());
//        viewHolder.Latitude_value.setText(mData.get(position).getLatitude_value_content());
//        /*convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("myMsg.AdapterClick","click");
//            }
//        });*/
//        return convertView;
//    }
//
//    private class ViewHolder{
//        TextView Id_value;
//        TextView EquipmentNumber_value;
//        TextView AcquisitionTime_value;
//        TextView Longitude_value;
//        TextView Latitude_value;
//    }
//}
