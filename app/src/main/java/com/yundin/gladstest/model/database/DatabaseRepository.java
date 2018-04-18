package com.yundin.gladstest.model.database;

import com.yundin.gladstest.model.dto.Category;
import com.yundin.gladstest.model.dto.Product;

import java.util.List;

/**
 * @author Yundin Vladislav
 */
public interface DatabaseRepository {

    List<Product> getDataByCategoryTitle(String title);
    List<Category> getCategories();
    Product getProductByTitle(String title);

    void updateOneCategoryData(List<Product> data, String category);
    void updateCategoriesData(List<Category> data);
}
