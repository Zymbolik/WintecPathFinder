package com.example.assignment3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignment3.core.contracts.SetupContract;
import com.example.assignment3.core.contracts.SetupContract.Presenter;
import com.example.assignment3.core.present.SetupPresenter;

public class SetupScreen extends Fragment implements SetupContract.View {

    private EditText editFirstName;
    private EditText editLastName;
    private Button btnNext;

    private Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_setup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // set up ui elements.
        editFirstName = view.findViewById(R.id.first_name_edit_text);
        editLastName =  view.findViewById(R.id.last_name_edit_text);
        btnNext =  view.findViewById(R.id.nextButton);

        // setting up actions.
        btnNext.setOnClickListener(ignored -> doOnSubmit());
        editFirstName.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                doFirstNameCheck(s.toString());
            }
        });

        // initializing presenter.
        presenter = new SetupPresenter(MainActivity.preferences);
        presenter.initialize(this);
    }


    @Override
    public void enableSubmit() {
        btnNext.setEnabled(true);
    }

    @Override
    public void disableSubmit() {
        btnNext.setEnabled(false);
    }

    @Override
    public void displayHomeScreen() {
        MainActivity.instance.changePage(new HomeScreen());
    }

    private void doFirstNameCheck(String firstName) {
        presenter.onFirstNameCheck(this, firstName);
    }

    private void doOnSubmit() {
        String firstName = editFirstName.getText().toString();
        String lastName = editLastName.getText().toString();
        presenter.onSubmit(this, firstName, lastName);
    }
}