package layout;


import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import za.co.developersjunction.projects.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignIn extends Fragment
{

    //Global TextView variables
    private TextView tv_sign_up;

    //Sign Up tag
    public final String TAG_SIGN_UP = "sign_up";

    public SignIn()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        //Initialize Views
        tv_sign_up = (TextView) view.findViewById(R.id.edt_sign_up);

        tv_sign_up.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                SignUp frag_signUp = new SignUp();

                ft.add(R.id.container,frag_signUp,TAG_SIGN_UP);
                ft.commit();
                return false;
            }
        });

        return view;
    }

}
