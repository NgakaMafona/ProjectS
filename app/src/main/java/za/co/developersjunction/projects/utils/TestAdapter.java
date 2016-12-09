package za.co.developersjunction.projects.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.HashMap;

import za.co.developersjunction.projects.R;
import za.co.developersjunction.projects.pojo.events.Events;

/**
 * Created by Phil on 12/9/2016.
 */

public class TestAdapter extends BaseAdapter
{
    private HashMap<String,Object> mData = new HashMap<String,Object>();
    private String[] mKeys;

    Context context;

    public TestAdapter(Context context,HashMap<String,Object> data)
    {
        mData = data;
        mKeys = mData.keySet().toArray(new String[data.size()]);

        this.context = context;
    }

    @Override
    public int getCount()
    {
        return mData.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mData.get(mKeys[position]);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent)
    {
        String key = mKeys[i];
        String value = getItem(i).toString();

        View listItemView = convertView;

        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(context).inflate(R.layout.event_list_items, parent, false);

        }

        Events current_event = (Events) getItem(i);
        TextView tv_name = (TextView) listItemView.findViewById(R.id.item_name);
        tv_name.setText(current_event.getEv_name());

        TextView tv_desc = (TextView) listItemView.findViewById(R.id.item_desc);
        tv_desc.setText(current_event.getEv_desc());

        return listItemView;
    }
}
