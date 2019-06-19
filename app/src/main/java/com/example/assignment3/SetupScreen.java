package com.example.assignment3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment3.android.AndroidPreferenceRepository;
import com.example.assignment3.core.contracts.SetupContract;
import com.example.assignment3.core.contracts.SetupContract.Presenter;
import com.example.assignment3.core.present.SetupPresenter;

public class SetupScreen extends AppCompatActivity implements SetupContract.View {

    private EditText editFirstName;
    private EditText editLastName;
    private Button btnNext;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setup);

        // initializing components.
        editFirstName = findViewById(R.id.first_name_edit_text);
        editLastName = findViewById(R.id.last_name_edit_text);
        btnNext = findViewById(R.id.nextButton);

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
        presenter = new SetupPresenter(new AndroidPreferenceRepository(this));
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
        Toast.makeText(this, "Moving to next screen!", Toast.LENGTH_SHORT).show();
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