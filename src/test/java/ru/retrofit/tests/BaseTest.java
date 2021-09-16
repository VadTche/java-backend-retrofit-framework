package ru.retrofit.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import retrofit2.Retrofit;
import ru.retrofit.service.CategoryService;
import ru.retrofit.service.ProductService;
import ru.retrofit.utils.RetrofitUtils;

public class BaseTest {
    static Retrofit retrofit;
    static CategoryService categoryService;
    static ProductService productService;
    static Faker faker;

    @BeforeAll //создание клиента
    static void beforeAll() {
        retrofit = RetrofitUtils.getRetrofit();
        categoryService = retrofit.create(CategoryService.class);
        productService = retrofit.create(ProductService.class);
        faker = new Faker();
    }
}
