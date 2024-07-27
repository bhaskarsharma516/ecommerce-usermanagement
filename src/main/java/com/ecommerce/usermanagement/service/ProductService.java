package com.ecommerce.usermanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.usermanagement.model.Product;


@Service
public interface ProductService {

	public Product saveProduct(Product product);

	public List<Product> getAllProducts();

	public Boolean deleteProduct(Integer id);

	public Product getProductById(Integer id);

	public Product updateProduct(Product product, MultipartFile file);

	public List<Product> getAllActiveProducts(String category);
}
