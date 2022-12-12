package com.example.foodcount.models;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @org.junit.jupiter.api.Test
    void getName() {
        Product test = new Product(null, "test");
        assertEquals("test", test.getName());
    }
}