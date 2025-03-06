package org.example;

import org.example.Barnes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BarnesAndNobleTest {
    private BookDatabase mockDatabase;
    private BuyBookProcess mockProcess;
    private BarnesAndNoble barnes;

    @BeforeEach
    void setUp(){
        mockDatabase = mock(BookDatabase.class);
        mockProcess = mock(BuyBookProcess.class);
        barnes = new BarnesAndNoble(mockDatabase,mockProcess);
    }

    @DisplayName("Specification-based")
    @Test
    void nullTest(){
        assertEquals(null, barnes.getPriceForCart(null));
    }

    @DisplayName("Specification-based")
    @Test
    void correctPurchaseTest(){
        Book b = new Book("1234", 20, 40);
        when(mockDatabase.findByISBN("1234")).thenReturn(b);

        Map<String, Integer> order = new HashMap<>();
        order.put("1234", 2);
        PurchaseSummary sum = barnes.getPriceForCart(order);

        assertEquals(40, sum.getTotalPrice());
        verify(mockProcess).buyBook(b, 2);
    }

    @DisplayName("Specification-based")
    @Test
    void notEnoughQuantityTest(){
        Book b = new Book("1234", 20, 20);
        when(mockDatabase.findByISBN("1234")).thenReturn(b);

        Map<String, Integer> order = new HashMap<>();
        order.put("1234", 22);
        PurchaseSummary sum = barnes.getPriceForCart(order);

        assertEquals(2, sum.getUnavailable().get(b));
        verify(mockProcess).buyBook(b, 20);
    }


    @DisplayName("Structura-based")
    @Test
    void purchaseGoesThroughTest(){
        Book b = new Book("12345", 20, 40);
        when(mockDatabase.findByISBN("12345")).thenReturn(b);

        Map<String, Integer> order = new HashMap<>();
        order.put("12345", 2);
        PurchaseSummary sum = barnes.getPriceForCart(order);

        assertEquals(40, sum.getTotalPrice());
        verify(mockProcess).buyBook(b, 2);
    }

    @DisplayName("Structural-based")
    @Test
    void aboveQuantityTest(){
        Book b = new Book("1234", 5, 4);
        when(mockDatabase.findByISBN("1234")).thenReturn(b);

        Map<String, Integer> order = new HashMap<>();
        order.put("1234", 8);
        PurchaseSummary sum = barnes.getPriceForCart(order);

        assertEquals(4, sum.getUnavailable().get(b));
        verify(mockProcess).buyBook(b, 4);
    }

    @DisplayName("Structural-based")
    @Test
    void nullOrderTest(){
        assertEquals(null, barnes.getPriceForCart(null));
    }




}
