package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.FirstScreenContract;
import com.example.assignment3.core.contracts.FirstScreenContract.View;
import com.example.assignment3.core.repo.PreferenceRepository;

import static java.util.Objects.requireNonNull;

public class FirstScreenPresent implements FirstScreenContract.Present {

    private PreferenceRepository preference;

    public FirstScreenPresent(PreferenceRepository preference) {
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
