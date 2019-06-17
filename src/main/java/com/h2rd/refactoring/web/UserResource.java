package com.h2rd.refactoring.web;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Repository;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;

@Path("/users")
@Repository
public class UserResource{

    public UserDao userDao;
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@QueryParam("name") String name,
                            @QueryParam("email") String email,
                            @QueryParam("roles") String roles) {
    	
        User user = new User();
        
        if(roles == null || roles.isEmpty()){
        	user.setStatus("error");
        	user.setMessage("user should have atleast one role");
        	return Response.status(400).entity(user).build();
        }
        
        user.setName(name);
        user.setEmail(email);

        String[] rolesArray = roles.split(",");
        if(rolesArray.length == 0){
        	user = new User();
        	user.setStatus("error");
        	user.setMessage("user should have atleast one role");
        	return Response.status(400).entity(user).build();
        }
        
        user.setRoles(Arrays.asList(rolesArray));

        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }

        boolean flag = userDao.saveUser(user);
               
        if(flag){
        	user.setStatus("success");
        	user.setMessage("user added successfully");
        	return Response.ok().entity(user).build();
        }
        else{
        	user = new User();
        	user.setStatus("error");
        	user.setMessage("user can not be added as email id already exists");
        	return Response.status(400).entity(user).build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("roles") String roles) {
        User user = new User();

        if(roles == null  || roles.isEmpty()){
        	user.setStatus("error");
        	user.setMessage("user should have atleast one role");
        	return Response.status(400).entity(user).build();
        }
        user.setName(name);
        user.setEmail(email);
        
        String[] rolesArray = roles.split(",");
        if(rolesArray.length == 0){
        	user = new User();
        	user.setStatus("error");
        	user.setMessage("user should have atleast one role");
        	return Response.status(400).entity(user).build();
        }
        user.setRoles(Arrays.asList(rolesArray));

        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }

        if(userDao.updateUser(user)){
        	user.setStatus("success");
        	user.setMessage("user updates successfully");
        	return Response.ok().entity(user).build();
        }
        else
        	user = new User();
    		user.setStatus("error");
    		user.setMessage("user can not be updated as email id does not exists");
        	return Response.status(400).entity(user).build();
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@QueryParam("email") String email) {
        User user = new User();
        user.setEmail(email);

        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }

        if(userDao.deleteUser(user)){
        	user.setStatus("success");
        	user.setMessage("user deleted successfully");
        	return Response.ok().entity(user).build();
        }
        else
        	user = new User();
			user.setStatus("error");
			user.setMessage("user can not be deleted as email id does not exists");
        	return Response.status(400).entity(user).build();
    }

    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
    	   	
    	if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
    	
    	List<User> users = userDao.getUsers();
    	if (users == null || users.isEmpty()) {
    		User user = new User();
			user.setStatus("error");
			user.setMessage("there are no users in the database");
        	return Response.status(400).entity(user).build();
    	}

        GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {};
        return Response.status(200).entity(usersEntity).build();
    }
    
    @POST
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUser(@QueryParam("email") String email) {

        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        
        User user = userDao.searchUser(email);
        if(user != null){
        	user.setStatus("success");
        	user.setMessage("user found successfully");
        	return Response.ok().entity(user).build();
        }
        else
        	user = new User();
			user.setStatus("error");
			user.setMessage("Can not find user with email id : " + email );
        	return Response.status(400).entity(user).build();
    }
}
