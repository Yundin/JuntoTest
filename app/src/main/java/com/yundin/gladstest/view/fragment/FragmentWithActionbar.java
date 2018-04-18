package com.yundin.gladstest.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;
import android.widget.TextView;

import com.yundin.gladstest.R;
import com.yundin.gladstest.model.dto.Category;

import java.util.List;

/**
 * @author Yundin Vladislav
 */
public abstract class FragmentWithActionbar extends Fragment {

    String title;
    Category category;

    public FragmentWithActionbar() {
    }

    void initToolbar(Toolbar toolbar, TextView toolbarTitle, String title) {

        this.title = title;
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbarTitle.setText(title);
    }

    void initToolbarWithoutTitle(Toolbar toolbar, Category category) {

        this.category = category;
        toolbar.setTitle("");
    }

    public abstract boolean backPressed();
}
