package ru.retrofit.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;
import ru.retrofit.dto.Product;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class PostProductTests extends BaseTest {
    Product product;
    Integer id;

    @BeforeEach
    void setUp() {
        product=  new Product()
                .withTitle(faker.animal().name())
                .withPrice(999)
                .withCategoryTitle("Electronic");
    }

// не успел разобраться почему эти тесты проходят и с валидными и с невалидными значениями переменных
    @ParameterizedTest
    @EnumSource(value = ru.retrofit.enums.Title.class)
    void createTitleTest(ru.retrofit.enums.Title tit) throws IOException {
        product=  new Product()
                .withTitle(tit.getTitle())
                .withPrice(tit.getPrice())
                .withCategoryTitle(tit.getCategoryTitle());
        Response<Product> response = productService
                .createProduct(product)
                .execute();
        id = response.body().getId();
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
        assertThat(response.body().getTitle()).isEqualTo(tit.getTitle());
        assertThat(response.body().getPrice()).isEqualTo(product.getPrice());
        assertThat(response.body().getId()).isNotNull();
    }

    @ParameterizedTest
    @EnumSource(value = ru.retrofit.enums.Price.class)
    void createPriceTest(ru.retrofit.enums.Price pr) throws IOException {
        product=  new Product()
                .withTitle(pr.getTitle())
                .withPrice(pr.getPrice())
                .withCategoryTitle(pr.getCategoryTitle());
        Response<Product> response = productService
                .createProduct(product)
                .execute();
        id = response.body().getId();
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
        assertThat(response.body().getTitle()).isEqualTo(product.getTitle());
        assertThat(response.body().getPrice()).isEqualTo(pr.getPrice());
        assertThat(response.body().getId()).isNotNull();
    }

/*    @Test
    void IntegerPriceTest() throws IOException {
        product.setPrice(-888);
        Response<Product> response = productService
                .createProduct(product)
                .execute();
        id = response.body().getId();
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
        assertThat(response.body().getTitle()).isEqualTo(product.getTitle());
        assertThat(response.body().getPrice()).isEqualTo(product.getPrice());
        assertThat(response.body().getId()).isNotNull();
    }*/

    @AfterEach
    void tearDown() throws IOException {
        productService.deleteProduct(id).execute();
    }
}
