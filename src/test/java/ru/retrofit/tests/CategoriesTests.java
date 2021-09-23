package ru.retrofit.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;
import ru.retrofit.db.model.Categories;
import ru.retrofit.db.model.CategoriesExample;
import ru.retrofit.dto.Category;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesTests extends BaseTest {


    @ParameterizedTest
    @EnumSource(value = ru.retrofit.enums.Category.class)
    void getCategoriesTest(ru.retrofit.enums.Category cat) throws IOException {
        Response<Category> response = categoryService
                .getCategory(cat.getId())
                .execute();
        if (response.body() != null) {
            assertThat(response.body().getTitle()).isEqualTo(cat.getName());
        }
        response.body().getProducts().forEach(e -> assertThat(e.getCategoryTitle()).isEqualTo(cat.getName()));

        CategoriesExample example = new CategoriesExample();
        example.createCriteria().andTitleEqualTo(cat.getName());
        Categories categoriesFromDb = categoriesMapper.selectByExample(example).get(0);
        assertThat(categoriesFromDb.getId()).isEqualTo(cat.getId());
    }
}
