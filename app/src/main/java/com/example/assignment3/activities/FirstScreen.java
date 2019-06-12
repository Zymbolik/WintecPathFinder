package com.example.assignment3.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignment3.R;
import com.example.assignment3.android.AndroidPreferenceRepository;
import com.example.assignment3.contracts.FirstScreenContract;
import com.example.assignment3.present.FirstScreenPresent;
import com.example.assignment3.repo.PreferenceRepository;

public class FirstScreen extends AppCompatActivity implements FirstScreenContract.View {

    private FirstScreenContract.Present presenter;

    private TextView textDisclaimer;
    private EditText editName;
    private CheckBox checkDisclaimer;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);

        textDisclaimer = findViewById(R.id.fs_textDisclaimer);
        editName = findViewById(R.id.fs_editName);

        checkDisclaimer = findViewById(R.id.fs_checkDisclaimer);
        checkDisclaimer.setOnCheckedChangeListener(this::notifyDisclaimerChecked);

        submitButton = findViewById(R.id.fs_submitButton);
        submitButton.setOnClickListener(this::notifyUsernameSubmitted);

        PreferenceRepository preference = new AndroidPreferenceRepository(getApplicationContext());
        presenter = new FirstScreenPresent(preference);
        presenter.initialize(this);
    }

    // START OF VIEW INTERFACE
    @Override
    public void displayHomeScreen(){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void enableSubmit() {
        submitButton.setEnabled(true);
    }

    @Override
    public void disableSubmit() {
        submitButton.setEnabled(false);
    }

    @Override
    public void displayDisclaimer(String disclaimer) {
        textDisclaimer.setText(disclaimer);
    }
    // END OF VIEW INTERFACE

    /**
     * Notifies the presenter that the disclaimer has been
     * checked.
     */
    private void notifyDisclaimerChecked(@SuppressWarnings("unused") CompoundButton ignored, boolean isChecked) {
        presenter.disclaimerAccepted(this, isChecked);
    }

    /**
     * Notifies the presenter that the username has been
     * submitted.
     */
    private void notifyUsernameSubmitted(@SuppressWarnings("unused") View ignored) {
        String username = editName.getText().toString();
        presenter.onSubmit(this, username);
    }
}