package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.SearchContract.Presenter;
import com.example.assignment3.core.contracts.SearchContract.View;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.core.repo.ModulesRepository;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements Presenter {

    private ModulesRepository repo;

    public SearchPresenter(ModulesRepository repo) {
        this.repo = repo;
    }

    @Override
    public void initialize(View view) {

    }

    @Override
    public void searchByProgramme(View view, String programme) {
        view.showLoading();
        Single.fromCallable(() -> repo.getProgrammeModules(programme))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modules -> displayResults(view, modules));
    }

    @Override
    public void searchBySpecialization(View view, String specialization) {
        view.showLoading();
        Single.fromCallable(() -> repo.getSpecializationModules(specialization))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modules -> displayResults(view, modules));
    }

    @Override
    public void searchByYear(View view, String year) {
        view.showLoading();
        String yr = year.replaceAll("\\D+", "");
        int intYear = Integer.parseInt(yr);
        Single.fromCallable(() -> repo.getYearModules(intYear))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modules -> displayResults(view, modules));
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
        view.hideLoading();
    }
}