package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class BuyController {

@RequestMapping("/buy")
 public String buy() {
	
	return "buy";
}
	
}
