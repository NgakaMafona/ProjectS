package layout;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.developersjunction.projects.R;
import za.co.developersjunction.projects.loginTest;
import za.co.developersjunction.projects.pojo.user.User;
import za.co.developersjunction.projects.utils.Validations;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileSetUp extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener
{

    //Bind edit text views
    EditText first_name;
    EditText last_name;
    EditText id_number;

    //Bind RadioGroup
    @BindView(R.id.rdg_gender)RadioGroup rdg_gender;

    Button btn_create;

    TextInputLayout til_name;
    TextInputLayout til_surname;
    TextInputLayout til_idNumber;

    String gender;
    String name;
    String surname;
    String str_id;

    View v;

    private DatabaseReference db;
    private FirebaseAuth mFirebaseAuth;

    public ProfileSetUp()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        v  = inflater.inflate(R.layout.fragment_profile_set_up, container, false);

        //ButterKnife.bind(this,v);

        first_name = (EditText) v.findViewById(R.id.edt_pro_firstname);
        last_name = (EditText) v.findViewById(R.id.edt_pro_lastname);
        id_number = (EditText) v.findViewById(R.id.edt_pro_idNumber);

        til_name = (TextInputLayout) v.findViewById(R.id.til_pro_fname);
        til_surname = (TextInputLayout) v.findViewById(R.id.til_pro_lname);
        til_idNumber = (TextInputLayout) v.findViewById(R.id.til_pro_idNumber);

        rdg_gender = (RadioGroup) v.findViewById(R.id.rdg_gender);
        btn_create = (Button) v.findViewById(R.id.btn_create_pro);

        btn_create.setOnClickListener(this);
        rdg_gender.setOnCheckedChangeListener(this);

        mFirebaseAuth = FirebaseAuth.getInstance();

        return v;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i)
    {
        RadioButton btn_selected = (RadioButton) v.findViewById(i);

        String txt = btn_selected.getText().toString();

        if(txt.equalsIgnoreCase("male"))
        {
            gender = "Male";
        }
        else if(txt.equalsIgnoreCase("female"))
        {
            gender = "Female";
        }
    }

    @Override
    public void onClick(View view)
    {

        int btn_id = view.getId();



        if(btn_id == R.id.img_btn_back)
        {
            SignUp signUp = new SignUp();

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ft.add(R.id.container,signUp);
            ft.commit();

        }
        else
        {
            Validations val = new Validations();

            name = first_name.getText().toString();
            surname = last_name.getText().toString();
            str_id = id_number.getText().toString();

            boolean valid_name = false;
            boolean valid_surname = false;
            boolean valid_id = false;

            if(name.equalsIgnoreCase(""))
            {
                til_name.setError("Enter Your Name");
            }
            else
            {
                til_name.setError("");

                valid_name = val.validString(name);

                if(!valid_name)
                {
                    til_name.setError("No numbers or special characters allowed");
                }
                else
                {
                    til_name.setError("");
                }
            }

            if(surname.equalsIgnoreCase(""))
            {
                til_surname.setError("Enter Your Surname");
            }
            else
            {
                til_surname.setError("");
                valid_surname = val.validString(surname);

                if(!valid_surname)
                {
                    til_name.setError("No numbers or special characters allowed");
                }
                else
                {
                    til_surname.setError("");
                }
            }

            if(str_id.equalsIgnoreCase(""))
            {
                til_idNumber.setError("Enter Your ID Number");
            }
            else
            {
                til_idNumber.setError("");

                valid_id = val.isValidID(str_id);

                if(!valid_id)
                {
                    til_idNumber.setError("Enter a valid ID number");
                }
                else
                {
                    til_idNumber.setError("");
                }
            }

            if(valid_name && valid_surname && valid_id)
            {
                Date date = new Date();

                String date_cr = date.toString();
                String date_mod = date.toString();

                User u = new User(date_cr,date_mod,name,surname,gender,str_id);

                addUser(u);

                startActivity(new Intent(getActivity(),loginTest.class));
            }
        }

    }

    private void addUser(User u)
    {
        User user = new User();
        Map<String,Object> map = new HashMap<String,Object>();

        //Retrieve user details in hashmap
        map = user.toMap(u);

        db = FirebaseDatabase.getInstance().getReference();


        //Add user details to the Users table
        db.child("User").child(mFirebaseAuth.getCurrentUser().getUid()).updateChildren(map);

        Toast.makeText(getActivity(),"Profile created",Toast.LENGTH_LONG).show();
    }
}
