package com.example.assignment3.core.contracts;

public interface ModuleContract {

    interface View {

        void setPresenter(Presenter presenter);

        void displayModuleCode(String moduleCode);

        void displayModuleName(String moduleName);

        void displayModuleLevel(String moduleLevel);

        void displayModuleStatus(String moduleStatus);

        void expandDetails();

        void collapseDetails();
    }

    interface Presenter {

        void initialize(View view);

        void onExpandDetails(View view);

        void onCollapseDetails(View view);
    }
}