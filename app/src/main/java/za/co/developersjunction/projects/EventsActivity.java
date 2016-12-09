package za.co.developersjunction.projects;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import za.co.developersjunction.projects.pojo.events.Events;
import za.co.developersjunction.projects.utils.EventsAdapter;
import za.co.developersjunction.projects.utils.TestAdapter;

public class EventsActivity extends Activity
{
    //string to hold the selected category
    private String category;

    //list view to display a returned list
    private ListView lv;

    FirebaseDatabase mDatabase;
    DatabaseReference mDataRef;

    HashMap<String, Object> map;
    ArrayList<Events> event_List;
    EventsAdapter adapter;

    private FirebaseAuth mFirebaseAuth;

    ArrayList<HashMap<String,Object>> testArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        lv = (ListView) findViewById(R.id.list_item);

        mFirebaseAuth = FirebaseAuth.getInstance();

        Intent i = getIntent();
        category = i.getStringExtra(loginTest.CATAGORY_TAG);

        Toast.makeText(this, category, Toast.LENGTH_LONG).show();

        //mDatabase = FirebaseDatabase.getInstance().getReference().child("event");
        mDataRef = FirebaseDatabase.getInstance().getReference().child("event");

        Query query = mDataRef.orderByChild("ev_type").equalTo(category);

        event_List = new ArrayList<>();

        Log.e("Step 1: "," Getting Query ");

        query.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                Log.e("Step 2: "," Putting data in the map ");

                map = (HashMap<String,Object>)dataSnapshot.getValue();

                /*for(DataSnapshot data : dataSnapshot.getChildren())
                {
                    Log.e("1 Getting data"," NOW");



                }*/


                Log.e("Step 3: "," Adding data to Event Class ");
                Events event =new Events();

                event.setEv_name(String.valueOf(map.get("ev_name")));
                event.setEv_desc(String.valueOf(map.get("ev_desc")));
                event.setEv_type(String.valueOf(map.get("ev_type")));
                event.setEv_date_cr(String.valueOf(map.get("ev_date_cr")));
                event.setEv_date_mod(String.valueOf(map.get("ev_date_mod")));

                event_List.add(event);

                //ArrayAdapter<String> adap = new ArrayAdapter<String>();

                adapter = new EventsAdapter(getBaseContext(),event_List);

                //TestAdapter tAdapter = new TestAdapter(getBaseContext(),map);

                Log.e("Step 4: "," Setting Adapter ");
                lv.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });

       mDataRef.addValueEventListener(new ValueEventListener()
       {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot)
           {


           }

           @Override
           public void onCancelled(DatabaseError databaseError)
           {

           }
       });


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

    private void signOut()
    {
        mFirebaseAuth.signOut();

    }

}
