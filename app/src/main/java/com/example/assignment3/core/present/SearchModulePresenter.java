package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.ModuleContract.View;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.core.domain.SelectedModules;

public class SearchModulePresenter extends AbstractModulePresenter {

    public SearchModulePresenter(Module module, SelectedModules selected) {
        super(module, selected);
    }

    @Override
    public void onSelected(View view) {
        getSelected().addModule(getModule());
        initialize(view);
        view.enableSelected("Module added");
    }

    @Override
    public void onDeselected(View view) {
        getSelected().removeModule(getModule());
        initialize(view);
        view.disableSelected("Module removed");
    }
}