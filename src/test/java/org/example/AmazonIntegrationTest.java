package org.example;

import org.example.Amazon.Amazon;
import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Cost.PriceRule;
import org.example.Amazon.Database;
import org.example.Amazon.Item;
import org.example.Amazon.ShoppingCartAdaptor;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmazonIntegrationTest {

    private Database database;
    private ShoppingCartAdaptor cart;
    private Amazon amazon;


    @BeforeEach
    void setUp() {
        database = new Database(); // Use real database
        cart = new ShoppingCartAdaptor(database);
        amazon = new Amazon(cart, new ArrayList<>());
        database.resetDatabase();
    }


    @Test
    void AddAndGetItemsTest() {
        Item help_book = new Item(ItemType.OTHER, "Self Help Book", 1, 35.00);
        Item headphones = new Item(ItemType.ELECTRONIC, "Headphones", 2, 55.00);

        cart.add(help_book);
        cart.add(headphones);

        List<Item> items = cart.getItems();

        assertEquals(2, items.size());
        assertEquals("Self Help Book", items.get(0).getName());
        assertEquals("Headphones", items.get(1).getName());
    }

    @Test
    void MultiItemTotalPriceTest() {
        // Add items
        cart.add(new Item(ItemType.OTHER, "Cleaning Supplies", 1, 50.00));
        cart.add(new Item(ItemType.ELECTRONIC, "Mouse", 2, 25.00));

        PriceRule rule = items -> items.stream().mapToDouble(item -> item.getQuantity() * item.getPricePerUnit()).sum();
        amazon = new Amazon(cart, List.of(rule));

        double totalPrice = amazon.calculate();

        assertEquals(100.00, totalPrice);
    }


    @Test
    void ResetDatabaseTest() {
        cart.add(new Item(ItemType.OTHER, "Testing guide", 1, 55.00));

        database.resetDatabase();

        List<Item> itemsAfterReset = cart.getItems();
        assertTrue(itemsAfterReset.isEmpty());
    }


}
