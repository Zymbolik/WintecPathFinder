package com.example.assignment3.repo;

import java8.util.Optional;

/**
 * Definition of a repository to access preference
 * values.
 */
public interface PreferenceRepository {

    /**
     * Loads the username from the persistent
     * repository.
     * @return a username or an empty optional.
     */
    Optional<String> loadUsername();

    /**
     * Saves the username to the persistent
     * repository.
     * @param username the new username.
     */
    void saveUsername(String username);
}