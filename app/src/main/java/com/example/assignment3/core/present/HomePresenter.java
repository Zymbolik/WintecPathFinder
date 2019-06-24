package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.HomeContract;
import com.example.assignment3.core.contracts.HomeContract.View;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.core.repo.ModulesRepository;
import com.example.assignment3.core.repo.PreferenceRepository;

import java.util.List;

import static java8.util.Objects.requireNonNull;

public class HomePresenter implements HomeContract.Presenter {

    private PreferenceRepository prefRepo;
    private ModulesRepository modulesRepo;

    public HomePresenter(PreferenceRepository prefRepo, ModulesRepository modulesRepo) {
        this.prefRepo = requireNonNull(prefRepo);
        this.modulesRepo = requireNonNull(modulesRepo);
    }

    @Override
    public void initialize(View view) {
        String username = prefRepo.loadFirstName().orElse("Guest");
        view.displayUsername(username);
        List<Module> modules = modulesRepo.getModules();
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

    }
}
