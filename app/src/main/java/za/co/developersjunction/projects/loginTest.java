package za.co.developersjunction.projects;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class loginTest extends AppCompatActivity
{
    private FirebaseAuth mFirebaseAuth;
    private GoogleApiClient mGoogleApiClient;

    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseStorage mStorage;

    private ImageButton img_btn_corp;
    private ImageButton img_btn_soci;

    DatabaseReference mDataRef;

    StorageReference storage_ref;
    StorageReference loc_ref;
    StorageReference img_ref;

    ImageView img_cop;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test);

        //getActionBar().setDisplayHomeAsUpEnabled(true);

        img_btn_corp = (ImageButton) findViewById(R.id.img_corp);
        img_btn_soci = (ImageButton) findViewById(R.id.img_soc);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance();

        storage_ref = mStorage.getReferenceFromUrl("gs://project-s-initial.appspot.com");

        loc_ref = storage_ref.child("images");
        img_ref = storage_ref.child("images/event_type_img/corp.jpg");

        String path = img_ref.getPath();



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

            Toast.makeText(this, "signed out", Toast.LENGTH_SHORT).show();

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
