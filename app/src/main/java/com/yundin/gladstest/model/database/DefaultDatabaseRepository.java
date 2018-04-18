package com.yundin.gladstest.model.database;

import com.yundin.gladstest.model.dto.Category;
import com.yundin.gladstest.model.dto.Product;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * @author Yundin Vladislav
 */
public class DefaultDatabaseRepository implements DatabaseRepository {

    @Override
    public List<Product> getDataByCategoryTitle(String title) {
        return new ArrayList<>(Realm.getDefaultInstance()
                .where(Product.class)
                .equalTo("category", title)
                .findAll());
    }

    @Override
    public List<Category> getCategories() {
        return new ArrayList<>(Realm.getDefaultInstance()
                .where(Category.class)
                .findAll());
    }

    @Override
    public Product getProductByTitle(String title) {
        return Realm.getDefaultInstance()
                .where(Product.class)
                .equalTo("title", title)
                .findFirst();
    }

    @Override
    public void updateOneCategoryData(List<Product> data, String category) {
        Realm realm = Realm.getDefaultInstance();
        for (Product product: data) {
            product.setCategory(category);
        }
        realm.executeTransactionAsync(realm1 -> {
            realm1.where(Product.class).equalTo("category", category).findAll().deleteAllFromRealm();
            realm1.copyToRealm(data);
        });
    }

    @Override
    public void updateCategoriesData(List<Category> data) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(realm1 -> {
            realm1.where(Category.class).findAll().deleteAllFromRealm();
            realm1.copyToRealm(data);
        });
    }
}
