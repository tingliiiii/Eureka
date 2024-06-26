package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BuyOneService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BuyOneService buyOneService;
	
	@GetMapping("/one")
	public String buyOneBook(@RequestParam("username") String username, @RequestParam("bookId") Integer bookId) {
		buyOneService.buyOne(username, bookId);
		return String.format("Buy One Book Success, username: %s bookId: %d", username, bookId);
	}
}
