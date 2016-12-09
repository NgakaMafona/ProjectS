package za.co.developersjunction.projects.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import za.co.developersjunction.projects.R;
import za.co.developersjunction.projects.pojo.events.Events;

/**
 * Created by Phil on 12/6/2016.
 */

public class EventsAdapter extends ArrayAdapter<Events>
{
    public EventsAdapter(Context context, List<Events> objects)
    {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;

        Log.e("Step 5: ", " Inflating ListView ");
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.event_list_items, parent, false);

        }

        Log.e("Step 6: ", "Placing data in TextViews");
        Events current_event = getItem(position);
        TextView tv_name = (TextView) listItemView.findViewById(R.id.item_name);
        tv_name.setText(current_event.getEv_name());

        TextView tv_desc = (TextView) listItemView.findViewById(R.id.item_desc);
        tv_desc.setText(current_event.getEv_desc());

        Log.e("Step 7: ", "Done");

        return listItemView;
    }
}
