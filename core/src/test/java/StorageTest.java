import org.junit.Test;
import static org.junit.Assert.*;

public class StorageTest {
    Storage storage = Storage.getInstance();

    @Test
    public void testSave() {
        User user = new User("login", "pass", "name");
        storage.save(user);
        User dbUser = storage.findById(storage.findIDByLogin(user.getLogin()));
        user.setId(dbUser.getId());
        assertEquals(user, dbUser);
    }
}
