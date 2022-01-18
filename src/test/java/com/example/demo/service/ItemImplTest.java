package com.example.demo.service;

import static org.mockito.ArgumentMatchers.any;
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
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;

@SpringBootTest
public class ItemImplTest {

	@Autowired
	private ItemService itemService;

	@MockBean
	private ItemRepository itemRepository;

	@Test
	@DisplayName("Test save")
	void testSave() {
		// Setup our mock repository
		Item item = new Item();

		doReturn(item).when(itemRepository).save(any());

		// Execute the service call
		itemService.addItem(item);
	}

	@Test
	@DisplayName("Test findAll")
	void testFindAll() {
		// Setup our mock repository
		Item item1 = new Item();
		Item item2 = new Item();

		doReturn(Arrays.asList(item1, item2)).when(itemRepository).findAll();
		// Execute the service call
		List<Item> items = itemService.getAllItems();

		// Assert the response
		Assertions.assertEquals(2, items.size(), "findAll should return 2 widgets");
	}

	@Test
	@DisplayName("Test getSelectedCategoryItem")
	void testGetSelectedCategoryItem() {
		// Setup our mock repository
		Item item1 = new Item();
		Item item2 = new Item();

		doReturn(Arrays.asList(item1, item2)).when(itemRepository).findBySpecifications("AnimalCare");
		// Execute the service call
		List<Item> items = itemService.getSelectedCategoryItem("AnimalCare");

		// Assert the response
		Assertions.assertEquals(2, items.size(), "findAll should return 2 widgets");
	}

	@Test
	@DisplayName("Test viewItemByID")
	void testViewItemByID() {
		Optional<Item> item = Optional.of(new Item());

		doReturn(item).when(itemRepository).findById(item.get().getId());

		Item item1 = itemService.viewItemByID(item.get().getId());

		Assertions.assertEquals(item1.getId(), item.get().getId());

	}

	@Test
	@DisplayName("Test viewItemByName")
	void testViewItemByName() {
		Optional<Item> item = Optional.of(new Item());

		doReturn(item.get()).when(itemRepository).findByName(item.get().getName());

		Item item1 = itemService.viewItemByName(item.get().getName());

		Assertions.assertEquals(item1.getId(), item.get().getId());

	}

	@Test
	@DisplayName("Test viewAllItems")
	void testViewAll() {
		// Setup our mock repository
		Item item1 = new Item();
		Item item2 = new Item();

		doReturn(Arrays.asList(item1, item2)).when(itemRepository).findAll();
		// Execute the service call
		List<Item> items = itemService.viewAllItems();

		// Assert the response
		Assertions.assertEquals(2, items.size(), "findAll should return 2 widgets");
	}

	@Test
	@DisplayName("Test  deleteItem")
	void testDeleteItem() {
		Optional<Item> item = Optional.of(new Item());

		doReturn(item).when(itemRepository).findById(item.get().getId());

		MessageResponse msg = itemService.deleteItem(item.get().getId());

		Assertions.assertEquals(msg.getMessage(), "Item is successfully deleted.");

	}

	@Test
	@DisplayName("Test advanceItemSearch")
	void testAdvanceItemSearch() {
		// Setup our mock repository
		Item item1 = new Item();
		Item item2 = new Item();

		doReturn(Arrays.asList(item1, item2)).when(itemRepository).search("dog");

		List<Item> items = itemService.advanceItemSearch("dog");

		Assertions.assertEquals(2, items.size(), "findAll should return 2 widgets");

	}
	
}
