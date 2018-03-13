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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (!name.equals(user.name)) return false;
        return !(id != null ? !id.equals(user.id) : user.id != null);

    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}

