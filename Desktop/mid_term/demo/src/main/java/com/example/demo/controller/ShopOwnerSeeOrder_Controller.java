package com.example.demo.controller;
import java.io.IOException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.OwnerConfirm_orderRepository;
import com.example.demo.dao.ShopRegisterRepository;
import com.example.demo.enittiy.CustomUserDetail;
import com.example.demo.enittiy.OwnerConfirmOrder;
import com.example.demo.enittiy.ShopRegister;
import com.example.demo.globalAccess.GlobalData;
import com.example.demo.services.ShopOwner_confirmOrder;



@Controller
public class ShopOwnerSeeOrder_Controller {

	@Autowired
	ShopOwner_confirmOrder shopowner_confirmorder;
	
	@Autowired
	private OwnerConfirm_orderRepository orderrepo;
	
	@Autowired
	private OwnerConfirm_orderRepository orderseerepository;
	
	@Autowired 
	ShopRegisterRepository shopregisterrepository;
	
	Authentication authentication;
	CustomUserDetail userDetails;
	
	
	

	@GetMapping("/shopowner_home_return")
	public String home(Model model) {
		return "shopowner_home";
	}
	
	
	
	
	
	@RequestMapping("/shopwoner_home")
	public String shopwoner(HttpServletRequest request) {
		
		
		 String email=(String) request.getSession().getAttribute("email");
			//System.out.println("session works :"+email);
			
			Optional<ShopRegister> shopregister=shopregisterrepository.findByEmail(email);
			long shopidd=shopregister.get().getId();
			
			String shopid_string= String.valueOf(shopidd);
			//System.out.println("shopid: "+shopidd);
			request.getSession().setAttribute("shopid", shopid_string);
			
			//System.out.println(" session set in onwer home for shopowner_seeorder ");
		
		
		return "shopowner_home";
	}
	
	@GetMapping("/shopwoner_seeorder")
	public String show(Model map ,HttpServletRequest request)//for session to work use in function parameter of mapping//
	{
		
		 
	String shopid_string= (String) request.getSession().getAttribute("shopid");
	int shopid=Integer. parseInt(shopid_string);
	System.out.println("shopid in shop seeorder"+shopid);
		
	List<OwnerConfirmOrder> orders = shopowner_confirmorder.getAllOrder(shopid);
		map.addAttribute("orders", orders  );
		map.addAttribute("shopid",shopid);
	
	
		
		
		
	
		
		return "ownerconfirm_order";
		
	}
	
	
	
	@GetMapping("/display/{id}/{shopid}")
	@ResponseBody
	void showImage(@PathVariable("shopid") int shopid,@PathVariable("id") int id, HttpServletResponse response, Optional<OwnerConfirmOrder> order)
			throws ServletException, IOException {
	
		 
		//System.out.println("shopidchaga:"+shopid);
		//System.out.println("in image display id:"+id);
		order = orderseerepository.getProductimageByIdAndShopid(id,shopid);
		
					response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
						response.getOutputStream().write(order.get().getProductImage());
		
		response.getOutputStream().close();
	}
	
	
	@GetMapping("/vieworderdetails/{id}/{shopid}")
	String showProductDetails(@PathVariable("shopid") int shopid,@PathVariable("id") int id, Optional<OwnerConfirmOrder> order, Model model,HttpServletRequest request) {
	
	//	System.out.println("shopid:"+shopid);
		try {
			
			if (id != 0) {
				order = orderseerepository.findById(id);
			String confirm;
			
			
				if (order.isPresent()) {
					model.addAttribute("id", order.get().getId());
					model.addAttribute("productname", order.get().getProductname());
					model.addAttribute("shipping_adress", order.get().getShippingadress());
					model.addAttribute("order_adress", order.get().getOrderadress());
					model.addAttribute("price", order.get().getPrice());
					model.addAttribute("customer_phone", order.get().getCustomerphonenumbr());
				
					model.addAttribute("shopid",shopid);
					 String email=(String) request.getSession().getAttribute("email");
						
						
						//System.out.println("session in view details :"+email);
					
				}
	
			}
			}catch(Exception e) {
				System.out.print("exception occured");
			}
		return "show_order_details";
	
	}
}

