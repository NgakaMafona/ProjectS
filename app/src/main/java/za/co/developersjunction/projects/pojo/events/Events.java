package za.co.developersjunction.projects.pojo.events;

import java.io.Serializable;
import java.util.HashMap;

import za.co.developersjunction.projects.pojo.user.User;

/**
 * Created by Phil on 12/6/2016.
 */

public class Events implements Serializable
{
    private String ev_date_cr;
    private String ev_date_mod;
    private String ev_name;
    private String ev_desc;
    private String ev_type;

    public Events()
    {

    }

    public Events(String ev_name, String ev_desc)
    {
        this.ev_name = ev_name;
        this.ev_desc = ev_desc;
    }

    public Events(String ev_date_cr, String ev_date_mod, String ev_name, String ev_desc, String ev_type)
    {

        this.ev_date_cr = ev_date_cr;
        this.ev_date_mod = ev_date_mod;
        this.ev_name = ev_name;
        this.ev_desc = ev_desc;
        this.ev_type = ev_type;
    }

    public String getEv_date_cr()
    {
        return ev_date_cr;
    }

    public String getEv_date_mod()
    {
        return ev_date_mod;
    }

    public String getEv_name()
    {
        return ev_name;
    }

    public String getEv_desc()
    {
        return ev_desc;
    }

    public String getEv_type()
    {
        return ev_type;
    }

    public void setEv_date_cr(String ev_date_cr)
    {
        this.ev_date_cr = ev_date_cr;
    }

    public void setEv_date_mod(String ev_date_mod)
    {
        this.ev_date_mod = ev_date_mod;
    }

    public void setEv_name(String ev_name)
    {
        this.ev_name = ev_name;
    }

    public void setEv_desc(String ev_desc)
    {
        this.ev_desc = ev_desc;
    }

    public void setEv_type(String ev_type)
    {
        this.ev_type = ev_type;
    }

    public HashMap<String,Object> toMap(Events e)
    {
        HashMap<String,Object> map = new HashMap<String,Object>();

        map.put("e_date_created",e.ev_date_cr);
        map.put("e_date_modified",e.ev_date_mod);
        map.put("e_name",e.ev_name);
        map.put("e_desc",e.ev_desc);
        map.put("e_type",e.ev_type);

        return map;
    }

    @Override
    public String toString()
    {
        return "Events{" +
                "ev_date_cr='" + ev_date_cr + '\'' +
                ", ev_date_mod='" + ev_date_mod + '\'' +
                ", ev_name='" + ev_name + '\'' +
                ", ev_desc='" + ev_desc + '\'' +
                ", ev_type='" + ev_type + '\'' +
                '}';
    }
}
