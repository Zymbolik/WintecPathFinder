package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.ModuleContract;
import com.example.assignment3.core.contracts.ModuleContract.View;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.core.domain.SelectedModules;

import static java8.util.Objects.requireNonNull;

public class HomeModulePresenter implements ModuleContract.Presenter {

    private Module module;
    private SelectedModules selected;

    public HomeModulePresenter(Module module, SelectedModules selected) {
        this.module = requireNonNull(module);
        this.selected = requireNonNull(selected);
    }

    @Override
    public void initialize(View view) {
        view.displayModuleCode(module.getModuleCode());
        view.displayModuleName(module.getModuleName());
        view.displayModuleLevel(Integer.toString(module.getLevel()));

        switch(module.getStatus()) {
            case NONE: view.displayModuleStatus(""); break;
            case PROGRESS: view.displayModuleStatus("In Progress"); break;
            case LOCKED: view.displayModuleStatus("Locked"); break;
            case COMPLETE: view.displayModuleStatus("Complete"); break;
            default:
        }
    }

    @Override
    public void onSelected(View view) {
        selected.completeModule(module);
        view.enableSelected();
    }

    @Override
    public void onDeselected(View view) {
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

