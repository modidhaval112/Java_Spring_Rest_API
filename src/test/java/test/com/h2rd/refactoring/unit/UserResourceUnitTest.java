package test.com.h2rd.refactoring.unit;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Test;

import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.web.UserResource;

public class UserResourceUnitTest {

    UserResource userResource;
    UserDao userDao;

    @Test
    public void getUsersPositiveTest() {
    	
        userResource = new UserResource();
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }

        userResource.addUser("fake user", "fake@user.com", "admin,master");

        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
        
        userResource=null;
        userDao.users = null;
        userDao = null;
    }
    
    @Test
    public void getUsersNotPresentNegativeTest() {
    	
        userResource = new UserResource();
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }

        Response response = userResource.getUsers();
        Assert.assertEquals(400, response.getStatus());
        
        userResource=null;
        userDao.users = null;
        userDao = null;
    }
    
    @Test
    public void addUserPositiveTest() {
    	
        userResource = new UserResource();
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }

        Response response = userResource.addUser("fake user", "fake@user.com", "admin,master");
        Assert.assertEquals(200, response.getStatus());
        
        userResource=null;
        userDao.users = null;
        userDao = null;
    }
    
    @Test
    public void addUserEmailExistNegativeTest() {
    	
        userResource = new UserResource();
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        userResource.addUser("fake user", "fake@user.com", "admin,master");
        Response response = userResource.addUser("fake user", "fake@user.com", "admin,master");
        Assert.assertEquals(400, response.getStatus());
        
        userResource=null;
        userDao.users = null;
        userDao = null;
    }
    
    @Test
    public void addUserRoleEmptyNegativeTest() {
    	
        userResource = new UserResource();
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        Response response = userResource.addUser("fake user", "fake@user.com", "");
        Assert.assertEquals(400, response.getStatus());
        
        userResource=null;
        userDao.users = null;
        userDao = null;
    }
    
    @Test
    public void updateUserPositiveTest() {
    	
        userResource = new UserResource();
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        
        userResource.addUser("fake user", "fake@user.com", "admin,master");
        Response response = userResource.updateUser("Real user", "fake@user.com", "ceo,cfo");
        Assert.assertEquals(200, response.getStatus());
        
        userResource=null;
        userDao.users = null;
        userDao = null;
    }
    
    @Test
    public void updateUserNotFoundNegativeTest() {
    	
        userResource = new UserResource();
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        
        Response response = userResource.updateUser("Real user", "fake@user.com", "ceo,cfo");
        Assert.assertEquals(400, response.getStatus());
        
        userResource=null;
        userDao.users = null;
        userDao = null;
    }
    
    @Test
    public void updateUserNoRoleProvidedNegativeTest() {
    	
        userResource = new UserResource();
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        userResource.addUser("fake user", "fake@user.com", "admin,master");
        Response response = userResource.updateUser("Real user", "fake@user.com", "");
        Assert.assertEquals(400, response.getStatus());
        
        userResource=null;
        userDao.users = null;
        userDao = null;
    }
    
    @Test
    public void deleteUserPositiveTest() {
    	
        userResource = new UserResource();
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        
        userResource.addUser("fake user", "fake@user.com", "admin,master");
        Response response = userResource.deleteUser("fake@user.com");
        Assert.assertEquals(200, response.getStatus());
        
        userResource=null;
        userDao.users = null;
        userDao = null;
    }
    
    @Test
    public void deleteUserNotFoundNegativeTest() {
    	
        userResource = new UserResource();
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        
        userResource.addUser("fake user", "fake@user.com", "admin,master");
        Response response = userResource.deleteUser("dummy@user.com");
        Assert.assertEquals(400, response.getStatus());
        
        userResource=null;
        userDao.users = null;
        userDao = null;
    }
    
    @Test
    public void findUserPositiveTest() {
    	
        userResource = new UserResource();
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        
        userResource.addUser("fake user", "fake@user.com", "admin,master");
        Response response = userResource.findUser("fake@user.com");
        Assert.assertEquals(200, response.getStatus());
        
        userResource=null;
        userDao.users = null;
        userDao = null;
    }
    
    @Test
    public void findUserNotFoundNegativeTest() {
    	
        userResource = new UserResource();
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        
        userResource.addUser("fake user", "fake@user.com", "admin,master");
        Response response = userResource.findUser("dummy@user.com");
        Assert.assertEquals(400, response.getStatus());
        
        userResource=null;
        userDao.users = null;
        userDao = null;
    }
}
