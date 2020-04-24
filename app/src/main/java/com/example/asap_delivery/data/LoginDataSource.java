package com.example.asap_delivery.data;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asap_delivery.R;
import com.example.asap_delivery.ui.login.LoginActivity;
import com.example.asap_delivery.ui.login.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;
import static com.google.firebase.remoteconfig.FirebaseRemoteConfig.TAG;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public Result<FirebaseUser> login(String username, String password) {
        try {
            Log.d(TAG, "login: ");
            mAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("YES", "signInWithEmail:success");
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("FAILURE", "signInWithEmail:failure", task.getException());
                                Exception e = task.getException();
                            }
                        }
                    });

            FirebaseUser user = mAuth.getCurrentUser();
            Log.d("wtf", "login: ");
            if (user == null){
                throw new Exception("Exception message");
            }
            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new FirebaseException("Error logging in", e));
        }

    }

    public Result<FirebaseUser> loginChef(String username, String password) {
        List<String> chefList = Arrays.asList("50mVYlzJiEhwMmjY3b1j5snojEs1");
        try {
            mAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("YES", "signInWithEmail:success");


                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("FAILURE", "signInWithEmail:failure", task.getException());
                            }
                        }
                    });



            FirebaseUser user = mAuth.getCurrentUser();
            if (!chefList.contains(user.getUid())){
                throw new Exception("Exception message");
            }
            return new Result.Success<>(user, true);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
