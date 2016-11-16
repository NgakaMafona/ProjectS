package layout;


import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import za.co.developersjunction.projects.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment implements View.OnClickListener
{

    //EditText global declaration
    private EditText edt_sign_in_email;
    private EditText edt_sign_in_password;
    private TextView tv_sign_up;

    //TextInputLayout global declaration
    private TextInputLayout til_email;
    private TextInputLayout til_pass;

    //Button global declaration
    private Button btn_google_signUp;
    private Button btn_on_signUp;

    //Firebase global declaration
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    public SignUp()
    {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);

        //Initialise EditTexts
        edt_sign_in_email = (EditText) v.findViewById(R.id.edt_sign_in_email);
        edt_sign_in_password = (EditText) v.findViewById(R.id.edt_sign_in_password);
        tv_sign_up = (TextView) v.findViewById(R.id.edt_sign_up);

        //initialise buttons
        btn_on_signUp = (Button) v.findViewById(R.id.btn_email_sign_up);
        btn_google_signUp = (Button) v.findViewById(R.id.btn_google_sign_up);

        //initialise input layouts
        til_email = (TextInputLayout) v.findViewById(R.id.til_logEmail);
        til_pass = (TextInputLayout) v.findViewById(R.id.til_logPassword);

        //set On click listeners for the buttons
        btn_google_signUp.setOnClickListener(this);
        btn_on_signUp.setOnClickListener(this);

        mFirebaseAuth = FirebaseAuth.getInstance();

        //request email addresses from local email handler
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mAuthStateListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null)
                {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                }
                else
                {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        return v;
    }

    @Override
    public void onClick(View view)
    {
        int btn_id = view.getId();

        if(btn_id == R.id.btn_google_sign_up)
        {

        }
        else if(btn_id ==  R.id.btn_email_sign_up)
        {

        }

    }

    //Manually manage account connection
    @Override
    public void onStart()
    {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        mGoogleApiClient.disconnect();
    }
}
