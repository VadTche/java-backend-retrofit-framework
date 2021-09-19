package ru.retrofit.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.retrofit.dto.Product;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class GetProductIDTests extends BaseTest {

    Product product;
    Integer id;

    @BeforeEach
    void setUp() throws IOException {
        product = new Product()
                .withTitle(faker.animal().name())
                .withCategoryTitle("Electronic")
                .withPrice(999);
        Response<Product> response = productService
                .createProduct(product)
                .execute();
        id = response.body().getId();
        product.setId(id);
    }

    @Test
    void GetExistingIDTest() throws IOException {

        Response<Product> response = productService
                .getProduct(product.getId())
                .execute();
        id = response.body().getId();
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
        assertThat(response.body().getTitle()).isEqualTo(product.getTitle());
        assertThat(response.body().getPrice()).isEqualTo(product.getPrice());
        assertThat(response.body().getId()).isNotNull();
    }

    @Test
    void GetUnExistingIDTest() throws IOException {

        Response<Product> response = productService
                .getProduct(77777)
                .execute();
        try {
            System.out.println(response.body().getId());
        } catch (NullPointerException e) {
            System.out.println(response.errorBody().string());
        }
    }

    @AfterEach
    void tearDown() throws IOException {
        productService.deleteProduct(id).execute();
    }
}
