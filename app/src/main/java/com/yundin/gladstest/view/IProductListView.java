package com.yundin.gladstest.view;

import com.yundin.gladstest.model.dto.Category;
import com.yundin.gladstest.model.dto.Product;

import java.util.List;

/**
 * @author Yundin Vladislav
 */
public interface IProductListView {

    void onProductsDataUpdated(List<Product> data);
    void onCategoriesDataUpdated(List<Category> data);
    void showError(Throwable throwable);
    void stopRefreshing(Throwable throwable);
    String getTitle();
}
