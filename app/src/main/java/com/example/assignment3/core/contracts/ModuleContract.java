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

        void enableSelected(String msg);

        void disableSelected(String msg);
    }

    interface Presenter {

        void initialize(View view);

        void onSelected(View view);

        void onDeselected(View view);

        void onExpandDetails(View view);

        void onCollapseDetails(View view);
    }
}