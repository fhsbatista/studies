package com.fhsbatista.meteora.products;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {
    @CsvBindByName(column = "ProductId", required = true)
    private int id;

    @CsvBindByName(column = "ProductName", required = true)
    private String name;

    @CsvBindByName(column = "Description", required = true)
    private String description;

    @CsvBindByName(column = "Price", required = true)
    private BigDecimal price;

    @CsvBindByName(column = "Category", required = true)
    private String category;

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Description: " + description + " | Price: " + price + " | Category: " + category;
    }
}
