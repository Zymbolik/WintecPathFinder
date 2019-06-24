package com.example.assignment3.core.present;

import com.example.assignment3.core.contracts.ToolbarContract;
import com.example.assignment3.core.contracts.ToolbarContract.View;

public class ToolbarPresenter implements ToolbarContract.Presenter {

    @Override
    public void initialize(View view) {
        view.displaySearchUnfocused();
        view.displayProfileUnfocused();
    }

    @Override
    public void onSearchClicked(View view) {
        view.displaySearchFocused();
        view.displayProfileUnfocused();
        view.displaySearch();
    }

    @Override
    public void onHomeClicked(View view) {
        view.displaySearchUnfocused();
        view.displayProfileUnfocused();
        view.displayHome();
    }

    @Override
    public void onProfileClicked(View view) {
        view.displayProfileFocused();
        view.displaySearchUnfocused();
        view.displayProfile();
    }
}