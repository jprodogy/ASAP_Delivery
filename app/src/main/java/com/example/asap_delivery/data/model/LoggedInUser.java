package com.example.asap_delivery.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private Boolean isChef = false;

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }
    public LoggedInUser(String userId, String displayName, Boolean isChef) {
        this.userId = userId;
        this.displayName = displayName;
        this.isChef = isChef;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Boolean getIsChef(){return isChef;}
}
