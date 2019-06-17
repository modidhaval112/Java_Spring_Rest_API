package com.h2rd.refactoring.usermanagement;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class User {

    private String name;
    private String email;
    private List<String> roles;
    private String status;
    private String message;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}    
}
