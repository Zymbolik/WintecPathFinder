package com.example.assignment3.ui.controls;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.R;
import com.example.assignment3.core.contracts.ModuleContract;

import static java8.util.Objects.requireNonNull;

public class CourseCard extends Fragment implements ModuleContract.View {

    private boolean expanded;
    private TextView textModuleCode;
    private TextView textModuleName;
    private TextView textModuleLevel;
    private TextView textModuleStatus;

    private ModuleContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.control_coursetile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // setup ui components.
        textModuleCode = view.findViewById(R.id.text_course_code);
        textModuleName = view.findViewById(R.id.text_course_name);
        textModuleLevel = view.findViewById(R.id.text_course_levelnum);
        textModuleStatus = view.findViewById(R.id.text_course_status);

        // setup ui actions.
        view.setOnClickListener(ignored -> {
            if(expanded) presenter.onCollapseDetails(this);
            else presenter.onExpandDetails(this);
            expanded = !expanded;
        });

        presenter.initialize(this);
    }

    @Override
    public void setPresenter(ModuleContract.Presenter presenter) {
        this.presenter = requireNonNull(presenter);
    }

    @Override
    public void displayModuleCode(String moduleCode) {
        textModuleCode.setText(moduleCode);
    }

    @Override
    public void displayModuleName(String moduleName) {
        textModuleName.setText(moduleName);
    }

    @Override
    public void displayModuleLevel(String moduleLevel) {
        textModuleLevel.setText(moduleLevel);
    }

    @Override
    public void displayModuleStatus(String moduleStatus) {
        textModuleStatus.setText(moduleStatus);
    }

    @Override
    public void expandDetails() {
        Toast.makeText(getContext(), "Expanding details", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void collapseDetails() {
        Toast.makeText(getContext(), "Collapsing details", Toast.LENGTH_SHORT).show();
    }
}