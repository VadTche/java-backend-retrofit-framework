package ru.retrofit.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.retrofit.dto.Product;
import ru.retrofit.enums.CategoryEnum;

import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductsTests extends BaseTest {
    Product product;
    Integer id;

    @BeforeEach
    void setUp() {
        product=  new Product()
                .withTitle(faker.food().dish())
                .withCategoryTitle(CategoryEnum.FOOD.getTitle())
                .withPrice(1000);
    }

    @Test
    void createProductWithIntPriceTest() throws IOException {
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
