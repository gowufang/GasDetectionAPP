package com.example.administrator.myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.entity.GasEntity;
import com.example.administrator.gasdetectionapp.R;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */

public class GasDataAdapter extends ArrayAdapter<GasEntity> {
    private int resourceId;


    public GasDataAdapter(Context context, int resource, List<GasEntity> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GasEntity gasEntity=getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
             view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.insertTime=(TextView) view.findViewById(R.id.insert_time);
            viewHolder.gas1= (TextView) view.findViewById(R.id.gas1);
            viewHolder.gas2= (TextView) view.findViewById(R.id.gas2);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.insertTime.setText(gasEntity.getInsertTime());
        viewHolder.gas1.setText(gasEntity.getGas1());
        viewHolder.gas2.setText(gasEntity.getGas2());
        return view;

    }
    class ViewHolder{
        TextView insertTime;
        TextView gas1;
        TextView gas2;

    }
}
