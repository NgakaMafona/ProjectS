package za.co.developersjunction.projects.pojo.user;

import android.util.Log;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Philemon on 11/17/2016.
 */

public class User
{
    private String date_cr;
    private String date_mod;
    private String name;
    private String surname;
    private String gender;
    private String id_number;

    public User()
    {

    }

    public User(String date_cr, String date_mod,String name, String surname, String gender, String id_number)
    {

        this.date_cr = date_cr;
        this.date_mod = date_mod;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.id_number = id_number;
    }


    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getGender()
    {
        return gender;
    }

    public String getId_number()
    {
        return id_number;
    }

    public String getDate_cr()
    {
        return date_cr;
    }

    public String getDate_mod()
    {
        return date_mod;
    }

    public int calcAge()
    {
        int age = 0;

        String strID = getId_number();
        String subYear = strID.substring(0,2);

        int birth_year = Integer.parseInt(subYear);

        String y = "";

        if(birth_year > 16 && birth_year <=99)
        {
            y = "19"+birth_year;
        }
        else
        {
            y = "20"+birth_year;
        }

        int bYear= Integer.parseInt(y);

        //get current year
        Date date = new Date();

        String str_year = date.toString();

        String a = str_year.substring(30);

        int current_year = Integer.parseInt(a);

        age = current_year - bYear;

        return age;
    }

    public HashMap<String,Object> toMap(User u)
    {
        HashMap<String,Object> map = new HashMap<String,Object>();

        map.put("date_created",u.getDate_cr());
        map.put("date_modified",u.getDate_mod());
        map.put("u_name",u.getName());
        map.put("u_surname",u.getSurname());
        map.put("u_gender",u.getGender());
        map.put("u_idNumber",u.getId_number());
        map.put("u_age",""+u.calcAge());

        return map;
    }
}
