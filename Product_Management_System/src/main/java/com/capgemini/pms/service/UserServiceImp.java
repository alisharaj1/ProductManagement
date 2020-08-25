package com.capgemini.pms.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pms.dao.UserDao;
import com.capgemini.pms.entity.Product;
import com.capgemini.pms.entity.User;


/***************************************************************************************************************************
 *  @author          ALISHA RAJ
 *  Description      It is a Service class that provides the services for adding a new user,showing all the users,
                     finding user by userID and deleting user by userId. 
 *  Version          1.0
 *  Created Date     
 **************************************************************************************************************************/


@Service
@Transactional
public class UserServiceImp implements UserService {

	
	
	@Autowired
	private UserDao userDao;
@Override
	public boolean addUser(User user) {
	Optional<User>findById=findUserById(user.getUserId());
	if(findById.isPresent())
	{
		return false;
	}
	
		else {
		return userDao.saveAndFlush(user)!=null;
		}
	}
	

   
	


	@Override
	public Optional<User> findUserById(Long userId) {

		return userDao.findById(userId);
		
	}
	

	


	
}
