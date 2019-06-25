package com.example.assignment3.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.assignment3.R;
import com.example.assignment3.core.contracts.SearchContract;
import com.example.assignment3.core.domain.Module;
import com.example.assignment3.core.domain.SampleModulesRepository;
import com.example.assignment3.core.present.ModulePresenter;
import com.example.assignment3.core.present.SearchPresenter;
import com.example.assignment3.ui.controls.CourseCard;

import java.io.IOException;
import java.io.InputStream;
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
    private Button btnSearch;

    private SearchPresenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // initialize ui elements
        spinnerProgrammes = view.findViewById(R.id.ss_program_spinner);
        String[] programmes = getResources().getStringArray(R.array.programmes);
        spinnerProgrammes.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, programmes));

        spinnerYears = view.findViewById(R.id.ss_year_spinner);
        String[] years = getResources().getStringArray(R.array.years);
        spinnerYears.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, years));

        spinnerSpecializations = view.findViewById(R.id.ss_spec_spinner);
        String[] specializations = getResources().getStringArray(R.array.specializations);
        spinnerSpecializations.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, specializations));

        searchResults = view.findViewById(R.id.ss_results);
        textNoResults = view.findViewById(R.id.ss_no_results);
        scrollResults = view.findViewById(R.id.ss_search_scroll);
        progressLoading = view.findViewById(R.id.ss_loading);
        btnSearch = view.findViewById(R.id.ss_search_button);

        // set up ui actions.
        btnSearch.setOnClickListener(ignored -> doSearch());

        // initialize presenter.
        try {
            InputStream is = getContext().getAssets().open("Modules.txt");
            presenter = new SearchPresenter(new SampleModulesRepository(is));
            presenter.initialize(this);
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void displayResults(List<Module> modules) {
        scrollResults.setVisibility(View.VISIBLE);
        textNoResults.setVisibility(View.INVISIBLE);
        searchResults.removeAllViews();
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

    private void doSearch() {
        String year = spinnerYears.getSelectedItem().toString();
        String programme = spinnerProgrammes.getSelectedItem().toString();
        String specialization = spinnerSpecializations.getSelectedItem().toString();
        presenter.search(this, year, programme, specialization);
    }
}