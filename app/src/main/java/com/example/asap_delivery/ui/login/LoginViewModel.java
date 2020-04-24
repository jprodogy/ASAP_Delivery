package com.example.asap_delivery.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;
import android.util.Patterns;

import com.example.asap_delivery.data.LoginRepository;
import com.example.asap_delivery.data.Result;
import com.example.asap_delivery.R;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;
    private DatabaseReference mRef;


    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }


    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<FirebaseUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            FirebaseUser data = ((Result.Success<FirebaseUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getEmail())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));

        }
    }

    public void loginChef(String username, String password) {
        // can be launched in a separate asynchronous job


        Result<FirebaseUser> result = loginRepository.loginChef(username, password);

        if (result instanceof Result.Success) {
            final FirebaseUser data = ((Result.Success<FirebaseUser>) result).getData();
            final boolean isChef = (((Result.Success<Boolean>) result).getChef());
            mRef = FirebaseDatabase.getInstance().getReference("Data3");

            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getEmail()), isChef));

        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));

        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
