package com.example.attendance_checking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseListAdapter extends BaseAdapter {
    LayoutInflater lInflater;
    ArrayList<Course> courses;
    Context context;
    Student student;

    public CourseListAdapter(ArrayList<Course> courses, Student student, Context context){
        this.courses = courses;
        this.student = student;
        this.context = context;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Object getItem(int i) {
        return courses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.course_item, viewGroup, false);
        }

        Course c = (Course)getItem(i);
        Attendance a = c.attendanceMap.get(student);
        ((TextView) view.findViewById(R.id.list_course_name)).setText(c.courseName);
        ((TextView) view.findViewById(R.id.list_course_proff_name)).setText("Prof: "+ c.professor.name);
        ((TextView) view.findViewById(R.id.list_course_percentage))
                .setText(a.toString());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        for(int j=0; j<a.attendance_list.length; j++){
            System.out.println(j);
            FrameLayout new_frame = new FrameLayout(context);
            new_frame.setLayoutParams(params);
            new_frame.setId(-i);

            if(a.attendance_list[j]) {
                if(j==0){
                    new_frame.setBackground(context.getResources().getDrawable(R.drawable.green_cell));
                }else{
                    new_frame.setBackground(context.getResources().getDrawable(R.drawable.add_green_cell));
                }

            }else{
                if(j==0){
                    new_frame.setBackground(context.getResources().getDrawable(R.drawable.red_cell));
                }else{
                    new_frame.setBackground(context.getResources().getDrawable(R.drawable.add_red_cell));
                }
            }
            ((LinearLayout) view.findViewById(R.id.list_attendance)).addView(new_frame);
        }
        return view;
    }
}
