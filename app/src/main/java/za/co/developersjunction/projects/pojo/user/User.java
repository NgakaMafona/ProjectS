package za.co.developersjunction.projects.pojo.user;

/**
 * Created by Philemon on 11/17/2016.
 */

public class User
{
    private String name;
    private String surname;
    private String gender;
    private int id_number;

    public User()
    {

    }

    public User(String name, String surname, String gender, int id_number)
    {

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

    public int getId_number()
    {
        return id_number;
    }
}
