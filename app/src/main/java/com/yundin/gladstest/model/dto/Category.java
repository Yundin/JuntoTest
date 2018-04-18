package com.yundin.gladstest.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.realm.RealmObject;

/**
 * @author Yundin Vladislav
 */
public class Category extends RealmObject {

    @SerializedName("name")
    private String title;
    @SerializedName("slug")
    private String requestName;

    public Category(){}

    public Category(String title, String requestName) {
        this.title = title;
        this.requestName = requestName;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public String getRequestName() {
        return requestName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Category)) {
            return false;
        }
        Category category = (Category) obj;
        return title.equals(category.title) && requestName.equals(category.requestName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, requestName);
    }
}
