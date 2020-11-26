package com.example.demo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.entities.Category;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderItem;
import com.example.demo.entities.Payment;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.entities.enums.OrderStatus;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.OrderItemRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository OIRepository;

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Beltrano", "Bletrano@fulano.com", "88888888", "123456");
		User u2 = new User(null, "Fulano", "Fulanagem@fulano.com", "99999999", "123456");
		
		Order o1 = new Order(null, Instant.parse("2020-11-15T17:15:05Z"), u1, OrderStatus.PAID);
		Order o2 = new Order(null, Instant.parse("2020-11-16T14:23:02Z"), u2, OrderStatus.WAITING_PAYMENT);
		Order o3 = new Order(null, Instant.parse("2020-11-17T15:32:08Z"), u2, OrderStatus.WAITING_PAYMENT);
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "Produto 1", "Uma descricao", 90.5, "");
		Product p2 = new Product(null, "Produto 2", "Uma descricao", 105.3, "");
		Product p3 = new Product(null, "Produto 3", "Uma descricao", 110.0, "");
		Product p4 = new Product(null, "Produto 4", "Uma descricao", 150.0, "");
		Product p5 = new Product(null, "Produto 5", "Uma descricao", 1200.0, "");
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat1);
		p5.getCategories().add(cat2);
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p5, 3, p5.getPrice());
		OrderItem oi4 = new OrderItem(o3, p3, 4, p3.getPrice());
		
		OIRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2020-11-16T15:15:05Z"), o2);
		Payment pay2 = new Payment(null, Instant.parse("2020-11-18T15:15:05Z"), o3);
		
		o2.setPayment(pay1);
		o3.setPayment(pay2);
		
		orderRepository.save(o2);
		orderRepository.save(o3);
	}
}
