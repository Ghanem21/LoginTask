package com.example.logintask;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

public class FacebookSignInHandler {

    private final CallbackManager callbackManager;

    public FacebookSignInHandler() {
        callbackManager = CallbackManager.Factory.create();
    }

    public void signIn(Activity activity) {
        // Register the callback for login results
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Handle successful login here
                Toast.makeText(activity, "FacebookSignIn Login successful: ", Toast.LENGTH_SHORT).show();
                // You can add additional logic here, like starting a new activity or showing a message
            }

            @Override
            public void onCancel() {
                // Handle login cancellation here
                Toast.makeText(activity, "FacebookSignIn Login canceled: ", Toast.LENGTH_SHORT).show();
                // Notify user that the login was canceled
            }

            @Override
            public void onError(@NonNull FacebookException error) {
                // Handle login error here
                Toast.makeText(activity, "FacebookSignIn Login error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                // Notify user that the login failed
            }
        });

        // Start the login process with the required permissions
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email", "public_profile"));
    }

    public void handleResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}

