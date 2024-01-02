package com.example.demo.service;

import java.util.List;

import javax.sound.midi.SysexMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo repo;
	
	
	public String saveUser(User user) {
			User u = repo.findByEmail(user.getEmail());
			if (u!= null)
				return("User already exists");
			else
			repo.save(user);  // Saves user details in database
			return "User registered successfully";
	}
	
	public List<User> getUsers() {
		return repo.findAll();  // Returns all the users in database table as list
	}
	
	public String DeleteUser(String email ) {
		try {
		User user = repo.findByEmail(email);		// finds the users in database table with this email
		if (user == null ) {
			throw new NullPointerException("User does'nt exist");
		}
		repo.delete(user);		// deletes the user from database table
		return "User deleted successfully";}
		catch(Exception e) {
			return e.getMessage();
		}
	}

	public String deleteUser(String email) {
		User user = repo.findByEmail(email);
		if (user == null)
			return "no user found to delete with the email " + email;
		repo.delete(user);
		return "User deleted successfully with email " + user.getEmail();
	}

	public String updateUser(User user) {
		User u = repo.findByEmail(user.getEmail());
		if (u == null)
			return "User not found to update with the email " + user.getEmail() ;
		u.setName(user.getName());
		u.setBloodGroup(user.getBloodGroup());
		u.setPhoneNumber(user.getPhoneNumber());
		u.setPassword(user.getPassword());
		repo.save(u);
		return "User details updated successfully";
	}

	public String getUser(String email) {
		// TODO Auto-generated method stub
		try {
			User user = repo.findByEmail(email);		// finds the users in database table with this email
			if (user == null ) {
				throw new NullPointerException("mail does'nt exist");
			}
			return(user.toString());	
		}
			catch(Exception e) {
				return e.getMessage();
			}
	}
}
