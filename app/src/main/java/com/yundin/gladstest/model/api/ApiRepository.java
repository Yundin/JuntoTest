package com.yundin.gladstest.model.api;

import com.yundin.gladstest.model.dto.Category;
import com.yundin.gladstest.model.dto.Product;

import java.util.List;

import rx.Observable;

/**
 * @author Yundin Vladislav
 */
public interface ApiRepository {

    Observable<List<Product>> getProductsByCategory(String category);

    Observable<List<Category>> getCategories();
}
