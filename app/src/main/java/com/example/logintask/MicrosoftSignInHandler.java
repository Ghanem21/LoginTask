package com.example.logintask;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.microsoft.identity.client.AuthenticationCallback;
import com.microsoft.identity.client.IAuthenticationResult;
import com.microsoft.identity.client.IPublicClientApplication;
import com.microsoft.identity.client.PublicClientApplication;
import com.microsoft.identity.client.exception.MsalException;

import android.content.Context;
import android.widget.Toast;

public class MicrosoftSignInHandler {

    private static final String TAG = "MicrosoftSignInHandler";
    private IPublicClientApplication mPublicClientApplication;
    private final String[] SCOPES = {"User.Read"};
    private final Context context;

    public MicrosoftSignInHandler(Context context) {
        this.context = context;
        new InitializeMsalTask().execute();
    }

    private class InitializeMsalTask extends AsyncTask<Void, Void, IPublicClientApplication> {
        @Override
        protected IPublicClientApplication doInBackground(Void... voids) {
            try {
                return PublicClientApplication.create(context, R.raw.msal_config);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(IPublicClientApplication publicClientApplication) {
            mPublicClientApplication = publicClientApplication;
            // You can notify that initialization is complete here, if needed
        }
    }

    public void signIn(Activity activity) {
        if (mPublicClientApplication != null) {
            mPublicClientApplication.acquireToken(activity, SCOPES, getAuthInteractiveCallback());
        } else {
            Log.e(TAG, "PublicClientApplication is not initialized yet.");
        }
    }

    private AuthenticationCallback getAuthInteractiveCallback() {
        return new AuthenticationCallback() {
            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                Log.d(TAG, "Access Token: " + authenticationResult.getAccessToken());
                Toast.makeText(context, "Sign-in successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(MsalException exception) {
                Log.e(TAG, "Sign-in failed: " + exception.getMessage());
                Toast.makeText(context, "Sign-in failed: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "User cancelled the sign-in");
                Toast.makeText(context, "Sign-in cancelled", Toast.LENGTH_SHORT).show();
            }
        };
    }
}





