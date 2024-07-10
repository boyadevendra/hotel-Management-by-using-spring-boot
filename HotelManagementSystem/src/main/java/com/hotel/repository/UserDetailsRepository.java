package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.Entity.UserDetails;
import java.util.List;


public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>
{
	UserDetails findByEmailidAndPassword(String emailid, String password);
	
	UserDetails findByUserid(int userid);

}
