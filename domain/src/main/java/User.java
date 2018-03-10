/**
 * Created by Mike S. on 04.03.2018.
 */
public class User {

    private String login;
    private String password;
    private String name;
    private Integer id;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {

        return login;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
