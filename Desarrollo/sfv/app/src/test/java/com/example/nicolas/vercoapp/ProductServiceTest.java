package com.example.nicolas.vercoapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Frank on 18/11/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService=new ProductService();
    @Test
    public void getPercentDiscount_isCorrect(){
        assertTrue("Porcentaje en el rango", 0 <= productService.getPercentDiscount(12.345));
    }
    @Test
    public void getDiscountPrice_isCorrect(){
        assertTrue("Descuento menor que el precio", 13.89 > productService.getDiscountPrice(0.002,13.89));
    }
    @Test
    public void getNameFilterType_isCorrect(){
        assertNotNull(productService.getNameFilterType(2));
    }
}
