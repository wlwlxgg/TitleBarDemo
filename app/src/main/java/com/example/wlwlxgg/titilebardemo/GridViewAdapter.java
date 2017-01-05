package com.example.wlwlxgg.titilebardemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wlwlxgg on 2017/1/5.
 */

public class GridViewAdapter extends BaseAdapter {
    private ArrayList<String> titleList;
    private Context mContext;
    private TextView tx;
    public GridViewAdapter(Context mContext, ArrayList<String> titleList) {
        this.titleList = titleList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.gridview_layout, null);
        tx = (TextView) convertView.findViewById(R.id.text);
        tx.setText(titleList.get(position));
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return titleList.get(position);
    }


}
