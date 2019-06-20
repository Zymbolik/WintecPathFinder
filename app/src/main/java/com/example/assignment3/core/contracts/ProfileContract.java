package com.example.assignment3.core.contracts;

public interface ProfileContract {

    interface View {

        void displayFirstName(String firstName);

        void displayPersonalization();

        void displayAbout();
    }

    interface Presenter {

        void initialize(View view);

        void onPersonalization(View view);

        void onAbout(View view);
    }
}