package ru.retrofit.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.retrofit.dto.Product;
import ru.retrofit.enums.Category;
import ru.retrofit.enums.Title;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class PutProductTests extends BaseTest{

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
    void updatePriceTest() throws IOException {
        product.setPrice(111);
        Response<Product> response = productService
                .updateProduct(product)
                .execute();
        id = response.body().getId();
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
        assertThat(response.body().getTitle()).isEqualTo(product.getTitle());
        assertThat(response.body().getPrice()).isEqualTo(product.getPrice());
        assertThat(response.body().getId()).isNotNull();
    }

    @Test
    void updateTitleTest() throws IOException {
        product.setTitle(Title.CYRILLIC.getTitle());
        Response<Product> response = productService
                .updateProduct(product)
                .execute();
        id = response.body().getId();
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
        assertThat(response.body().getTitle()).isEqualTo(product.getTitle());
        assertThat(response.body().getPrice()).isEqualTo(product.getPrice());
        assertThat(response.body().getId()).isNotNull();
    }

    @Test
    void updateCategoryTest() throws IOException {
        product.setCategoryTitle(Category.FOOD.getName());
        Response<Product> response = productService
                .updateProduct(product)
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
