package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.SetupContract.Presenter;
import com.example.assignment3.core.contracts.SetupContract.View;
import com.example.assignment3.core.repo.PreferenceRepository;

import static java.util.Objects.requireNonNull;

public class SetupPresenter implements Presenter {

    private PreferenceRepository preference;

    public SetupPresenter(PreferenceRepository preference) {
        this.preference = requireNonNull(preference);
    }

    @Override
    public void initialize(View view) {
        if(preference.loadFirstName().isPresent())
            view.displayHomeScreen();
        else
            view.disableSubmit();
    }

    @Override
    public void onFirstNameCheck(View view, String firstName) {
        if(firstName == null || firstName.isEmpty())
            view.disableSubmit();
        else
            view.enableSubmit();
    }

    @Override
    public void onSubmit(View view, String firstName, String lastName) {
        preference.saveFirstName(firstName);
        preference.saveLastName(lastName);
        view.displayHomeScreen();
    }
}
