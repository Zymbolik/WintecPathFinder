package com.example.assignment3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.android.AndroidPreferenceRepository;
import com.example.assignment3.core.contracts.ProfileContract;
import com.example.assignment3.core.contracts.ProfileContract.Presenter;
import com.example.assignment3.core.present.ProfilePresenter;
import com.example.assignment3.core.repo.PreferenceRepository;

public class ProfileScreen extends Fragment implements ProfileContract.View {

    private TextView textFirstName;

    private Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // setup ui components.
        textFirstName = view.findViewById(R.id.first_name_title);

        // setup ui actions.

        // setup presenter.
        PreferenceRepository repo = new AndroidPreferenceRepository(MainActivity.instance);
        presenter = new ProfilePresenter(repo);
        presenter.initialize(this);
    }

    @Override
    public void displayFirstName(String firstName) {
        textFirstName.setText(firstName);
    }

    @Override
    public void displayPersonalization() {
        Toast.makeText(MainActivity.instance, "Moving to personalization!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayAbout() {
        Toast.makeText(MainActivity.instance, "Moving to about!", Toast.LENGTH_SHORT).show();
    }
}