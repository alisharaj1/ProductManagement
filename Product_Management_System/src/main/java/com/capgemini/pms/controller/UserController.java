package com.capgemini.pms.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pms.dao.UserDao;
import com.capgemini.pms.entity.Product;
import com.capgemini.pms.entity.User;
import com.capgemini.pms.exception.ProductManagementSystemException;
import com.capgemini.pms.service.UserService;


/***************************************************************************************************************************
 *  @author         ALISHA RAJ
 *  Description      It is a Controller class that provides the Control for Registering a new user,viewing all the users,
                     login and deleting a user. 
 *  Version          1.0
 *  Created Date     
 **************************************************************************************************************************/


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class UserController {
	
	
	@Autowired
	private UserService  userService;
	private UserDao userDao;
	
	
	
/***************************************************************************************************************************
*  Method       :addUser
*  Description  :To Register a new user
*  @returns String:It will return Done if user has been registered
*  @throw UserException :It is raised due to invalid userId,invalid userName ,password pattern mismatched 
*  Created By   :@author ALISHA RAJ
*  Created Date :   
***************************************************************************************************************************/

	
	
	@PostMapping("/Register")
	public ResponseEntity<String> addUser(@RequestBody User user, BindingResult result) {
	String errorMessage = "";
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors)
				errorMessage += error.getDefaultMessage() + "<br/>";
			throw new ProductManagementSystemException(errorMessage);
		}
		try {
		
			boolean Result=userService.addUser(user);
			if(Result)
			{ return new ResponseEntity<String>("user registered successfully: " + user.getUserId() + " " + " is your user id", new HttpHeaders(),HttpStatus.OK);
			
			}
			else
			{
				return new ResponseEntity<String>("User Id already exists", new HttpHeaders(),HttpStatus.OK);
			}

		} catch (DataIntegrityViolationException ex) {
			throw new ProductManagementSystemException(ex.getMessage());
		}
	}
	

/***************************************************************************************************************************
*  Method       :authUser
*  Description  :To logging in the user
*  @PathVariable userId: It will take userId given by a user during login
*  @PathVariable password: It will take password given by a user during login
*  @returns String:It will return Admin if user is admin,return ProductMaster if user is productmaster,return Wrong Password if password 
  				   is not verified and return  Id doesn't Exist if userId is not found
*  Created By   :@author ALISHA RAJ
*  Created Date :   
***************************************************************************************************************************/
	
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value="/login/{userId}")
	public ResponseEntity<String> authUser(@PathVariable long userId , @RequestBody String password,BindingResult result) 
	{
		String errorMessage = "";
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors)
				errorMessage += error.getDefaultMessage() + "<br/>";
			throw new ProductManagementSystemException(errorMessage);
		}
		try {
		Optional<User>findById=userService.findUserById(userId);
		
		
		if(findById.isPresent()) {
			User user=findById.get();
			if(password.equals(user.getPassword())) {
				
				if(user.getIsAdmin())
					return new  ResponseEntity<String>("Admin", HttpStatus.OK);
				else
					return new  ResponseEntity<String>("Product Master", HttpStatus.OK);
			}
			else
				return new  ResponseEntity<String>("Wrong Password", HttpStatus.OK);
		}
		else
			throw new ProductManagementSystemException("Id doesn't Exist ");
		}
	catch(DataIntegrityViolationException ex)
	{
		throw new ProductManagementSystemException(ex.getMessage());
		
	}

	}}
	
