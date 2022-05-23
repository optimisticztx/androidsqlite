package com.example.zhangtianxiang15.MyUtils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhangtianxiang15.MyUtils.Student;
import com.example.zhangtianxiang15.R;

import java.util.ArrayList;

/**
 * ListView Adapter
 */
public class StudentsAdapter extends BaseAdapter {
    private ListView listView;
    private LayoutInflater layoutInflater;
    private ArrayList<Student> studentsArrayList;

    public StudentsAdapter(Context context, ListView listView) {
        this.layoutInflater = LayoutInflater.from(context);
        this.listView = listView;
    }

    @Override
    public View getView(int arg0, View converView, ViewGroup arg2) {
        Log.i("StudentAdapter", "getView " + arg0 + " " + converView);
        ViewHolder viewHolder = null;
        Student student = (Student) getItem(arg0);
        if (converView == null) {
            converView = layoutInflater.inflate(R.layout.item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) converView.findViewById(R.id.name_id);
            viewHolder.rank = (TextView) converView.findViewById(R.id.rank);
            viewHolder.score = (TextView) converView.findViewById(R.id.score);
            viewHolder.android = (TextView) converView.findViewById(R.id.android);
            viewHolder.spark = (TextView) converView.findViewById(R.id.spark);
            viewHolder.datamining = (TextView) converView.findViewById(R.id.datamining);
            viewHolder.echarts = (TextView) converView.findViewById(R.id.echarts);
            converView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) converView.getTag();
        }
        String title = "姓名:" + student.getName() + "学号:" + student.getId();
        viewHolder.title.setText(title);
        String rank = "第" + (arg0 + 1) + "名";
        viewHolder.rank.setText(rank);
        viewHolder.score.setText(String.valueOf(student.getScore()));
        viewHolder.android.setText(String.valueOf(student.getAndroid()));
        viewHolder.spark.setText(String.valueOf(student.getSpark()));
        viewHolder.datamining.setText(String.valueOf(student.getDatamining()));
        viewHolder.echarts.setText(String.valueOf(student.getEcharts()));
        return converView;
    }

    //每个item的所有view的类
    public class ViewHolder {
        TextView title;
        TextView rank;
        TextView score;
        TextView android;
        TextView spark;
        TextView datamining;
        TextView echarts;
    }

    public void setData(ArrayList<Student> list) {
        studentsArrayList = list;
    }

    @Override
    public int getCount() {
        return null != studentsArrayList &&
                studentsArrayList.size() > 0 ?
                studentsArrayList.size() : 0;
    }

    @Override
    public Object getItem(int arg0) {
        return studentsArrayList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }


}


