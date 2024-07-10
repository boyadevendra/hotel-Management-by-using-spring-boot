package com.hotel.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto 
{
	private String firstname;
	private String lastname;
	private String emailid;
	private String password;
	private long mobilenumber;
}
