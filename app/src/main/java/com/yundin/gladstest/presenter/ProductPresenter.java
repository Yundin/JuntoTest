package com.yundin.gladstest.presenter;

import com.yundin.gladstest.model.RepositoryProvider;
import com.yundin.gladstest.model.dto.Product;


/**
 * @author Yundin Vladislav
 */
public class ProductPresenter {

    public Product getData(String title) {
        return RepositoryProvider.provideDatabaseRepository()
                .getProductByTitle(title);
    }
}
