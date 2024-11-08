package com.demo.actions;

public class Actions {
    /**
     * Page actions
     */
    private static LoginActions loginActions;
    private static AccountActions accountActions;
    private static MainActions mainActions;

    /**
     * This function returns an instance of `LoginActions`
     */
    public static LoginActions loginActions() {
        if (loginActions == null) {
            loginActions = new LoginActions();
        }
        return loginActions;
    }

    /**
     * This function returns an instance of `AccountActions`
     */
    public static AccountActions accountActions() {
        if (accountActions == null) {
            accountActions = new AccountActions();
        }
        return accountActions;
    }

    /**
     * This function returns an instance of `MainActions`
     */
    public static MainActions mainActions() {
        if (mainActions == null) {
            mainActions = new MainActions();
        }
        return mainActions;
    }
}