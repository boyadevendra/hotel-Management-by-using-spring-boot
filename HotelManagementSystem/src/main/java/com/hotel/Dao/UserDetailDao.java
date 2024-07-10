package com.hotel.Dao;

import java.util.List;

import com.hotel.Entity.UserDetails;

public interface UserDetailDao 
{
	UserDetails insertUserRegistration(UserDetails details);
	
	UserDetails userlogin(String emailid,String password);
	
	List<UserDetails> getalluserdetails();
	
	UserDetails updatealluserdetailsbyusinguserid(int userid);
	
    boolean  deletebyuserid(int userid);
    
    
    UserDetails updateValuesinDataBase(UserDetails userdetails);
}
