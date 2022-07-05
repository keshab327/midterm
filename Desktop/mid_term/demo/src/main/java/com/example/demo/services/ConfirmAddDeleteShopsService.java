package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.ShopRegisterRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.enittiy.Role;
import com.example.demo.enittiy.ShopRegister;
import com.example.demo.enittiy.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class ConfirmAddDeleteShopsService {

	@Autowired
	ShopRegisterRepository shopregisterrepo;
	
	@Autowired
	UserRepository userrepo;
	
@Autowired
	RoleRepository rolerepository;

@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
String account_Id="AC240fbe5c5603c9ad49a7360082adbc21";
String auth_token="6d3d8a73abc98dd2305e97773c0898d6";
String from_twilo="+18145408277";
String to_number="+9779868679009";

	
	public String confirmandadd(Long id) {
		
		Optional<ShopRegister> shopregister;
		
		shopregister=shopregisterrepo.findById(id);
		System.out.println(id);
		System.out.println("in confirm service");
		
		
		String name=shopregister.get().getOwner_name();
		System.out.println(name);
		String email=shopregister.get().getEmail();
		String role="ROLE_OWNER";
		int min=10000000;
		int max=99999900;
		int number =(int)(Math.random()*(max-min+1)+min);
		String password=Integer.toString(number);
		
		Long phone_number=shopregister.get().getPhone_number();
		String phn_numbr=String.valueOf(phone_number);
		
	shopregister.get().setVerification("CONFIRMED");
		
		
		//Adding user
		
		User user=new User();
		user.setFirstName(name);
		List<Role> roles=new ArrayList<>();
		roles.add(rolerepository.findById(0).get());
		user.setRoles(roles);
		user.setEmail(email);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		userrepo.save(user);
		System.out.println("passwordddddddddddddd:"+password);
		
		//user  added
		
		
		//msg to owner phone with password and user name i.e email
		String msg="\nFROM e-fancy\n\n"+name+"\nThank for registration\n your user name is:"+email+"\n password is:"+password;
		
		
		
		//twilio code
	
		Twilio.init(account_Id, auth_token);
		
		
		PhoneNumber to=new PhoneNumber(to_number);
		PhoneNumber from=new PhoneNumber(from_twilo);
		
		
		//send otp to mobile number
		Message message=Message.creator(to,from,msg).create();
		
		
		
		return "success";
		
		
	}
	
	
	public String rejectShop(Long id) {
		
	Optional<ShopRegister> shopregister;
		
		shopregister=shopregisterrepo.findById(id);
		
		
		
		String name=shopregister.get().getOwner_name();
		
		
		
		shopregisterrepo.deleteById(id);
		
		
		
		
		
		//msg to owner phone notifiyinng their request has been declined
				String msg="\nFROM e-fancy\n\n"+name+"\nyour request has been declined due to improper information provided\n\n  please try again";
				
				
				
				//twilio code
			
				Twilio.init(account_Id, auth_token);
				
				
				PhoneNumber to=new PhoneNumber(to_number);
				PhoneNumber from=new PhoneNumber(from_twilo);
				
				
				//send otp to mobile number
				Message message=Message.creator(to,from,msg).create();
		

		return "aaa";
		
		
		
	}
	
	public String deleteShop(Long id) {
		
		
		Optional<ShopRegister> shopregister;
		shopregister=shopregisterrepo.findById(id);
		System.out.println(id);
		System.out.println("in delete from both table shop");
		
		
		String name=shopregister.get().getOwner_name();
		System.out.println("name"+name);
		String email=shopregister.get().getEmail();
		
	
		//delete from user table so shop couldn't login

		userrepo.deleteByEmail(email);
		
		//delete form shop table
		shopregisterrepo.deleteById(id);
		
		System.out.println("succesfully removed from system");
		
		return "aaa";
		
		
		
	}
	
	
	
	
	
	
	
}
