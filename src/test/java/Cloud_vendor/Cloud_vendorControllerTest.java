package Cloud_vendor;
 
import Cloud_vendor.entity.cloud_vendor;
import Cloud_vendor.service.Cloud_vendorService;
import jakarta.xml.ws.Response;
import Cloud_vendor.controller.Cloud_vendorController;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springdoc.webmvc.core.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
 
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(Cloud_vendorController.class)
@ContextConfiguration(classes = {Cloud_vendorController.class})
public class Cloud_vendorControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
    
    ObjectMapper om = new ObjectMapper();

    @MockBean
    private Cloud_vendorService userService;
 
    private cloud_vendor user;
 
    @BeforeEach
    public void setup() {
    	
        user = new cloud_vendor();
        user.setId(1);
        user.setName("Toheed Indikar");
        user.setEmail("toheed@gmail.com");
        user.setPassword("password");
        user.setAddress("mg road 5th block");
        user.setPhoneNumber("9591913575");
    }
    
    @Test
    public void addEmployeeTest() throws Exception {
    	when(userService.saveUser(any(cloud_vendor.class))).thenReturn("cloud vendor registered successfully");
		
		String jsonRequest = om.writeValueAsString(user);
		 mockMvc.perform(post("/cloudvendor/registerUser")
		            .content(jsonRequest)
		            .contentType(MediaType.APPLICATION_JSON_VALUE))
		            .andExpect(MockMvcResultMatchers.status().isCreated())
		            .andExpect(content().string("cloud vendor registered successfully"));
                   }
 
    @Test
    public void getUserTest() throws Exception {
        when(userService.getUser(anyString())).thenReturn(user.toString());
 
        mockMvc.perform(get("/cloudvendor/getUser/{email}", user.getEmail()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(user.toString()));
    }
 
    @Test
    public void getUsersTest() throws Exception {
        List<cloud_vendor> users = Arrays.asList(user);
        when(userService.getUsers()).thenReturn(users);
 
        mockMvc.perform(get("/cloudvendor/getallUsers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].name").value(user.getName()))
                .andExpect(jsonPath("$[0].email").value(user.getEmail()));
    }
 
    @Test
    public void updateUserTest() throws Exception {
    	String jsonRequest = om.writeValueAsString(user);
        when(userService.updateUser(any(cloud_vendor.class))).thenReturn("cloud vendor details updated successfully");
 
        mockMvc.perform(put("/cloudvendor/updateUser")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("cloud vendor details updated successfully"));
    }
 
    @Test
    public void deleteByEmailTest() throws Exception {
    	
        when(userService.deleteUser(anyString())).thenReturn("cloud vendor deleted successfully with email " + user.getEmail());
 
        mockMvc.perform(delete("/cloudvendor/deleteByEmail/{email}", user.getEmail()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("cloud vendor deleted successfully with email " + user.getEmail()));
    }
}