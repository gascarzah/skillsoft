package com.example.nicolas.vercoapp;

/**
 * Created by Frank on 18/11/2017.
 */

public class ProductService {
    public Double getPercentDiscount(Double discount){
        return (double)Math.round(discount*100.0);
    }
    public Double getDiscountPrice(Double discountPercent,Double price){
        return (double)Math.round(price*(1 - discountPercent));
    }
    public String getNameFilterType(int filterType){
        System.out.print("Tipo de Filtro"+filterType);
        String filterString="";
        switch (filterType) {
            case 0:
                filterString="hombre";
                break;
            case 1:
                filterString="mujer";
                break;
            case 2:
                filterString="mostrar todo";
                break;
        }
        return filterString;
    }
}
