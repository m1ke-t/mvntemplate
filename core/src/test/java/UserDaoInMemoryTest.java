import org.junit.BeforeClass;
import org.junit.Test;


import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class UserDaoInMemoryTest {

    private static Storage mockedStorage;
    static User user0 = new User("login0", "pwd", "username0");
    static User user1 = new User("login1", "pwd", "username1");
    static User user2 = new User("login2", "pwd", "username2");
    static User user3 = new User("login3", "pwd", "username3");

    User dbUser;

    @BeforeClass
    public static void setUp(){
        mockedStorage = mock(Storage.class);


        User dbUser0 = user0;
        user0.setId(0);
        User dbUser1 = new User("login1", "pwd", "username1");
        user0.setId(1);
        User dbUser2 = new User("login2", "pwd", "username2");
        user0.setId(2);
        User dbUser3 = new User("login3", "pwd", "username3");
        user0.setId(3);


        when(mockedStorage.findById(0)).thenReturn(dbUser0);
        when(mockedStorage.findById(1)).thenReturn(dbUser1);
        when(mockedStorage.findById(2)).thenReturn(dbUser2);
        when(mockedStorage.findById(3)).thenReturn(dbUser3);

        when(mockedStorage.save(user0)).thenReturn(dbUser0);
        when(mockedStorage.save(user1)).thenReturn(dbUser1);
        when(mockedStorage.save(user2)).thenReturn(dbUser2);
        when(mockedStorage.save(user3)).thenReturn(dbUser3);

        when(mockedStorage.deleteById(0)).thenReturn(true);
        when(mockedStorage.deleteById(1)).thenReturn(true);
        when(mockedStorage.deleteById(2)).thenReturn(true);
        when(mockedStorage.deleteById(3)).thenReturn(true);

        when(mockedStorage.deleteByLogin("login0")).thenReturn(true);
        when(mockedStorage.deleteByLogin("login1")).thenReturn(true);
        when(mockedStorage.deleteByLogin("login2")).thenReturn(true);
        when(mockedStorage.deleteByLogin("login3")).thenReturn(true);

        when(mockedStorage.getAll()).thenReturn(Arrays.asList(user0, user1, user2, user3));
    }

    UserDao ud = new UserDaoInMemory(mockedStorage);


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

    @Test (expected = NullPointerException.class)
    public void testUpdateNull() {
        ud.updateUser(null);
    }


    @Test
    public void testGetUserByWrongID() {
        dbUser = ud.getById(1337);
        assertTrue(dbUser == null);
    }
    @Test
    public void testGetUserByID() {
        dbUser = ud.getById(3);
        assertTrue(dbUser != null);
        assertEquals(dbUser, user3);
    }
}
