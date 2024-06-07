package com.example.demo.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {

	private Integer id;
	private Integer orderId;
	private Integer productId;
	private Integer quantity;
	private Integer subtotal;
}
