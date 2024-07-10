package com.hotel.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotel.Entity.UserDetails;
import com.hotel.repository.UserDetailsRepository;

@Repository
public class UserDetailsDaoImp implements UserDetailDao 
{
	@Autowired
	UserDetailsRepository detailsRepository;

	@Override
	public UserDetails insertUserRegistration(UserDetails details) 
	{
		return  detailsRepository.save(details);
	}
	
	@Override
	public UserDetails userlogin(String emailid, String password) 
	{
		return detailsRepository.findByEmailidAndPassword(emailid, password);
	}

	@Override
	public List<UserDetails> getalluserdetails() 
	{
		return detailsRepository.findAll();
	}

	@Override
	public boolean deletebyuserid(int userid) {
		UserDetails userDetails = detailsRepository.findById(userid).orElse(new UserDetails());
		if(userDetails.getUserid()!=0)
		{
			detailsRepository.delete(userDetails );
			return true;
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public UserDetails updatealluserdetailsbyusinguserid(int userid)
	{
		return detailsRepository.findById(userid).orElse(new UserDetails());
	}

	@Override
	public UserDetails updateValuesinDataBase(UserDetails userdetails) {
		UserDetails byUserid = detailsRepository.findByUserid(userdetails.getUserid());
		byUserid.setFirstname(userdetails.getFirstname());
		byUserid.setLastname(userdetails.getLastname());
		byUserid.setEmailid(userdetails.getEmailid());
		byUserid.setPassword(userdetails.getPassword());
		byUserid.setMobilenumber(userdetails.getMobilenumber());
		return detailsRepository.save(byUserid);
	}



	

}
