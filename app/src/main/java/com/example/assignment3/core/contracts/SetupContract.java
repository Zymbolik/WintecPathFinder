package com.example.assignment3.core.contracts;

public interface SetupContract {

    interface View {

        void enableSubmit();

        void disableSubmit();

        void displayHomeScreen();
    }

    interface Presenter {

        void initialize(View view);

        void onFirstNameCheck(View view, String firstName);

        void onSubmit(View view, String firstName, String lastName);
    }
}