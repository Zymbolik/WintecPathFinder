package com.example.assignment3.ui;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.assignment3.R;
import com.example.assignment3.core.contracts.SearchContract;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.core.present.ModulePresenter;
import com.example.assignment3.ui.controls.CourseCard;

import java.util.List;

import static java8.util.stream.StreamSupport.stream;


public class SearchScreen  extends Fragment implements SearchContract.View {

    private Spinner spinnerProgrammes;
    private Spinner spinnerYears;
    private Spinner spinnerSpecializations;
    private LinearLayout searchResults;
    private ProgressBar progressLoading;

    private ScrollView scrollResults;
    private TextView textNoResults;

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

        searchResults = view.findViewById(R.id.ss_results);
        textNoResults = view.findViewById(R.id.ss_no_results);
        scrollResults = view.findViewById(R.id.ss_search_scroll);
        progressLoading = view.findViewById(R.id.ss_loading);

    }

    @Override
    public void displayResults(List<Module> modules) {
        scrollResults.setVisibility(View.VISIBLE);
        textNoResults.setVisibility(View.INVISIBLE);
        stream(modules)
                .map(ModulePresenter::new)
                .forEach(this::appendCourseCard);
    }

    @Override
    public void displayNoResults() {
        textNoResults.setVisibility(View.VISIBLE);
        scrollResults.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoading() {
        progressLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressLoading.setVisibility(View.INVISIBLE);
    }

    private void appendCourseCard(ModulePresenter modulePresenter) {
        CourseCard card = new CourseCard();
        card.setPresenter(modulePresenter);
        getFragmentManager()
                .beginTransaction()
                .add(searchResults.getId(), card)
                .commit();
    }
}