package com.yundin.gladstest.presenter;

import com.yundin.gladstest.R;
import com.yundin.gladstest.model.RepositoryProvider;
import com.yundin.gladstest.model.dto.Category;
import com.yundin.gladstest.model.dto.Product;
import com.yundin.gladstest.view.IProductListView;

import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;

/**
 * @author Yundin Vladislav
 */
public class ProductListPresenter {

    private LifecycleHandler lifecycleHandler;
    private IProductListView view;

    public ProductListPresenter(IProductListView view, LifecycleHandler lifecycleHandler) {
        this.lifecycleHandler = lifecycleHandler;
        this.view = view;
    }

    public void updateProductsData(String title) {
        RepositoryProvider.provideApiRepository()
                .getProductsByCategory(title)
                .compose(lifecycleHandler.reload(R.id.product_list_request))
                .subscribe(this::onProductsUpdated, view::showError);
    }

    public void updateCategories() {
        RepositoryProvider.provideApiRepository()
                .getCategories()
                .compose(lifecycleHandler.reload(R.id.categories_list_request))
                .subscribe(this::onCategoriesUpdated, view::stopRefreshing);
    }

    private void onProductsUpdated(List<Product> data) {
        List<Product> oldData = getDataByCategoryTitle(view.getTitle());
        if (!oldData.equals(data) && !data.isEmpty()) {
            updateProductsDataBase(data);
        }
        view.onProductsDataUpdated(data);
    }

    private void onCategoriesUpdated(List<Category> data) {
        if (!data.isEmpty()) {
            updateCategoriesDatabase(data);
            view.onCategoriesDataUpdated(data);
        }
    }

    private void updateProductsDataBase(List<Product> data) {
        RepositoryProvider.provideDatabaseRepository()
                .updateOneCategoryData(data, view.getTitle());
    }

    private void updateCategoriesDatabase(List<Category> data) {
        RepositoryProvider.provideDatabaseRepository()
                .updateCategoriesData(data);
    }

    public List<Category> getCategories() {
        return RepositoryProvider.provideDatabaseRepository()
                .getCategories();
    }

    public List<Product> getDataByCategoryTitle(String title) {
        return RepositoryProvider.provideDatabaseRepository()
                .getDataByCategoryTitle(title);
    }
}
