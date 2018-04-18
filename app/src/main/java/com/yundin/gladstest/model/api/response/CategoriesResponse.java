package com.yundin.gladstest.model.api.response;

import com.google.gson.annotations.SerializedName;
import com.yundin.gladstest.model.dto.Category;

import java.util.List;

/**
 * @author Yundin Vladislav
 */
public class CategoriesResponse {

    @SerializedName("topics")
    List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }
}
