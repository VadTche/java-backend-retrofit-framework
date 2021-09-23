package ru.retrofit;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import ru.retrofit.db.dao.ProductsMapper;
import ru.retrofit.db.model.Products;
import ru.retrofit.db.model.ProductsExample;

import java.io.IOException;
@Slf4j
public class MainDb {
    static Faker faker = new Faker();
    private static String resource = "mybatisConfig.xml";
    public static void main(String[] args) {

        //открываем сессию
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //select product by ID
        ProductsMapper productsMapper = sqlSession.getMapper(ProductsMapper.class);
        Products product = productsMapper.selectByPrimaryKey(10399l);
        log.info(product.getTitle());

        //delete product by ID
//        productsMapper.deleteByPrimaryKey(9159l);
//        sqlSession.commit(); - это если без параметра true в openSession

        // select+count products (без фильтров)
        long count = productsMapper.countByExample(new ProductsExample());
        log.info(String.valueOf(count));

        // select+count products with the price
        ProductsExample example = new ProductsExample();
        example.createCriteria().andPriceGreaterThan(1000);
        log.info(String.valueOf(productsMapper.countByExample(example)));

        // select+list products with the category and the price
        example.clear();
        example.createCriteria().andCategory_idEqualTo(3l).andPriceGreaterThan(1000);
        productsMapper.selectByExample(example).forEach(e -> log.info(String.valueOf(e)));

        // insert product with the title and the price and the category ID
        int newId = productsMapper.insert(new Products(faker.animal().name(), 1500, 555L));
        log.info(String.valueOf(newId));

        // delete all products from our new category after inserting
        example.clear();
        example.createCriteria().andCategory_idEqualTo(555L);
        productsMapper.deleteByExample(example);

        // delete our new category (needs categoriesMapper)

    }
}
