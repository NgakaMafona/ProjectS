package za.co.developersjunction.projects;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginTest extends AppCompatActivity
{
    private FirebaseAuth mFirebaseAuth;
    private GoogleApiClient mGoogleApiClient;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null)
                {
                    // User is signed in
                    Log.d("Sign in Tag", "onAuthStateChanged:signed_in:" + user.getUid());
                }
                else
                {
                    startActivity(new Intent(loginTest.this, SignInActivity.class));

                    // User is signed out
                    Log.d("Sign Out Tag", "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    private void signOut()
    {
        mFirebaseAuth.signOut();

        //google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>()
        {
            @Override
            public void onResult(@NonNull Status status)
            {
                updateUI(null);
            }
        });
    }
    private void updateUI(FirebaseUser user)
    {
        if(user != null)
        {
            Toast.makeText(this,"User : " + user.getEmail() + " \n ID : " + user.getUid(),Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Signed Out", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.login_test_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id == R.id.sign_out)
        {
            //signOut();

            FirebaseAuth.getInstance().signOut();

            Toast.makeText(this, "Wana sign out", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this,SignInActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        FirebaseAuth.getInstance().signOut();

        if(mAuthStateListener != null)
        {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }
}
