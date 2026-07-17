package com.example.productapp.service;

import com.example.productapp.model.Product;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private final AtomicInteger sequence = new AtomicInteger(0);

    public ProductService() {
        // Du lieu mau ban dau (id se duoc gan lai boi add()).
        add(new Product(0, "Laptop", 10000000));
        add(new Product(0, "Mouse", 200000));
    }
    public List<Product> findAll() {
        return products;
    }

    public List<Product> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return findAll();
        }
        String kw = keyword.trim().toLowerCase();
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getName() != null && p.getName().toLowerCase().contains(kw)) {
                result.add(p);
            }
        }
        return result;
    }
    public Product findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Product add(Product product) {
        product.setId(sequence.incrementAndGet());
        products.add(product);
        return product;
    }
    public boolean deleteById(int id) {
        return products.removeIf(p -> p.getId() == id);
    }
    public String formatPrice(double price) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("#,##0", symbols);
        return formatter.format(price) + " ₫";
    }
}
