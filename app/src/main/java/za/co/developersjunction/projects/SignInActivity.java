package za.co.developersjunction.projects;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import layout.SignIn;

public class SignInActivity extends AppCompatActivity
{
    public final String TAG_LOGIN = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        SignIn frag_signIn = new SignIn();

        ft.add(R.id.container,frag_signIn,TAG_LOGIN);
        ft.commit();

    }
}
