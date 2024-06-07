package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.model.po.Product;

@Repository
public class ProductDao {

	// In-Memory
	private static List<Product> products = new CopyOnWriteArrayList<>();

	static {
		// id, name, price, cost, quantity
		products.add(new Product(1, "龜記三韻紅萱", 40, 10, 300));
		products.add(new Product(2, "烏弄柳橙冬片仔", 60, 15, 200));
		products.add(new Product(3, "五十嵐四季春", 32, 8, 100));

	}

	public List<Product> findAll() {
		return products;
	}

	public Optional<Product> findById(Integer id) {
		return products.stream().filter(product -> product.getId().equals(id)).findFirst();
	}

	public Product save(Product product) {
		int maxId = products.stream().mapToInt(Product::getId).max().orElse(0);
		product.setId(maxId + 1);
		products.add(product);
		return product;
	}

	public Product update(Product product) {
		for (Product p : products) {
			if (p.getId().equals(product.getId())) {
				p.setName(product.getName());
				p.setPrice(product.getPrice());
				p.setCost(product.getCost());
				p.setQuantity(product.getQuantity());
				return p;
			}
		}
		return null;
	}

	// 直接調整庫存數量
	public Boolean replaceQuantity(Integer id, Integer quantity) {
		for (Product p : products) {
			if (p.getId().equals(id)) {
				p.setQuantity(quantity);
				return true;
			}
		}
		return false;
	}

	// 加減現有庫存數量
	public Boolean adjustQuantity(Integer id, int amount) {
		for (Product p : products) {
			if (p.getId().equals(id)) {
				int lastQuantiy = p.getQuantity()+amount;
				if(lastQuantiy<0) {
					return false;
				}
				p.setQuantity(lastQuantiy);
				return true;
			}
		}
		return false;
	}
	
	public Boolean delete(Integer id) {
		Boolean isDelete = false;
		Optional<Product> product = findById(id);
		if(product.isEmpty()) {
			return isDelete;
		}
		isDelete = products.remove(product.get());
		return isDelete;
	}

}
