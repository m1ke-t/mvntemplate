import org.junit.Test;
import static org.junit.Assert.*;


public class UserDaoInMemoryTest {

    UserDao ud = new UserDaoInMemory();

    User user0 = new User("login0", "pwd", "username0");
    User user1 = new User("login1", "pwd", "username1");
    User user2 = new User("login2", "pwd", "username2");
    User user3 = new User("login3", "pwd", "username3");

    User dbUser;
    int size;



    @Test
    public void testAdd() {

        ud.createUser(user0);
        ud.createUser(user1);
        ud.createUser(user2);
        ud.createUser(user3);

        dbUser = ud.getById(2);

        assertNotNull(ud);
        assertEquals(ud.getAll().size(), 4);
        assertEquals(user2.getId(), dbUser.getId());
        assertEquals(user2.getName(), dbUser.getName());
        assertEquals(user2.getLogin(), dbUser.getLogin());
    }

    @Test
    public void testUpdate(){
        size = ud.getAll().size();

        ud.createUser(user0);
        ud.createUser(user1);
        ud.createUser(user2);
        ud.createUser(user3);

        User userUpd = new User("login3", "pwd", "username3");
        userUpd.setId(3);


        ud.updateUser(userUpd);
        dbUser = ud.getById(3);
        assertEquals(userUpd.getId(), dbUser.getId());
        assertEquals(userUpd.getName(), dbUser.getName());
        assertEquals(userUpd.getLogin(), dbUser.getLogin());

        assertEquals(ud.getAll().size(), size + 4);

    }

    @Test (expected = NullPointerException.class)
    public void testUpdateNull() {
        ud.updateUser(null);
    }




    @Test
    public void testDeleteUserByLogin() {
       size = ud.getAll().size();
        ud.createUser(user1);
        ud.deleteUser("login1");
        assertTrue(ud.getById(1) == null);
        assertEquals(ud.getAll().size(), size);
    }
    @Test
    public void testDeleteUserWrongLogin() {
        size = ud.getAll().size();
        ud.deleteUser("nonexistentLogin");
        assertEquals(size, ud.getAll().size());
    }
    @Test
    public void testDeleteUserWrongID() {
        size = ud.getAll().size();
        ud.deleteUser(4124);
        assertEquals(size, ud.getAll().size());
    }

    @Test
    public void testGetUserByWrongID() {
        dbUser = ud.getById(1337);
        assertTrue(dbUser == null);
    }
    @Test
    public void testGetUserByID() {
        for (int i = 0; i < 10; i++) {
            ud.createUser(new User("login" + i, "pwd", "username" + i));
        }
        dbUser = ud.getById(9);
        assertTrue(dbUser != null);
    }
}
