package com.example.wlwlxgg.titilebardemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wlwlxgg on 2016/12/30.
 */

public class MyFragment extends android.support.v4.app.Fragment {

    static MyFragment newInstance(String s) {
        MyFragment newFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("string", s);
        newFragment.setArguments(bundle);
        return newFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        TextView textView = (TextView) v.findViewById(R.id.fragment_text);
        Bundle bundle = getArguments();
        String s = bundle == null ? "null" : bundle.getString("string");
        textView.setText(s);
        return v;
    }
}
