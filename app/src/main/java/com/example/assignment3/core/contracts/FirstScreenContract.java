package com.example.assignment3.core.contracts;

/**
 * A contract to define a view and
 * presenter for the first screen.
 */
public interface FirstScreenContract {

    /**
     * Definition of what a view for the
     * first screen should do.
     */
    interface View {

        /**
         * This method should enable the ui element
         * to allow the user to submit.
         */
        void enableSubmit();

        /**
         * This method should disable the ui element
         * to disallow a user to submit.
         */
        void disableSubmit();

        /**
         * This method should display the disclaimer
         * to the user.
         * @param disclaimer the disclaimer to display.
         */
        void displayDisclaimer(String disclaimer);

        /**
         * This method should display the home screen
         * to the user.
         */
        void displayHomeScreen();
    }

    /**
     * Definition of what a presenter for the
     * first screen should do.
     */
    interface Present {

        /**
         * This method should be called to initialize
         * the view for the user.
         * @param view presented view.
         */
        void initialize(View view);

        /**
         * This method should be called when the user
         * accepts or declines the disclaimer.
         * @param view presented view.
         * @param isAccepted true if disclaimer accepted.
         */
        void disclaimerAccepted(View view, boolean isAccepted);

        /**
         * This method should be called when the user
         * has submitted their name.
         * @param view presented view.
         * @param name the user's name.
         */
        void onSubmit(View view, String name);
    }
}