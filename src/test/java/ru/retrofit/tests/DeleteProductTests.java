package ru.retrofit.tests;

import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.retrofit.dto.Product;

import java.io.IOException;

    public class DeleteProductTests extends BaseTest {
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
        void deleteExistingProductTest() throws IOException{

            Response<ResponseBody> response = productService
                    .deleteProduct(product.getId())
                    .execute();
            try {
                System.out.println(response.body().string());
            } catch (NullPointerException e) {
                System.out.println(response.errorBody().string());
            }
        }

        @Test
        void deleteUnExistingProductTest() throws IOException{

            Response<ResponseBody> response = productService
                    .deleteProduct(77777)
                    .execute();
            try {
                System.out.println(response.body().string());
            } catch (NullPointerException e) {
                System.out.println(response.errorBody().string());
            }
        }
    }

