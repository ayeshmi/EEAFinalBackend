package com.example.demo.controller.webController;
import org.springframework.stereotype.Component;



import com.example.demo.security.JwtResponse;


import java.util.List;

//implementation of facade design pattern and home page controller
@Component
public class HomePageControllerFacade {

	
	public String directUserToHomePage(JwtResponse  authentication)  {

	        List<String> role =authentication.getRoles();

         
            String page=null;
    
            //get switch case to find home page for particular user
	        switch (role.get(0).toString()) {

	            case "ROLE_USER":
	                page="UserHomepage";
	                break;

	            case "ROLE_ADMIN":
	            	page="AdminHomePage";
	                break;

	            case "ROLE_PHARMACIST":
	            	page="PharmacientHomePage";
	                break;
	        }

	        return page;
	    }
	
}
