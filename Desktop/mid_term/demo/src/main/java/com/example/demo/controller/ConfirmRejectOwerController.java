package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.ShopRegisterRepository;
import com.example.demo.enittiy.ShopRegister;
import com.example.demo.services.ConfirmAddDeleteShopsService;

@Controller
public class ConfirmRejectOwerController {

	@Autowired
	ConfirmAddDeleteShopsService confirmaddshop;
	
	@GetMapping("/confirm_add_user/{id}")
	String confirmShop(@PathVariable("id") Long id, Optional<ShopRegister> shopregister, Model model) {
		
		//System.out.println("in confirm controller");	
	String a=confirmaddshop.confirmandadd(id);
	
	// redirect to showing all requests
	 return "redirect:/image/show";  //shop register controller  last teera
		
		
	}
	
	
	
	@GetMapping("/reject_shop_request/{id}")
	String rejectShop(@PathVariable("id") Long id, Optional<ShopRegister> shopregister, Model model)
	{
		
	System.out.println("in reject controller");	
	   String aa= confirmaddshop.rejectShop(id);
	   return "redirect:/image/show";  //shop register controller last teera
}
	
	@GetMapping("/delete_shop_request/{id}")
	String deleteShop(@PathVariable("id") Long id, Optional<ShopRegister> shopregister, Model model)
	{
		
	System.out.println("in delete controller");	
	   String aa= confirmaddshop.deleteShop(id);
	   return "redirect:/image/show";  //shop register controller last teera
}	
	
	
	
	
	
	
	
	
	
	
	
	
}
