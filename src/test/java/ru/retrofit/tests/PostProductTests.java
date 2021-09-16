package ru.retrofit.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.retrofit.dto.*;
import ru.retrofit.enums.Category;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class PostProductTests extends BaseTest {
    Product product;
    Integer id;

    @BeforeEach
    void setUp() {
        product=  new Product()
                .withTitle(faker.dog().memePhrase())
                .withCategoryTitle(Category.ELECTRONIC.getName())
                .withPrice(999);
    }

    @Test
    void IntegerPriceTest() throws IOException {
        product.setPrice(888);
        Response<Product> response = productService
                .createProduct(product)
                .execute();
        id = response.body().getId();
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
        assertThat(response.body().getTitle()).isEqualTo(product.getTitle());
        assertThat(response.body().getPrice()).isEqualTo(product.getPrice());
        assertThat(response.body().getId()).isNotNull();
    }

    @AfterEach
    void tearDown() throws IOException {
        productService.deleteProduct(id).execute();
    }
}
