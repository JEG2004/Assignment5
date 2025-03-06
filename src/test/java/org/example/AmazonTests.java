package org.example;

import org.example.Amazon.*;
import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AmazonTests {

    private org.example.Amazon.Database mockDatabase;
    private ShoppingCartAdaptor cart;
    private Amazon amazon;
    private List<PriceRule> mockrule;

    @BeforeEach
    void setUp(){
        mockDatabase = mock(Database.class);
        cart = new ShoppingCartAdaptor(mockDatabase);
        mockrule = new ArrayList<>();
        amazon = new Amazon(cart,mockrule);
    }

    @DisplayName("Specification Testing")
    @Test
    void AddToCartTest(){
        Item apple = new Item(ItemType.OTHER, "Apple", 2, 2.00);

        ShoppingCart mockCart = mock(ShoppingCart.class);
        Amazon amazon1 = new Amazon(mockCart,mockrule);

        amazon1.addToCart(apple);

        verify(mockCart).add(apple);
    }

    @DisplayName("Specification Tests")
    @Test
    void EmptyCartTest(){
        double cart = amazon.calculate();
        assertEquals(0.0, cart);
    }

    @DisplayName("Specification Testing")
    @Test
    void FullCartCalculationTest(){
        ShoppingCart mockCart = mock(ShoppingCart.class);
        PriceRule rule = mock(PriceRule.class);

        when(mockCart.getItems()).thenReturn(List.of(new Item(ItemType.ELECTRONIC, "Computer", 2, 12.00)));
        when(rule.priceToAggregate(any())).thenReturn(24.00);
        Amazon amazon1 = new Amazon(mockCart,List.of(rule));

        double actual = amazon1.calculate();

        assertEquals(24.00, actual);
        verify(rule).priceToAggregate(any());

    }

    @DisplayName("Structual-based Test")
    @Test
    void EmptyCalculateTest(){
        double total = amazon.calculate();
        assertEquals(0.00, total);
    }

    @DisplayName("Structual-based Testing")
    @Test
    void OneRuleTest(){
        PriceRule mockrule = mock(PriceRule.class);
        when(mockrule.priceToAggregate(any())).thenReturn(20.00);

        amazon = new Amazon(cart, List.of(mockrule));

        double result = amazon.calculate();

        assertEquals(20.00, result);
    }

    @Test
    @DisplayName("Structural-based Testing")
    void testAddToCart() {
        ShoppingCart mockCart = mock(ShoppingCart.class);
        Amazon amazon2 = new Amazon(mockCart, mockrule);

        Item item = new Item(ItemType.ELECTRONIC, "Tablet", 1, 145.00);
        amazon2.addToCart(item);

        verify(mockCart).add(item);
    }



}
