package hty.example.updownfiles2.entity.person;

public class user {
    /*public user()
    {

    }*/
    public user(String id,String username, String passwd) {
        this.id = id;
        this.username = username;
        this.passwd = passwd;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    private String username;
    public String getId() {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    private String id;

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "user{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }

    public String getPasswd() {
        return passwd;
    }
    private String passwd;
}
