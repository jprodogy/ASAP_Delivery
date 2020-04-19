package com.example.asap_delivery.ui.login;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;
    @Nullable
    private Boolean isChef = false;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    LoginResult(@Nullable LoggedInUserView success, Boolean isChef) {
        this.success = success;
        this.isChef = isChef;
    }


    @Nullable
    LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    Boolean getIsChef() {
        return isChef;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
