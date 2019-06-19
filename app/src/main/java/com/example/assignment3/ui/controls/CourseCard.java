package com.example.assignment3.ui.controls;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment3.R;

public class CourseCard extends Fragment {

    public static View instance;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.control_coursetile, container, false);

        instance = view;

        return view;
    }

    public void setCourseCode(String _courseCode)
    {
        if(instance == null) return;
        ((TextView)instance.findViewById(R.id.text_course_code)).setText(_courseCode);
    }

    public void setCourseName(String _courseName)
    {
        if(instance == null) return;
        ((TextView)instance.findViewById(R.id.text_course_name)).setText(_courseName);
    }

    public void setCourseLevel(int _courseLevel)
    {
        if(instance == null) return;
        ((TextView)instance.findViewById(R.id.text_course_name)).setText(_courseLevel);
    }

    public void setCourseStatus(String _courseStatusMsg)
    {
        if(instance == null) return;
        ((TextView)instance.findViewById(R.id.text_course_status)).setText(_courseStatusMsg);
    }

    public void setCourseTips(String _courseTipsMsg)
    {
        if(instance == null) return;
        ((TextView)instance.findViewById(R.id.text_course_tips)).setText(_courseTipsMsg);
    }
}
