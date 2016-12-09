package za.co.developersjunction.projects;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class loginTest extends AppCompatActivity implements View.OnClickListener
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

    private String ev_type = "";
    private String cur_user_email;

    public static final String CATAGORY_TAG = "category";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test);

        img_btn_soci = (ImageButton) findViewById(R.id.img_soc);
        img_btn_corp = (ImageButton) findViewById(R.id.img_corp);

        Handler h = new Handler();

        h.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Animation fade_img_soc = AnimationUtils.loadAnimation(loginTest.this, R.anim.fade_in);
                img_btn_soci.setVisibility(View.VISIBLE);
                img_btn_soci.setAnimation(fade_img_soc);
                img_btn_corp.setVisibility(View.VISIBLE);
                img_btn_corp.setAnimation(fade_img_soc);
            }
        },1000);

        //set on click listerners for image buttons
        img_btn_soci.setOnClickListener(this);
        img_btn_corp.setOnClickListener(this);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance();

        FirebaseUser user = mFirebaseAuth.getCurrentUser();

        cur_user_email = user.getEmail();
       // storage_ref = mStorage.getReferenceFromUrl("gs://project-s-initial.appspot.com");

        //loc_ref = storage_ref.child("images");
        //img_ref = storage_ref.child("images/event_type_img/corp.jpg");

        //String path = img_ref.getPath();

    }

    private void signOut()
    {
        mFirebaseAuth.signOut();

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
            signOut();

            FirebaseAuth.getInstance().signOut();

            Toast.makeText(this, "signed out", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this,SignInActivity.class));
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    /*@Override
    protected void onStop()
    {
        super.onStop();

        FirebaseAuth.getInstance().signOut();

        if(mAuthStateListener != null)
        {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }*/

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        SharedPreferences sp = getSharedPreferences("event_data",MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();

        if(id == R.id.img_soc)
        {
            //Toast.makeText(this,cur_user_email,Toast.LENGTH_LONG).show();

            ev_type = "Social";

            ed.putString("user_email",cur_user_email);
            ed.putString("event_type", ev_type);
            ed.commit();

            Animation fade_out = AnimationUtils.loadAnimation(loginTest.this, R.anim.fade_out);
            img_btn_soci.setAnimation(fade_out);
            img_btn_soci.setVisibility(View.INVISIBLE);
            img_btn_corp.setAnimation(fade_out);
            img_btn_corp.setVisibility(View.INVISIBLE);

            Handler h = new Handler();
            h.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    Intent i = new Intent(loginTest.this,EventsActivity.class);
                    i.putExtra(CATAGORY_TAG,ev_type);

                    startActivity(i);
                    finish();
                }
            },1500);


        }
        else if(id == R.id.img_corp)
        {
            ev_type = "Corporate";

            ed.putString("user_email",cur_user_email);
            ed.putString("event_type", ev_type);
            ed.commit();

            Animation fade_out = AnimationUtils.loadAnimation(loginTest.this, R.anim.fade_out);
            img_btn_soci.setAnimation(fade_out);
            img_btn_soci.setVisibility(View.INVISIBLE);
            img_btn_corp.setAnimation(fade_out);
            img_btn_corp.setVisibility(View.INVISIBLE);

            Handler h = new Handler();
            h.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    Intent i = new Intent(loginTest.this,EventsActivity.class);
                    i.putExtra(CATAGORY_TAG,ev_type);

                    startActivity(i);
                    finish();
                }
            },1500);

            //Toast.makeText(this,cur_user_email,Toast.LENGTH_LONG).show();
        }
    }
}
