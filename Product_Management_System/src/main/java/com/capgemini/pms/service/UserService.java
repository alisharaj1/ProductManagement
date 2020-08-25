package com.capgemini.pms.service;



import org.springframework.data.jpa.repository.Query;


import com.capgemini.pms.entity.User;

public interface UserService {
	
	@Query("select u from User u where u.userId=?1")
	boolean addUser(User user);
	java.util.Optional<User> findUserById(Long userId);


}
