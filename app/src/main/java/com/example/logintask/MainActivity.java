package com.example.logintask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.jetbrains.annotations.Nullable;

public class MainActivity extends AppCompatActivity {

    private GoogleSignInHandler googleSignInHandler;
    private FacebookSignInHandler facebookSignInHandler;
    private MicrosoftSignInHandler microsoftSignInHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGoogleSignIn();
        initFacebookSignIn();
        initMicrosoftSignIn();

        // Attach to button clicks or any trigger events
        findViewById(R.id.googleSignInButton).setOnClickListener(v -> googleSignInHandler.signIn(MainActivity.this));
        findViewById(R.id.facebookLoginButton).setOnClickListener(v -> facebookSignInHandler.signIn(MainActivity.this));
        findViewById(R.id.microsoftSignInButton).setOnClickListener(v -> microsoftSignInHandler.signIn(MainActivity.this));
    }

    private void initGoogleSignIn() {
        googleSignInHandler = new GoogleSignInHandler(this);
    }

    private void initFacebookSignIn() {
        facebookSignInHandler = new FacebookSignInHandler();
    }

    private void initMicrosoftSignIn() {
        microsoftSignInHandler = new MicrosoftSignInHandler(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Context context = this.getApplicationContext();
        // Handle Google sign-in result
        if (requestCode == GoogleSignInHandler.RC_SIGN_IN) {
            googleSignInHandler.handleResult(data, new GoogleSignInHandler.GoogleSignInResultCallback() {
                @Override
                public void onSuccess(GoogleSignInAccount account) {
                    // Handle Google sign-in success
                    Toast.makeText(context, "Google sign-in successful", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Exception exception) {
                    // Handle Google sign-in error
                    Toast.makeText(context, "Google sign-in failed", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Handle Facebook sign-in result
        facebookSignInHandler.handleResult(requestCode, resultCode, data);
    }
}
