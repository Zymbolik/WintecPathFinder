package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.ModuleContract;
import com.example.assignment3.core.contracts.ModuleContract.View;
import com.example.assignment3.core.domain.Module;

import static java8.util.Objects.requireNonNull;

public class ModulePresenter implements ModuleContract.Presenter {

    private Module module;

    public ModulePresenter(Module module) {
        this.module = requireNonNull(module);
    }

    @Override
    public void initialize(View view) {
        view.displayModuleCode(module.getModuleCode());
        view.displayModuleName(module.getModuleName());
        view.displayModuleLevel(Integer.toString(module.getLevel()));
    }

    @Override
    public void onExpandDetails(View view) {
        view.expandDetails();
    }

    @Override
    public void onCollapseDetails(View view) {
        view.collapseDetails();
    }
}
