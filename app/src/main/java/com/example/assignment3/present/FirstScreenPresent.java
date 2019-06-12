package com.example.assignment3.present;

import com.example.assignment3.contracts.FirstScreenContract;
import com.example.assignment3.contracts.FirstScreenContract.View;
import com.example.assignment3.repo.PreferenceRepository;

import static java.util.Objects.requireNonNull;

public class FirstScreenPresent implements FirstScreenContract.Present {

    private PreferenceRepository preference;

    public FirstScreenPresent(PreferenceRepository preference) {
        this.preference = requireNonNull(preference);
    }

    @Override
    public void initialize(View view) {
        if(preference.loadUsername().isPresent()) {
            view.displayHomeScreen();
        }
        else {
            view.disableSubmit();

            // FIXME load disclaimer from file
            view.displayDisclaimer("This is a disclaimer");
        }
    }

    @Override
    public void disclaimerAccepted(View view, boolean isAccepted) {
        if(isAccepted)
            view.enableSubmit();
        else
            view.disableSubmit();
    }

    @Override
    public void onSubmit(View view, String name) {
        preference.saveUsername(name);
        view.displayHomeScreen();
    }
}
