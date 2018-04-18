package com.yundin.gladstest.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.realm.RealmObject;

/**
 * @author Yundin Vladislav
 */
public class Thumbnail extends RealmObject {
    @SerializedName("image_url")
    String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Thumbnail)) {
            return false;
        }
        return ((Thumbnail) obj).imageUrl.equals(imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUrl);
    }
}
