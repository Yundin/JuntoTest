package com.yundin.gladstest.model.api;

import com.yundin.gladstest.model.api.response.CategoriesResponse;
import com.yundin.gladstest.model.api.response.ProductsResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author Yundin Vladislav
 */
public interface ApiInterface {

    @Headers({
            "Authorization: Bearer 591f99547f569b05ba7d8777e2e0824eea16c440292cce1f8dfb3952cc9937ff",
            "Accept: application/json",
            "Content-Type: application/json",
            "Host: api.producthunt.com"
    })
    @GET("v1/categories/{category}/posts")
    Observable<ProductsResponse> getProductsByCategory(@Path("category") String category);

    @Headers({
            "Authorization: Bearer 591f99547f569b05ba7d8777e2e0824eea16c440292cce1f8dfb3952cc9937ff",
            "Accept: application/json",
            "Content-Type: application/json",
            "Host: api.producthunt.com"
    })
    @GET("v1/topics?search[trending]=true")
    Observable<CategoriesResponse> getCategories();
}
