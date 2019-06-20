package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.ProfileContract;
import com.example.assignment3.core.contracts.ProfileContract.View;
import com.example.assignment3.core.repo.PreferenceRepository;

import static java8.util.Objects.requireNonNull;

public class ProfilePresenter implements ProfileContract.Presenter {

    private PreferenceRepository repo;

    public ProfilePresenter(PreferenceRepository repo) {
        this.repo = requireNonNull(repo);
    }


    @Override
    public void initialize(View view) {
        String firstName = repo.loadFirstName().get();
        view.displayFirstName(firstName);
    }

    @Override
    public void onPersonalization(View view) {
        view.displayPersonalization();
    }

    @Override
    public void onAbout(View view) {
        view.displayAbout();
    }
}