package com.example.assignment3.core.contracts;

public interface FirstScreenContract {

    interface View {

        void enableSubmit();

        void disableSubmit();

        void displayHomeScreen();
    }

    interface Present {

        void initialize(View view);

        void onFirstNameCheck(View view, String firstName);

        void onSubmit(View view, String firstName, String lastName);
    }
}