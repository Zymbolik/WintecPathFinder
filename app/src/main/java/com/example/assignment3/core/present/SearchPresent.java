package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.SearchContract;
import com.example.assignment3.core.contracts.SearchContract.View;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.repo.ModuleRepo;

import java.util.List;

public class SearchPresent implements SearchContract.Present {

    private ModuleRepo repo;

    @Override
    public void initialize(View view) {

    }

    @Override
    public void searchByProgramme(View view, String programme) {
        List<Module> modules = repo.getProgrammeModules(programme);
        displayResults(view, modules);
    }

    @Override
    public void searchBySpecialization(View view, String specialization) {
        List<Module> modules = repo.getSpecializationModules(specialization);
        displayResults(view, modules);
    }

    @Override
    public void searchByYear(View view, String year) {
        int intYear = Integer.parseInt(year);
        List<Module> modules = repo.getYearModules(intYear);
        displayResults(view, modules);
    }

    /**
     * Determines how the results will be displayed in the view.
     * @param view the view to display in.
     * @param results the results to display.
     */
    private void displayResults(View view, List<Module> results) {
        if (results.isEmpty())
            view.displayNoResults();
        else
            view.displayResults(results);
    }
}