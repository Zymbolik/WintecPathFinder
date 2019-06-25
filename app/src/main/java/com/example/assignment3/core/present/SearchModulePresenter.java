package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.ModuleContract;
import com.example.assignment3.core.contracts.ModuleContract.View;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.core.domain.ModuleBuilder;
import com.example.assignment3.core.domain.ModuleStatus;
import com.example.assignment3.core.domain.SelectedModules;

import java.util.List;

import static java8.util.Objects.requireNonNull;
import static java8.util.stream.Collectors.toList;
import static java8.util.stream.StreamSupport.stream;

public class SearchModulePresenter implements ModuleContract.Presenter {

    private Module original;
    private Module module;
    private SelectedModules selected;

    public SearchModulePresenter(Module module, SelectedModules selected) {
        this.original = requireNonNull(module);
        this.module = original;
        this.selected = requireNonNull(selected);

    }

    @Override
    public void initialize(View view) {
        checkModuleAgainstSelected();
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
        selected.addModule(module);
        initialize(view);
        view.enableSelected();
    }

    @Override
    public void onDeselected(View view) {
        selected.removeModule(module);
        initialize(view);
        view.disableSelected();
    }

    @Override
    public void onExpandDetails(View view) {
        view.expandDetails();
    }

    @Override
    public void onCollapseDetails(View view) {
        view.collapseDetails();
    }

    private void checkModuleAgainstSelected() {
        String moduleCode = module.getModuleCode();

        // was this module previously selected and currently in progress?
        if(selected.inProgress(moduleCode))
            module = new ModuleBuilder(original).setStatus(ModuleStatus.PROGRESS).build();

        // was this module previously selected and has been completed?
        else if(selected.isCompleted(moduleCode))
            module = new ModuleBuilder(original).setStatus(ModuleStatus.COMPLETE).build();

        // was this module previously locked but the prerequisites have been completed?
        else if(selected.prerequisitesSatisfied(module))
            module = new ModuleBuilder(original).setStatus(ModuleStatus.NONE).build();

        // change the module back to it's unselected state.
        else
            module = original;
    }
}
