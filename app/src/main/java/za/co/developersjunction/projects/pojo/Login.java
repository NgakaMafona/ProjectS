package za.co.developersjunction.projects.pojo;

/**
 * Created by Codetribe on 10/31/2016.
 */

public class Login
{
    private int id;
    private String email;
    private String password;

    public Login()
    {

    }

    public Login(int id, String email, String password)
    {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Login(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
