package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.HomeContract;
import com.example.assignment3.core.contracts.HomeContract.View;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.core.domain.SelectedModules;
import com.example.assignment3.core.repo.PreferenceRepository;

import java.util.List;

import static java8.util.Objects.requireNonNull;

public class HomePresenter implements HomeContract.Presenter {

    private PreferenceRepository prefRepo;
    private SelectedModules selected;

    public HomePresenter(PreferenceRepository prefRepo, SelectedModules selected) {
        this.prefRepo = requireNonNull(prefRepo);
        this.selected = requireNonNull(selected);
    }

    @Override
    public void initialize(View view) {
        String username = prefRepo.loadFirstName().orElse("Guest");
        view.displayUsername(username);
        List<Module> modules = selected.getModules();
        if (modules.isEmpty()) {
            view.displayHeading("Looks like you have no modules");
            view.displayNoModules();
        }
        else {
            view.displayHeading("Here are your current modules");
            view.displayModules(modules);
        }
    }

    @Override
    public void onAddModules(View view) {
        view.displaySearchScreen();
    }
}
