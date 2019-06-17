package com.h2rd.refactoring.usermanagement;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public ArrayList<User> users;

    public static UserDao userDao;

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public boolean saveUser(User user) {
    	
    	boolean flag = true;
    	
        if (users == null) {
            users = new ArrayList<User>();
        }
        
        for (User tempUser : users) {
            if (tempUser.getEmail().equalsIgnoreCase(user.getEmail())) {
            	flag = false;
            	break;
            }
        }
        
        if(flag){
        	users.add(user);
        	return true;
        }
        return false;
    }

    public ArrayList<User> getUsers() {
        try {
            return users;
        } catch (Exception e) {
            System.out.println("error");
            return null;
        }
    }

    public boolean deleteUser(User userToDelete) {
    	boolean flag = false;
        try {
        	if(users == null || users.isEmpty()){
        		return flag;
        	}
        	List<User> tempList = new ArrayList<>(users);
            for (User user : users) {
                if (user.getEmail().equalsIgnoreCase(userToDelete.getEmail())) {
                	tempList.remove(user);
                	flag = true;
                }
            }
            users = new ArrayList<>(tempList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateUser(User userToUpdate) {
        try {
        	if(users == null || users.isEmpty()){
        		return false;
        	}
            for (User user : users) {
                if (user.getEmail().equalsIgnoreCase(userToUpdate.getEmail())) {
                    user.setName(userToUpdate.getName());
                    user.setRoles(userToUpdate.getRoles());
                    return true;
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User searchUser(String email) {
        try {
        	if(users == null || users.isEmpty()){
        		return null;
        	}
            for (User user : users) {
                if (email.equalsIgnoreCase(user.getEmail())) {
                    return user;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
