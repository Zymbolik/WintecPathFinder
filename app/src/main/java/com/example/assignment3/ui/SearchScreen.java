package com.example.assignment3.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.assignment3.R;
import com.example.assignment3.core.contracts.SearchContract;
import com.example.assignment3.core.domain.Module;

import java.util.List;

import java8.util.stream.StreamSupport;


public class SearchScreen  extends Fragment implements SearchContract.View {

    private Spinner spinnerProgrammes;
    private Spinner spinnerYears;
    private Spinner spinnerSpecializations;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // initialize ui elements
        spinnerProgrammes = view.findViewById(R.id.ss_program_spinner);
        String[] programmes = getResources().getStringArray(R.array.programmes);
        spinnerProgrammes.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, programmes));

        spinnerYears = view.findViewById(R.id.ss_year_spinner);
        int[] intYears = getResources().getIntArray(R.array.years);
        String[] years = getResources().getStringArray(R.array.years);
        spinnerYears.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, years));

        spinnerSpecializations = view.findViewById(R.id.ss_spec_spinner);
        String[] specializations = getResources().getStringArray(R.array.specializations);
        spinnerSpecializations.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, specializations));
    }

    @Override
    public void displayResults(List<Module> modules) {

    }

    @Override
    public void displayNoResults() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}