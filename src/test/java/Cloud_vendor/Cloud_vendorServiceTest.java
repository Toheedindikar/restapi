package Cloud_vendor;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
 
import Cloud_vendor.entity.cloud_vendor;
import Cloud_vendor.repository.Cloud_vendorRepo;
import Cloud_vendor.service.Cloud_vendorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
//import java.util.Optional;
@ActiveProfiles("test")
public class Cloud_vendorServiceTest {
 
    @Mock
    private Cloud_vendorRepo userRepo;
 
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
 
    @InjectMocks
    private Cloud_vendorService userService;
 
    private cloud_vendor user;
 
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        user = new cloud_vendor();
        user.setName("Toheed Indikar");
        user.setEmail("toheed@gmail.com");
        user.setPassword("password");
        user.setAddress("mg road 5th block");
        user.setPhoneNumber("9591913575");
    }

    @Test
    public void testSaveUserSuccess() {
        when(userRepo.findByEmail(user.getEmail())).thenReturn(null);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepo.save(any(cloud_vendor.class))).thenReturn(user);
 
        String response = userService.saveUser(user);
        assertEquals("cloud vendor registered successfully", response);
    }
 
    @Test
    public void testSaveUserFailure() {
        when(userRepo.findByEmail(user.getEmail())).thenReturn(user);
 
        String response = userService.saveUser(user);
        assertEquals("cloud vendor is  already exists", response);
    }
 
    @Test
    public void testGetUsers() {
        when(userRepo.findAll()).thenReturn(Arrays.asList(user));
 
        List<cloud_vendor> users = userService.getUsers();
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
    }
 
    @Test
    public void testDeleteUserSuccess() {
        when(userRepo.findByEmail(user.getEmail())).thenReturn(user);
 
        String response = userService.deleteUser(user.getEmail());
        assertEquals("cloud vendor deleted successfully with email " + user.getEmail(), response);
    }
 
    @Test
    public void testDeleteUserFailure() {
        when(userRepo.findByEmail(user.getEmail())).thenReturn(null);
 
        String response = userService.deleteUser(user.getEmail());
        assertEquals(" cloud vendor not found to delete with the email " + user.getEmail(), response);
    }
 
    @Test
    public void testUpdateUserSuccess() {
        when(userRepo.findByEmail(user.getEmail())).thenReturn(user);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepo.save(any(cloud_vendor.class))).thenReturn(user);
 
        String response = userService.updateUser(user);
        assertEquals("cloud vendor details updated successfully", response);
    }
 
    @Test
    public void testUpdateUserFailure() {
        when(userRepo.findByEmail(user.getEmail())).thenReturn(null);
 
        String response = userService.updateUser(user);
        assertEquals("cloud vendor not found to update with the email " + user.getEmail(), response);
    }
 
    @Test
    public void testGetUserSuccess() {
        when(userRepo.findByEmail(user.getEmail())).thenReturn(user);
 
        String response = userService.getUser(user.getEmail());
        assertEquals(user.toString(), response);
    }
 
    @Test
    public void testGetUserFailure() {
        when(userRepo.findByEmail(user.getEmail())).thenReturn(null);
 
        String response = userService.getUser(user.getEmail());
        assertEquals("cloud vendor gmail does't exist try with other gmail", response);
    }
}