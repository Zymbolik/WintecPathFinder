package com.example.assignment3.core.contracts;

import com.example.assignment3.core.domain.Module;

import java.util.List;

public interface HomeContract {

    interface View {

        void displayUsername(String username);

        void displayHeading(String message);

        void displayModules(List<Module> modules);

        void displayNoModules();
    }

    interface Presenter {

        void initialize(View view);

        void onAddModules(View view);
    }
}