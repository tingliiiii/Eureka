package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BookDao;

@Service
public class BuyOneServiceImpl implements BuyOneService {
	
	@Autowired
	private BookDao bookDao;

	@Transactional(
			propagation = Propagation.REQUIRED, 
			isolation = Isolation.REPEATABLE_READ)
	@Override
	public void buyOne(String username, Integer bookId) {
		// 查詢價格
		Integer bookPrice = bookDao.getBookPrice(bookId);
		// 減去庫存
		bookDao.reduceBookStock(bookId, 1);
		// 修改餘額
		bookDao.reduceWalletBalance(username, bookPrice);
	}

}
