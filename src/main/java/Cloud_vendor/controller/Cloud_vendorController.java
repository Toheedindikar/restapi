package Cloud_vendor.controller;
 
import java.util.List;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import Cloud_vendor.entity.cloud_vendor;
import Cloud_vendor.service.Cloud_vendorService;
 
@RestController
@RequestMapping("/cloudvendor")
public class Cloud_vendorController {
	 private static final Logger logger = LoggerFactory.getLogger(cloud_vendor.class);
 
	
	@Autowired
	private Cloud_vendorService service;
	@PostMapping("/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody cloud_vendor user) {
	    logger.info("creating the registered user details");
	    String result = service.saveUser(user);
	    return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	
	@GetMapping("/getUser/{email}")
	public ResponseEntity<String> getUser(@PathVariable("email") String email) {
	    logger.info("Retrieving registered user details");
	    String result = service.getUser(email);
	    return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	
	@GetMapping("/getallUsers")
	public ResponseEntity<List<cloud_vendor>> getUsers() {
	    logger.info("Retrieving all registered user details");
	    List<cloud_vendor> users = service.getUsers();
	    return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	

	
	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody cloud_vendor user) {
	    logger.info("Updating registered user details");
	    String result = service.updateUser(user);
	    return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	

	
	@DeleteMapping("/deleteByEmail/{email}")
	public ResponseEntity<String> deleteByEmail(@PathVariable("email") String email) {
	    logger.info("Deleting registered user details");
	    String result = service.deleteUser(email);
	    return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	

}