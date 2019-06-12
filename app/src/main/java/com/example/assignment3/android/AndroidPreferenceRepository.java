package com.example.assignment3.android;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.assignment3.repo.PreferenceRepository;

import java8.util.Optional;

import static java8.util.Optional.ofNullable;

/**
 * Implementation of the Android preference repository.
 */
public class AndroidPreferenceRepository implements PreferenceRepository {

    final private static String PREFERENCE_NAME = "WintecPathFinder-Preferences";
    final private static String USERNAME_KEY = "WintecPathFinder-Username";

    private SharedPreferences preference;

    public AndroidPreferenceRepository(Context androidContext) {
        preference = androidContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public Optional<String> loadUsername() {
        String username = preference.getString(USERNAME_KEY, null);
        return ofNullable(username);
    }

    @Override
    public void saveUsername(String username) {
        preference.edit()
                .putString(USERNAME_KEY, username)
                .apply();
    }
}