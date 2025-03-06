package org.example;

import org.example.Amazon.*;
import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AmazonUnitTest {

    private ShoppingCart cartM;
    private PriceRule mockRule;
    private Amazon amazon;
    private Database database;
    private ShoppingCartAdaptor shoppingCart;

    @BeforeEach
    void setUp() {
        cartM = mock(ShoppingCart.class);
        mockRule = mock(PriceRule.class);
        amazon = new Amazon(cartM, List.of(mockRule));
        database = new Database();
        shoppingCart = new ShoppingCartAdaptor(database);
        database.resetDatabase();
    }

    @Test
    @DisplayName("Amazon")
    void OneRuleCalculateTest() {
        when(cartM.getItems()).thenReturn(List.of(new Item(ItemType.OTHER, "Crumbl Cookie", 2, 10.00)));
        when(mockRule.priceToAggregate(any())).thenReturn(20.00);

        double result = amazon.calculate();
        assertEquals(20.00, result);
    }

    @Test
    @DisplayName("Amazon")
    void MultiRuleCalculateTest() {
        PriceRule rule2 = mock(PriceRule.class);

        amazon = new Amazon(cartM, List.of(mockRule, rule2));

        when(cartM.getItems()).thenReturn(List.of(new Item(ItemType.OTHER, " Erwon Strawberry", 1, 15.00)));
        when(mockRule.priceToAggregate(any())).thenReturn(10.00);
        when(rule2.priceToAggregate(any())).thenReturn(5.00);

        double result = amazon.calculate();
        assertEquals(15.00, result);
    }

    @Test
    @DisplayName("Amazon")
    void AddToCartTest() {
        Item item = new Item(ItemType.ELECTRONIC, "Laptop", 1, 1500.00);

        amazon.addToCart(item);

        verify(cartM, times(1)).add(item);
    }



    @Test
    @DisplayName("Shopping Cart Adapter")
    void AddItemToCartTest() {
        Item item = new Item(ItemType.OTHER, "Insomnia Cookie", 1, 4.00);
        shoppingCart.add(item);

        List<Item> items = shoppingCart.getItems();
        assertEquals(1, items.size());
        assertEquals("Insomnia Cookie", items.get(0).getName());
    }

    @Test
    @DisplayName("Shopping Cart Adapter")
    void GetItemsCountTest() {
        shoppingCart.add(new Item(ItemType.OTHER, "Paperback Book", 1, 25.00));
        shoppingCart.add(new Item(ItemType.ELECTRONIC, "Headphones", 2, 35.00));

        List<Item> items = shoppingCart.getItems();
        assertEquals(2, items.size());
    }




}
