package ru.retrofit.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;
import ru.retrofit.db.model.Products;
import ru.retrofit.db.model.ProductsExample;
import ru.retrofit.dto.Product;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class PostProductTests extends BaseTest {
    Product product;
    Integer id;

    @ParameterizedTest
    @EnumSource(value = ru.retrofit.enums.Title.class)
    void createTitleTest(ru.retrofit.enums.Title tit) throws IOException {
        product=  new Product()
                .withTitle(tit.getTitle())
                .withPrice(tit.getPrice())
                .withCategoryTitle(testCategory.getTitle());
        Response<Product> response = productService
                .createProduct(product)
                .execute();
        id = response.body().getId();
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
        assertThat(response.body().getTitle()).isEqualTo(product.getTitle());
        assertThat(response.body().getPrice()).isEqualTo(product.getPrice());
        assertThat(response.body().getId()).isNotNull();

        ProductsExample example = new ProductsExample();
        example.createCriteria()
                .andCategory_idEqualTo(Long.valueOf(testCategory.getId()))
                .andPriceEqualTo(tit.getPrice())
                .andTitleEqualTo(tit.getTitle());
        Products productFromDb = productsMapper.selectByExample(example).get(0);
        assertThat(productFromDb.getPrice()).isEqualTo(product.getPrice());
        assertThat(productFromDb.getCategory_id()).isEqualTo(Long.valueOf(testCategory.getId()));
        //не очень уверен, проверяется ли категория на самом деле или это тождество уже изначально :)
        assertThat(productFromDb.getTitle()).isEqualTo(product.getTitle());
    }

    @ParameterizedTest
    @EnumSource(value = ru.retrofit.enums.Price.class)
    void createPriceTest(ru.retrofit.enums.Price pr) throws IOException {
        product=  new Product()
                .withTitle(pr.getTitle())
                .withPrice(pr.getPrice())
                .withCategoryTitle(testCategory.getTitle());
        Response<Product> response = productService
                .createProduct(product)
                .execute();
        id = response.body().getId();
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
        assertThat(response.body().getTitle()).isEqualTo(product.getTitle());
        assertThat(response.body().getPrice()).isEqualTo(product.getPrice());
        assertThat(response.body().getId()).isNotNull();

        ProductsExample example = new ProductsExample();
        example.createCriteria()
                .andCategory_idEqualTo(Long.valueOf(testCategory.getId()))
                .andPriceEqualTo(pr.getPrice())
                .andTitleEqualTo(pr.getTitle());
        Products productFromDb = productsMapper.selectByExample(example).get(0);
        assertThat(productFromDb.getPrice()).isEqualTo(product.getPrice());
        assertThat(productFromDb.getCategory_id()).isEqualTo(Long.valueOf(testCategory.getId()));
        assertThat(productFromDb.getTitle()).isEqualTo(product.getTitle());
    }

    @AfterEach
    void tearDown() throws IOException {
        productService.deleteProduct(id).execute();
    }
}
