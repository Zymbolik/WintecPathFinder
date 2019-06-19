package com.example.assignment3.core.repo;

import java8.util.Optional;

public interface PreferenceRepository {

    Optional<String> loadFirstName();

    Optional<String> loadLastName();

    void saveFirstName(String firstName);

    void saveLastName(String lastName);
}