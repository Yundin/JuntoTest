package com.yundin.gladstest.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Yundin Vladislav
 */
public class Product extends RealmObject {

    @SerializedName("name")
    String title;
    @SerializedName("tagline")
    String description;
    @SerializedName("votes_count")
    int votes;
    @SerializedName("thumbnail")
    Thumbnail thumbnail;
    @SerializedName("screenshot_url")
    Screenshot screenshot;
    @SerializedName("discussion_url")
    String url;
    String category;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getVotes() {
        return votes;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public Screenshot getScreenshot() {
        return screenshot;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Product)) {
            return false;
        }
        Product item = (Product) obj;
        return item.title.equals(title) && item.description.equals(description) && item.votes == votes &&
                item.thumbnail.equals(thumbnail) && item.screenshot.equals(screenshot) && item.url.equals(url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, votes, thumbnail, screenshot, url);
    }
}
