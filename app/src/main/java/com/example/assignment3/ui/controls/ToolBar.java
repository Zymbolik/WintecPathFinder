package com.example.assignment3.ui.controls;

import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.assignment3.HomeScreen;
import com.example.assignment3.MainActivity;
import com.example.assignment3.ProfileScreen;
import com.example.assignment3.R;
import com.example.assignment3.core.contracts.ToolbarContract;
import com.example.assignment3.core.present.ToolbarPresenter;

public class ToolBar extends Fragment implements ToolbarContract.View {

    private ImageView imageSearch;
    private ImageView imageHome;
    private ImageView imageProfile;

    private ToolbarContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.control_toolbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set up ui components.
        imageSearch = view.findViewById(R.id.tb_btn_search);
        imageHome = view.findViewById(R.id.tb_btn_home);
        imageProfile = view.findViewById(R.id.tb_btn_profile);

        // set up ui actions.
        imageSearch.setOnClickListener(ignored -> presenter.onSearchClicked(this));
        imageHome.setOnClickListener(ignored -> presenter.onHomeClicked(this));
        imageProfile.setOnClickListener(ignroed -> presenter.onProfileClicked(this));

        // set up presenter.
        presenter = new ToolbarPresenter();
        presenter.initialize(this);
    }

    @Override
    public void displaySearchFocused() {
        setTintColor(imageSearch, R.color.tb_icon_focus);
    }

    @Override
    public void displaySearchUnfocused() {
        setTintColor(imageSearch, R.color.tb_icon);
    }

    @Override
    public void displaySearch() {
        Toast.makeText(getContext(), "Search screen displayed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayProfileFocused() {
        setTintColor(imageProfile, R.color.tb_icon_focus);
    }

    @Override
    public void displayProfileUnfocused() {
        setTintColor(imageProfile, R.color.tb_icon);
    }

    private void setTintColor(ImageView image, int colorId) {
        int color = getResources().getColor(colorId);
        image.setColorFilter(color, Mode.SRC_IN);
    }

    @Override
    public void displayProfile() {
        MainActivity.instance.changePage(new ProfileScreen());
    }

    @Override
    public void displayHome() {
        MainActivity.instance.changePage(new HomeScreen());
    }
}