package Cloud_vendor.service;

import java.util.List;


import javax.sound.midi.SysexMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Cloud_vendor.entity.cloud_vendor;
import Cloud_vendor.repository.Cloud_vendorRepo;

@Service
public class Cloud_vendorService {
	@Autowired
	private Cloud_vendorRepo repo;

	public String saveUser(cloud_vendor user) {
		try {
		 repo.findByEmail(user.getEmail());
		}
		catch(Exception e){
			 BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
	           String password = encode.encode(user.getPassword());
	           user.setPassword(password);
			
				repo.save(user); 
			return "cloud vendor registered successfully";
			
		}
		return "cloud vendor is  already exists"; 
	}

	public List<cloud_vendor> getUsers() {
		
			return repo.findAll();
		
	}

	

	public String deleteUser(String email) {
		try {
		 repo.findByEmail(email);
		}
		catch(Exception e) {
			return " cloud vendor not found to delete with the email " + email;
		}
		
		cloud_vendor user = repo.findByEmail(email);
		repo.delete(user);
		return "cloud vendor deleted successfully with email " + user.getEmail();
	}
	
	public String updateUser(cloud_vendor user) {
		cloud_vendor u = repo.findByEmail(user.getEmail());
		try {
			if (u == null) {
				throw new Exception();
			}
		}
		catch(Exception e) {
			return "cloud vendor not found to update with the email " + user.getEmail();}
		u.setName(user.getName());
		u.setAddress(user.getAddress());
		u.setPhoneNumber(user.getPhoneNumber());
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String password = encode.encode(user.getPassword());
		u.setPassword(password);
		repo.save(u);
		return "cloud vendor details updated successfully";
	}

	public String getUser(String email) {
		try {
			cloud_vendor user = repo.findByEmail(email); 
			if (user == null) {
				throw new NullPointerException("cloud vendor gmail does't exist try with other gmail");
			}
			return (user.toString());
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
