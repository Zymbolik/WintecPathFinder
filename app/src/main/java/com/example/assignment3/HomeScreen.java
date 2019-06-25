package com.example.assignment3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.assignment3.core.contracts.HomeContract;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.core.present.HomePresenter;
import com.example.assignment3.core.present.ModulePresenter;
import com.example.assignment3.ui.SearchScreen;
import com.example.assignment3.ui.controls.CourseCard;

import java.util.List;

import static java8.util.stream.StreamSupport.stream;

public class HomeScreen extends Fragment implements HomeContract.View {

    private TextView textUsername;
    private TextView textHeading;
    private LinearLayout layoutModules;
    private Button btnAddModules;
    private ScrollView scrollModules;

    private HomeContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // setup ui components.
        textUsername = view.findViewById(R.id.hs_title_message);
        textHeading = view.findViewById(R.id.hs_heading_message);
        layoutModules = view.findViewById(R.id.hs_modules);
        btnAddModules = view.findViewById(R.id.hs_btn_add_modules);
        scrollModules = view.findViewById(R.id.hs_scroll_modules);

        // setup ui actions.
        btnAddModules.setOnClickListener(ignored -> presenter.onAddModules(this));

        // setup presenter.
        MainActivity.instance.showToolBar();
        presenter = new HomePresenter(MainActivity.preferences, MainActivity.modules);
        presenter.initialize(this);
    }

    @Override
    public void displayUsername(String username) {
        textUsername.setText("Hello, \n" + username + ".");
    }

    @Override
    public void displayHeading(String message) {
        textHeading.setText(message);
    }

    @Override
    public void displayModules(List<Module> modules) {
        btnAddModules.setVisibility(View.INVISIBLE);
        scrollModules.setVisibility(View.VISIBLE);
        layoutModules.removeAllViews();
        stream(modules)
                .map(ModulePresenter::new)
                .forEach(this::appendCourseCard);
    }

    @Override
    public void displayNoModules() {
        scrollModules.setVisibility(View.INVISIBLE);
        btnAddModules.setVisibility(View.VISIBLE);
    }

    @Override
    public void displaySearchScreen() {
        MainActivity.instance.changePage(new SearchScreen());
    }

    private void appendCourseCard(ModulePresenter modulePresenter) {
        CourseCard card = new CourseCard();
        card.setPresenter(modulePresenter);
        getFragmentManager()
                .beginTransaction()
                .add(layoutModules.getId(), card)
                .commit();
    }
}