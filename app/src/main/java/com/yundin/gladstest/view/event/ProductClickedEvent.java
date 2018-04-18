package com.yundin.gladstest.view.event;

/**
 * @author Yundin Vladislav
 */
public class ProductClickedEvent {

    private String title;

    public ProductClickedEvent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
