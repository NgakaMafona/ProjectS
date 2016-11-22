package layout;


import android.os.Bundle;
import android.app.Fragment;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.developersjunction.projects.R;
import za.co.developersjunction.projects.pojo.user.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileSetUp extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener
{

    //Bind edit text views
    @BindView(R.id.edt_pro_firstname) EditText first_name;
    @BindView(R.id.edt_pro_lastname) EditText last_name;
    @BindView(R.id.edt_pro_idNumber) EditText id_number;

    //Bind RadioGroup
    @BindView(R.id.rdg_gender)RadioGroup rdg_gender;

    Button btn_create;

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

        ButterKnife.bind(this,v);

        btn_create = (Button) v.findViewById(R.id.btn_create_pro);

        btn_create.setOnClickListener(this);

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
        //Toast.makeText(getActivity(),"I got Clicked",Toast.LENGTH_LONG).show();

        name = first_name.getText().toString();
        surname = last_name.getText().toString();
        str_id = id_number.getText().toString();

        int id = Integer.parseInt(str_id);

        User u = new User(name,surname,gender,id);

        addUser(u);

    }

    private void addUser(User u)
    {
        db = FirebaseDatabase.getInstance().getReference();

        db.child("User").child(mFirebaseAuth.getCurrentUser().getUid()).setValue(u);

        Toast.makeText(getActivity(),"Profile created",Toast.LENGTH_LONG).show();
    }
}
