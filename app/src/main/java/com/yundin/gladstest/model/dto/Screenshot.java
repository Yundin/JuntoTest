package com.yundin.gladstest.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.realm.RealmObject;

/**
 * @author Yundin Vladislav
 */
public class Screenshot extends RealmObject {
    @SerializedName("850px")
    String URL;

    public String getURL() {
        return URL;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Screenshot)) {
            return false;
        }
        return ((Screenshot) obj).URL.equals(URL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(URL);
    }
}
