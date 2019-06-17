package test.com.h2rd.refactoring.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;

public class UserDaoUnitTest {

    UserDao userDao;

    @Test
    public void saveUserPositiveTest() {
        userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));

        boolean flag = userDao.saveUser(user);
        
        assertTrue(flag);
        
        userDao.users = null;
        userDao = null;

    }
    
    @Test
    public void saveUserDuplicateEmailNegativeTest() {
        userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        
        userDao.saveUser(user);
        
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));

         boolean flag = userDao.saveUser(user);
        
        assertFalse(flag);
        userDao.users = null;
        userDao = null;

    }

    @Test
    public void deleteUserPositiveTest() {
        userDao = UserDao.getUserDao();
        
        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        
        userDao.saveUser(user);

        try {
            boolean flag = userDao.deleteUser(user);
            assertTrue(flag);
        } catch (NullPointerException e) {
        }
        userDao.users = null;
        userDao = null;

    }
    
    @Test
    public void deleteUserNotPresentNegativeTest() {
        userDao = UserDao.getUserDao();
        
        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        
        userDao.saveUser(user);

        User user1 = new User();
        user1.setName("Fake Name");
        user1.setEmail("dummy@email.com");
        user1.setRoles(Arrays.asList("admin", "master"));

        try {
            boolean flag = userDao.deleteUser(user1);
            assertFalse(flag);
        } catch (NullPointerException e) {
        }
        userDao.users = null;
        userDao = null;

    }
    
    @Test
    public void updateUserPositiveTest() {
        userDao = UserDao.getUserDao();
        
        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        
        userDao.saveUser(user);

        User user1 = new User();
        user1.setName("Ream Name");
        user1.setEmail("fake@email.com");
        user1.setRoles(Arrays.asList("Real", "Real"));

        try {
            boolean flag = userDao.updateUser(user1);
            assertTrue(flag);
        } catch (NullPointerException e) {
        }
        userDao.users = null;
        userDao = null;

    }
    
    @Test
    public void updateUserNotPresentNegativeTest() {
        userDao = UserDao.getUserDao();
        
        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        
        userDao.saveUser(user);

        User user1 = new User();
        user1.setName("Real Name");
        user1.setEmail("dummy@email.com");
        user1.setRoles(Arrays.asList("Real", "Real"));

        try {
            boolean flag = userDao.updateUser(user1);
            assertFalse(flag);
        } catch (NullPointerException e) {
        }
        userDao.users = null;
        userDao = null;

    }
    
    @Test
    public void getUserPositiveTest() {
        userDao = UserDao.getUserDao();
        
        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        
        userDao.saveUser(user);

        try {
            List<User> list = userDao.getUsers();
            assertEquals(list.size(), 1);
        } catch (NullPointerException e) {
        }
        userDao.users = null;
        userDao = null;

    }
    
    @Test
    public void getUsersNotPresentNegativeTest() {
        userDao = UserDao.getUserDao();

        try {
            List<User> list = userDao.getUsers();
            assertEquals(list.size(), 0);
        } catch (NullPointerException e) {
        }
        userDao.users = null;
        userDao = null;

    }
    
    @Test
    public void searchUserPositiveTest() {
        userDao = UserDao.getUserDao();
        
        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        
        userDao.saveUser(user);

        try {
            User user1 = userDao.searchUser("fake@email.com");
            assertEquals(user1.getName(), "Fake Name");
            assertEquals(user1.getRoles(), Arrays.asList("admin", "master"));
            assertEquals(user1.getEmail(), "fake@email.com");
        } catch (NullPointerException e) {
        }
        userDao.users = null;
        userDao = null;

    }
    
    @Test
    public void searchUserNotPresentNegativeTest() {
        userDao = UserDao.getUserDao();
        
        try {
            User user = userDao.searchUser("fake@email.com");
            assertEquals(user, null);
        } catch (NullPointerException e) {
        }
        userDao.users = null;
        userDao = null;

    }

    
}
