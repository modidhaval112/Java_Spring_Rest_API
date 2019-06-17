package test.com.h2rd.refactoring.integration;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Test;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.web.UserResource;

public class UserIntegrationTest {
	
    UserDao userDao;
	
	@Test
	public void createUserPositiveTest() {
		UserResource userResource = new UserResource();
		
		if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
		userDao.users = null;
		
		User integration = new User();
        integration.setName("integration");
        integration.setEmail("initial@integration.com");
        integration.setRoles(Arrays.asList("admin", "master"));
        
        String roles = String.join(",", integration.getRoles());
        
        Response response = userResource.addUser(integration.getName(), integration.getEmail(), roles);
        Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void updateUserPositiveTest() {
		
		UserResource userResource = new UserResource();
		createUserPositiveTest();
		       
        User updated = new User();
        updated.setName("integration");
        updated.setEmail("initial@integration.com");
        updated.setRoles(Arrays.asList("admin", "master"));
        
        String roles = String.join(",", updated.getRoles());
        
        Response response = userResource.updateUser(updated.getName(), updated.getEmail(), roles);
        Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void findUserPositiveTest() {
		
		UserResource userResource = new UserResource();
		createUserPositiveTest();
                
        Response response = userResource.findUser("initial@integration.com");
        Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void getUsersPositiveTest() {
		
		UserResource userResource = new UserResource();
		createUserPositiveTest();
		
        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void deleteUserPositiveTest() {
		
		UserResource userResource = new UserResource();
		createUserPositiveTest();
        
        Response response = userResource.deleteUser("initial@integration.com");
        Assert.assertEquals(200, response.getStatus());
	}
	
	
	/*
	 * Negative integration test cases
	 */
	
	@Test
	public void createUserNegativeTest() {
		UserResource userResource = new UserResource();
		createUserPositiveTest();
		
		if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
		
		User integration = new User();
        integration.setName("integration");
        integration.setEmail("initial@integration.com");
        integration.setRoles(Arrays.asList("admin", "master"));
        
        String roles = String.join(",", integration.getRoles());
        
        Response response = userResource.addUser(integration.getName(), integration.getEmail(), roles);
        Assert.assertEquals(400, response.getStatus());
	}
	
	@Test
	public void updateUserNegativeTest() {
		
		UserResource userResource = new UserResource();
		createUserPositiveTest();
		deleteUserPositiveTest();
		       
        User updated = new User();
        updated.setName("integration");
        updated.setEmail("initial@integration.com");
        updated.setRoles(Arrays.asList("admin", "master"));
        
        String roles = String.join(",", updated.getRoles());
        
        Response response = userResource.updateUser(updated.getName(), updated.getEmail(), roles);
        Assert.assertEquals(400, response.getStatus());
	}
	
	@Test
	public void findUserNegativeTest() {
		
		UserResource userResource = new UserResource();
		createUserPositiveTest();
		deleteUserPositiveTest();
                
        Response response = userResource.findUser("initial@integration.com");
        Assert.assertEquals(400, response.getStatus());
	}
	
	@Test
	public void getUsersNegativeTest() {
		
		UserResource userResource = new UserResource();
		createUserPositiveTest();
		deleteUserPositiveTest();
		
        Response response = userResource.getUsers();
        Assert.assertEquals(400, response.getStatus());
	}
	
	@Test
	public void deleteUserNegativeTest() {
		
		UserResource userResource = new UserResource();
		createUserPositiveTest();
		deleteUserPositiveTest();
        
        Response response = userResource.deleteUser("initial@integration.com");
        Assert.assertEquals(400, response.getStatus());
	}
}
