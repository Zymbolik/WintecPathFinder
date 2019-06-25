package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.ModuleContract.View;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.core.domain.SelectedModules;

public class HomeModulePresenter extends AbstractModulePresenter {

    public HomeModulePresenter(Module module, SelectedModules selected) {
        super(module, selected);
    }

    @Override
    public void onSelected(View view) {
        getSelected().completeModule(getModule());
        initialize(view);
        view.enableSelected("Module completed!");
    }

    @Override
    public void onDeselected(View view) {}
}