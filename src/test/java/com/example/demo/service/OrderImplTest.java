package com.example.demo.service;

import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;

@SpringBootTest
public class OrderImplTest {
	@Autowired
	private OrderService orderService;

	@MockBean
	private OrderRepository orderRepository;

	
	@Test
	@DisplayName("Test viewCartDetailsUser")
	void testViewCartDetailsUser() {
		// Setup our mock repository
				Order order1 = new Order();
				Order order2 = new Order();

				doReturn(Arrays.asList(order1, order2)).when(orderRepository).searchCartDetails(1l);

				// Execute the service call
				List<Order> widgets = orderService.viewCartDetailsUser(1l);

				// Assert the response
				Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	
	@Test
	@DisplayName("Test viewPendingOrders")
	void testViewPendingOrders() {
		// Setup our mock repository
				Order order1 = new Order();
				Order order2 = new Order();

				doReturn(Arrays.asList(order1, order2)).when(orderRepository).searchProcessOrderDetails(1l);

				// Execute the service call
				List<Order> widgets = orderService.viewPendingOrders(1l);
				// Assert the response
				Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}

	
	@Test
	@DisplayName("Test viewPendingOrders")
	void testViewCancelOrders() {
		// Setup our mock repository
				Order order1 = new Order();
				Order order2 = new Order();

				doReturn(Arrays.asList(order1, order2)).when(orderRepository).searchCancelOrderDetails(1l);

				// Execute the service call
				List<Order> widgets = orderService.viewCancelOrders(1l);
				// Assert the response
				Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	
	
	@Test
	@DisplayName("Test viewPendingOrders")
	void testViewComletedOrders() {
		// Setup our mock repository
				Order order1 = new Order();
				Order order2 = new Order();

				doReturn(Arrays.asList(order1, order2)).when(orderRepository).searchComletedOrderDetails(1l);

				// Execute the service call
				List<Order> widgets = orderService.viewComletedOrders(1l);
				// Assert the response
				Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	

	
	@Test
	@DisplayName("Test OrderConfirmation")
	void testOrderConfirmation() {
		// Setup our mock repository
				Order order1 = new Order();
				Order order2 = new Order();

				doReturn(Arrays.asList(order1, order2)).when(orderRepository).findByClientEmail("ayeshmi@gmail.com");

				// Execute the service call
				orderService.orderConfirmation("ayeshmi@gmail.com");
				// Assert the response
				//Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	
	
	@Test
	@DisplayName("Test deleteOrder")
	void testDeleteOrder() {
		Optional<Order> order = Optional.of(new Order());

		doReturn(order).when(orderRepository).findById(order.get().getId());

		MessageResponse msg = orderService.deleteItem(order.get().getId());

		Assertions.assertEquals(msg.getMessage(), "Item is successfully deleted from the cart.");
	}
	
	
	@Test
	@DisplayName("Test ViewOrderDetailsUser")
	void testViewOrderDetailsUser() {
		// Setup our mock repository
		Order order1 = new Order();
		Order order2 = new Order();

		doReturn(Arrays.asList(order1, order2)).when(orderRepository).search(1l);

		// Execute the service call
		List<Order> widgets = orderService.viewOrderDetailsUser(1l);
		// Assert the response
		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	
	
	@Test
	@DisplayName("Test ViewOrderDetailsUser")
	void testOrderCompleted() {
		Optional<Order> order = Optional.of(new Order());

		doReturn(order).when(orderRepository).findById(order.get().getId());
		MessageResponse message=orderService.orderCompleted(order.get().getId());
		Assertions.assertEquals(message.getMessage(),  "We are happy, Order is successfully completed.");
	}
	
	
	@Test
	@DisplayName("Test ViewOrderCancelation")
	void testOrderCancelation() {
		Optional<Order> order = Optional.of(new Order());

		doReturn(order).when(orderRepository).findById(order.get().getId());
		MessageResponse message=orderService.orderCancelation(order.get().getId(),order.get().getReason());
		Assertions.assertEquals(message.getMessage(),  "Order is canceled.");
	}
	
	
	
	@Test
	@DisplayName("Test GetAllCancelOrders")
	void testGetAllCancelOrders() {
		// Setup our mock repository
				Order order1 = new Order();
				Order order2 = new Order();

				doReturn(Arrays.asList(order1, order2)).when(orderRepository).getCancelOrders();

				// Execute the service call
				List<Order> widgets = orderService.getAllCancelOrders();
				// Assert the response
				Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	
	
	@Test
	@DisplayName("Test GetAllCancelOrders")
	void testGetAllProcessingOrders() {
		// Setup our mock repository
				Order order1 = new Order();
				Order order2 = new Order();

				doReturn(Arrays.asList(order1, order2)).when(orderRepository).getProcessingOrders();

				// Execute the service call
				List<Order> widgets = orderService.getAllProcessingOrders();
				// Assert the response
				Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	
	@Test
	@DisplayName("Test GetAllCompletedOrders")
	void testGetAllCompletedOrders() {
		// Setup our mock repository
				Order order1 = new Order();
				Order order2 = new Order();

				doReturn(Arrays.asList(order1, order2)).when(orderRepository).getCompletedOrders();

				// Execute the service call
				List<Order> widgets = orderService.getAllCompletedOrders();
				// Assert the response
				Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	

	@Test
	@DisplayName("Test GetAllCompletedOrders")
	void testViewOrders() {
		// Setup our mock repository
				Order order1 = new Order();
				Order order2 = new Order();

				doReturn(Arrays.asList(order1, order2)).when(orderRepository).viewOrders();

				// Execute the service call
				List<Order> widgets = orderService.viewOrders();
				// Assert the response
				Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	
	@Test
	@DisplayName("Test GetAllCompletedOrders")
	void testViewItemsForOrder() {
		// Setup our mock repository
				Order order1 = new Order();
				Order order2 = new Order();

				doReturn(Arrays.asList(order1, order2)).when(orderRepository).itemListOfOrder("2022-01-05");

				// Execute the service call
				List<Order> widgets = orderService.viewItemsForOrder("2022-01-05");
				// Assert the response
				Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	
	@Test
	@DisplayName("Test ViewOrderDetailsUser")
	void testPharmacistConfirmation() {
		Optional<Order> order = Optional.of(new Order());

		doReturn(order).when(orderRepository).findById(order.get().getId());
		MessageResponse message=orderService.pharmacistConfirmation(order.get().getId());
		Assertions.assertEquals(message.getMessage(), "Order is marked as completed");
	}
	
	
	


}
