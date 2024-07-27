package com.ecommerce.usermanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.usermanagement.model.OrderRequest;
import com.ecommerce.usermanagement.model.ProductOrder;

@Service
public interface OrderService {

	public void saveOrder(Integer userid, OrderRequest orderRequest) throws Exception;

	public List<ProductOrder> getOrdersByUser(Integer userId);

	public ProductOrder updateOrderStatus(Integer id, String status);

	public List<ProductOrder> getAllOrders();
	}
