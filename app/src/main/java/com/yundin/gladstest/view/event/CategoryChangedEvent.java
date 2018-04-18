package com.yundin.gladstest.view.event;

import com.yundin.gladstest.model.dto.Category;

/**
 * @author Yundin Vladislav
 */
public class CategoryChangedEvent {

    public Category newCategory;

    public CategoryChangedEvent(Category newCategory) {
        this.newCategory = newCategory;
    }
}
