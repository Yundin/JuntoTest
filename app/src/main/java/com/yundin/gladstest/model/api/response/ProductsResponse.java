package com.yundin.gladstest.model.api.response;

import com.google.gson.annotations.SerializedName;
import com.yundin.gladstest.model.dto.Product;

import java.util.List;

/**
 * @author Yundin Vladislav
 */
public class ProductsResponse {

    @SerializedName("posts")
    List<Product> data;

    public List<Product> getData() {
        return data;
    }
}
