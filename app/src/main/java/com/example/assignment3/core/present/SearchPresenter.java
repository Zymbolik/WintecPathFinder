package com.example.assignment3.core.present;

import android.widget.Toast;

import com.example.assignment3.MainActivity;
import com.example.assignment3.core.contracts.SearchContract.Presenter;
import com.example.assignment3.core.contracts.SearchContract.View;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.core.repo.ModulesRepository;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static java.lang.Integer.parseInt;
import static java8.util.stream.Collectors.toList;
import static java8.util.stream.StreamSupport.stream;

public class SearchPresenter implements Presenter {

    private ModulesRepository repo;

    public SearchPresenter(ModulesRepository repo) {
        this.repo = repo;
    }

    @Override
    public void initialize(View view) {
        view.hideLoading();
        view.displayNoResults();
    }

    @Override
    public void searchByProgramme(View view, String programme) {
        view.showLoading();
        Single.fromCallable(() -> doSearch("All", programme, "Any"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modules -> displayResults(view, modules));
    }

    @Override
    public void searchBySpecialization(View view, String specialization) {
        view.showLoading();
        Single.fromCallable(() -> doSearch("All", "Any", specialization))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modules -> displayResults(view, modules));
    }

    @Override
    public void searchByYear(View view, String year) {
        view.showLoading();
        Single.fromCallable(() -> doSearch(year, "Any", "Any"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modules -> displayResults(view, modules));
    }

    @Override
    public void search(View view, String year, String programme, String specialization) {
        view.showLoading();
        Single.fromCallable(() -> doSearch(year, programme, specialization))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modules -> displayResults(view, modules));
    }

    private List<Module> doSearch(String year, String programme, String specialization) {
        List<Module> modules = repo.getModules();
        String y = year.replaceAll("\\D+", "");
        return stream(modules)
                .filter(m -> y.isEmpty() || (m.getYear() == parseInt(y)))
                .filter(m -> withinProgramme(m.getProgramme(), programme))
                .filter(m -> specialization.equals("Any") || m.getSpecializations().isEmpty() || m.getSpecializations().contains(specialization))
                .collect(toList());
    }

    private boolean withinProgramme(String mProgramme, String programme) {
        return programme.equals("Any") ||
                programme.equals(mProgramme) ||
                programme.contains("Diploma") && mProgramme.contains("Certificate") ||
                programme.contains("Bachelor") && mProgramme.contains("Diploma") ||
                programme.contains("Bachelor") && mProgramme.contains("Certificate");
    }

    /**
     * Determines how the results will be displayed in the view.
     * @param view the view to display in.
     * @param results the results to display.
     */
    private void displayResults(View view, List<Module> results) {
        Toast.makeText(MainActivity.instance, "Results size: " + results.size(), Toast.LENGTH_SHORT).show();
        if (results.isEmpty())
            view.displayNoResults();
        else
            view.displayResults(results);
        view.hideLoading();
    }
}