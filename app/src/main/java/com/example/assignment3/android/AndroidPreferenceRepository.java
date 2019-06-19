package com.example.assignment3.android;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.assignment3.core.repo.PreferenceRepository;

import java8.util.Optional;

import static java8.util.Optional.ofNullable;

public class AndroidPreferenceRepository implements PreferenceRepository {

    final private static String PREFERENCES = "WintecPathfinder-Preferences";
    final private static String FIRST_NAME = "First-Name";
    final private static String LAST_NAME = "Last-Name";

    private SharedPreferences preference;

    public AndroidPreferenceRepository(Context androidContext) {
        preference = androidContext.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public Optional<String> loadFirstName() {
        String firstName = preference.getString(FIRST_NAME, null);
        return ofNullable(firstName);
    }

    @Override
    public Optional<String> loadLastName() {
        String lastName = preference.getString(LAST_NAME, null);
        return ofNullable(lastName);
    }

    @Override
    public void saveFirstName(String firstName) {
        preference.edit()
                .putString(FIRST_NAME, firstName)
                .apply();
    }

    @Override
    public void saveLastName(String lastName) {
        preference.edit()
                .putString(LAST_NAME, lastName)
                .apply();
    }
}