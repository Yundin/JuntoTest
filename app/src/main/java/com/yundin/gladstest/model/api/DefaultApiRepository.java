package com.yundin.gladstest.model.api;

import com.yundin.gladstest.model.api.response.CategoriesResponse;
import com.yundin.gladstest.model.api.response.ProductsResponse;
import com.yundin.gladstest.model.dto.Category;
import com.yundin.gladstest.model.dto.Product;

import java.util.List;

import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;


/**
 * @author Yundin Vladislav
 */
public class DefaultApiRepository implements ApiRepository {

    @Override
    public Observable<List<Product>> getProductsByCategory(String category) {
        return ApiFactory.getApiInterface()
                .getProductsByCategory(category)
                .map(ProductsResponse::getData)
                .compose(RxUtils.async());
    }

    @Override
    public Observable<List<Category>> getCategories() {
        return ApiFactory.getApiInterface()
                .getCategories()
                .map(CategoriesResponse::getCategories)
                .compose(RxUtils.async());
    }
}
