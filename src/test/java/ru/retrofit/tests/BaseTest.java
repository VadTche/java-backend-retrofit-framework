package ru.retrofit.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import retrofit2.Retrofit;
import ru.retrofit.db.dao.CategoriesMapper;
import ru.retrofit.db.dao.ProductsMapper;
import ru.retrofit.db.model.Categories;
import ru.retrofit.service.CategoryService;
import ru.retrofit.service.ProductService;
import ru.retrofit.utils.DbUtils;
import ru.retrofit.utils.RetrofitUtils;

public class BaseTest {
    static Retrofit retrofit;
    static CategoryService categoryService;
    static ProductService productService;
    static Faker faker;
    static ProductsMapper productsMapper;
    static CategoriesMapper categoriesMapper;
    static Categories testCategory;

    @BeforeAll
    static void beforeAll() {
        retrofit = RetrofitUtils.getRetrofit();
        categoryService = retrofit.create(CategoryService.class);
        productService = retrofit.create(ProductService.class);
        faker = new Faker();
        productsMapper = DbUtils.getProductsMapper();
        categoriesMapper = DbUtils.getCategoriesMapper();
        //создаем новую категорию
        String quote = faker.backToTheFuture().quote();
        testCategory = DbUtils.getNewTestCategory(quote);
    }

    @AfterAll
    static void afterAll() {
        //ProductsExample example = new ProductsExample();
        //example.createCriteria().andCategory_idEqualTo(Long.valueOf(testCategory.getId()));
        //productsMapper.deleteByExample(example);
        DbUtils.deleteAllProductsWithTheCategory(testCategory.getId());
        categoriesMapper.deleteByPrimaryKey(testCategory.getId());
    }
}
