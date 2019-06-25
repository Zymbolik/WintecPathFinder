package com.example.assignment3.core.contracts;

import com.example.assignment3.core.domain.Module;

import java.util.List;

public interface SearchContract {

    interface View {

        void displayResults(List<Module> modules);

        void displayNoResults();

        void showLoading();

        void hideLoading();
    }

    interface Presenter {

        void initialize(View view);

        void searchByProgramme(View view, String programme);

        void searchBySpecialization(View view, String specialization);

        void searchByYear(View view, String year);

        void search(View view, String year, String programme, String specialization);
    }
}