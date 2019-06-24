package com.example.assignment3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.assignment3.android.AndroidPreferenceRepository;
import com.example.assignment3.core.contracts.HomeContract;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.core.domain.SampleModulesRepository;
import com.example.assignment3.core.present.HomePresenter;
import com.example.assignment3.core.present.ModulePresenter;
import com.example.assignment3.core.repo.ModulesRepository;
import com.example.assignment3.core.repo.PreferenceRepository;
import com.example.assignment3.ui.controls.CourseCard;

import java.io.IOException;
import java.util.List;

import static java8.util.stream.StreamSupport.stream;

public class HomeScreen extends Fragment implements HomeContract.View {

    private TextView textUsername;
    private TextView textHeading;
    private LinearLayout layoutModules;

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
        textUsername = view.findViewById(R.id.hs_username);
        textHeading = view.findViewById(R.id.hs_heading_message);
        layoutModules = view.findViewById(R.id.hs_modules);

        // setup ui actions.

        // setup presenter.
        try {
            MainActivity.instance.showToolBar();
            PreferenceRepository prefsRepo = new AndroidPreferenceRepository(getContext());
            ModulesRepository modulesRepo = new SampleModulesRepository(getActivity().getAssets().open("Modules.txt"));
            presenter = new HomePresenter(prefsRepo, modulesRepo);
            presenter.initialize(this);
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void displayUsername(String username) {
        textUsername.setText(username);
    }

    @Override
    public void displayHeading(String message) {
        textHeading.setText(message);
    }

    @Override
    public void displayModules(List<Module> modules) {
        stream(modules)
                .map(ModulePresenter::new)
                .forEach(this::appendCourseCard);
    }

    @Override
    public void displayNoModules() {

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