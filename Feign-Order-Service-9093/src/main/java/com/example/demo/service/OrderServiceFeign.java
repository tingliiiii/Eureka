package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.CustomerClient;
import com.example.demo.client.ProductClient;
import com.example.demo.dao.OrderDao;
import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.po.Customer;
import com.example.demo.model.po.Item;
import com.example.demo.model.po.Order;
import com.example.demo.model.po.Product;

@Service
public class OrderServiceFeign {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CustomerClient customerClient;

	@Autowired
	private ProductClient productClient;

	// 將 order 從 PO 轉 DTO
	private OrderDto convertToDto(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setOrderDate(order.getOrderDate());

		// 到 http://172.20.10.5:9092/customers/{id} 找資料
		// 到 FEIGN-CUSTOMER-SERVICE-9092 下的 /customers/{id} 找資料
		Customer customer = customerClient.getCustomerById(order.getCustomerId()).getData();
		System.out.println(customer);
		orderDto.setCustomer(customer);
		// 尋訪每一項 item, 並透過 Feign 取得實體資料
		for (Item item : order.getItems()) {
			ItemDto itemDto = convertToDto(item);
			orderDto.getItemDtos().add(itemDto); // 加入到 itemDtos
		}
		return orderDto;
	}

	// 將 item 從 PO 轉 DTO
	private ItemDto convertToDto(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setId(item.getId());
		itemDto.setQuantity(item.getQuantity());
		itemDto.setSubtotal(item.getSubtotal());
		// 透過 Feign 到 FEIGN-PRODUCT-SERVICE-9091 下的 /products/1 取得商品資料
		Product product = productClient.getProductById(item.getProductId()).getData();
		itemDto.setProduct(product);
		return itemDto;
	}

	public OrderDto getOrderById(Integer orderId) {
		Order order = orderDao.getOrderById(orderId);
		// PO 轉 DTO
		OrderDto orderDto = convertToDto(order);
		return orderDto;
	}

}
