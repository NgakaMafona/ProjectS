package za.co.developersjunction.projects;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import layout.SignIn;

public class SplashActivity extends AppCompatActivity
{

    public static int SPLASH_TIME_OUT = 2000;


    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Log.e("STEP 1", "GETTING FIREBASE INSTANCE");
        mFirebaseAuth = FirebaseAuth.getInstance();
        Log.e("STEP 2", "DONE");

        //request email addresses from local email handler
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mAuthStateListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                Log.e("STEP 3", "GETTING CURRENT USER");

                FirebaseUser user = firebaseAuth.getCurrentUser();

                Log.e("STEP 4", "DONE");

                if(user != null)
                {
                    Log.e("STEP 5", "USER FOUND");

                    startActivity(new Intent(SplashActivity.this,loginTest.class));
                    finish();
                }
                else
                {
                    Log.e("STEP 6", "USER NOT FOUND");
                    startActivity(new Intent(SplashActivity.this, SignInActivity.class));
                    finish();
                }
            }
        };

        /*new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(SplashActivity.this,SignInActivity.class));
                finish();
            }
        },SPLASH_TIME_OUT);*/
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mGoogleApiClient.connect();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }

   /* @Override
    protected void onStop()
    {
        super.onStop();

        if(mAuthStateListener != null)
        {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }*/
}
