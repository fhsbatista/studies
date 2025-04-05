package com.fhsbatista.meteora.products;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<Product> products = new CsvToBeanBuilder<Product>(new FileReader("src/main/resources/products.csv"))
                .withType(Product.class).build().parse();

        for (Product product : products) {
            System.out.println(product);
        }


        for (Product product : products) {
            ProductTranslator.translate(product);
            System.out.println(product);
        }
    }
}
