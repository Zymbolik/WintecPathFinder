package com.example.assignment3.core.contracts;

public interface ToolbarContract {

    interface View {

        void displaySearchFocused();

        void displaySearchUnfocused();

        void displaySearch();

        void displayProfileFocused();

        void displayProfileUnfocused();

        void displayProfile();

        void displayHome();
    }

    interface Presenter {

        void initialize(View view);

        void onSearchClicked(View view);

        void onHomeClicked(View view);

        void onProfileClicked(View view);
    }
}