package com.example.demo.model.po;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	private Integer id;
	private Integer customerId;
	private String orderDate;
	private List<Item> items = new CopyOnWriteArrayList<>(); // 訂單細目（明細）
	private Integer total;
}
