package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.SearchContract;
import com.example.assignment3.core.contracts.SearchContract.View;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.core.repo.ModuleRepo;

import java.util.List;

import io.reactivex.Single;

public class SearchPresent implements SearchContract.Present {

    private ModuleRepo repo;

    public SearchPresent(ModuleRepo repo) {
        this.repo = repo;
    }

    @Override
    public void initialize(View view) {

    }

    @Override
    public void searchByProgramme(View view, String programme) {
        view.showLoading();
        Single.fromCallable(() -> repo.getProgrammeModules(programme))
                .subscribe(modules -> displayResults(view, modules))
                .dispose();
    }

    @Override
    public void searchBySpecialization(View view, String specialization) {
        view.showLoading();
        Single.fromCallable(() -> repo.getSpecializationModules(specialization))
                .subscribe(modules -> displayResults(view, modules))
                .dispose();
    }

    @Override
    public void searchByYear(View view, String year) {
        int intYear = Integer.parseInt(year);
        Single.fromCallable(() -> repo.getYearModules(intYear))
                .subscribe(modules -> displayResults(view, modules))
                .dispose();
    }

    /**
     * Determines how the results will be displayed in the view.
     * @param view the view to display in.
     * @param results the results to display.
     */
    private void displayResults(View view, List<Module> results) {
        view.hideLoading();
        if (results.isEmpty())
            view.displayNoResults();
        else
            view.displayResults(results);
    }
}