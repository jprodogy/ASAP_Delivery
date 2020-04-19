package com.example.asap_delivery.data;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asap_delivery.R;
import com.example.asap_delivery.data.model.LoggedInUser;
import com.example.asap_delivery.ui.login.LoginActivity;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource extends AppCompatActivity {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            auth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(LoginDataSource.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                // there was an error
                                Toast.makeText(LoginDataSource.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

            LoggedInUser fakeUser = new LoggedInUser(UUID.randomUUID().toString(), username.split("@")[0]);
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {


            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public Result<LoggedInUser> loginChef(String username, String password) {
        final ArrayList<String> chefList = new ArrayList<String>(Arrays.asList("jboudre1", "jaboudreauxjr"));
        final String userName = username.split("@")[0];
        try {
            // TODO: handle loggedInUser authentication

            auth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(LoginDataSource.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful() || !chefList.contains(userName)) {
                                // there was an error
                                Toast.makeText(LoginDataSource.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

            LoggedInUser fakeUser = new LoggedInUser(java.util.UUID.randomUUID().toString(), username.split("@")[0], true);
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {


            return new Result.Error(new IOException("Error logging in", e));
        }
    }


    public void logout() {
        // TODO: revoke authentication
    }
}
