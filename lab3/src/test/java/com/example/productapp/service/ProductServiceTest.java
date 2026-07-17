package com.example.productapp.service;

import com.example.productapp.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ProductServiceTest {

    private ProductService service;

    @BeforeEach
    void setUp() {
        service = new ProductService();
    }

    @Test
    void seedsTwoProductsAtStartup() {
        assertThat(service.findAll()).hasSize(2);
        assertThat(service.findById(1).getName()).isEqualTo("Laptop");
        assertThat(service.findById(2).getName()).isEqualTo("Mouse");
    }

    @Test
    void addAssignsIncrementingId() {
        Product added = service.add(new Product(0, "Keyboard", 500000));
        assertThat(added.getId()).isEqualTo(3);
        assertThat(service.findAll()).hasSize(3);
    }

    @Test
    void searchByNameIsCaseInsensitive() {
        assertThat(service.search("lap")).extracting(Product::getName).containsExactly("Laptop");
        assertThat(service.search("MOUSE")).extracting(Product::getName).containsExactly("Mouse");
    }

    @Test
    void searchWithEmptyKeywordReturnsAll() {
        assertThat(service.search("")).hasSize(2);
        assertThat(service.search("   ")).hasSize(2);
        assertThat(service.search(null)).hasSize(2);
    }

    @Test
    void searchWithNoMatchReturnsEmpty() {
        assertThat(service.search("khong-ton-tai")).isEmpty();
    }

    @Test
    void findByIdReturnsNullWhenMissing() {
        assertThat(service.findById(999)).isNull();
    }

    @Test
    void deleteRemovesProduct() {
        assertThat(service.deleteById(1)).isTrue();
        assertThat(service.findById(1)).isNull();
        assertThat(service.findAll()).hasSize(1);
    }

    @Test
    void deleteMissingIdReturnsFalse() {
        assertThat(service.deleteById(999)).isFalse();
        assertThat(service.findAll()).hasSize(2);
    }

    @Test
    void idStaysUniqueAfterDelete() {
        service.deleteById(2);
        Product added = service.add(new Product(0, "Monitor", 3000000));
        assertThat(added.getId()).isEqualTo(3);
    }

    @Test
    void formatPriceUsesVietnameseGrouping() {
        assertThat(service.formatPrice(15000000)).isEqualTo("10.000.000 ₫");
        assertThat(service.formatPrice(250000)).isEqualTo("200.000 ₫");
    }
}
