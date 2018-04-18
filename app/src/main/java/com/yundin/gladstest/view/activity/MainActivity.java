package com.yundin.gladstest.view.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yundin.gladstest.model.dto.Category;
import com.yundin.gladstest.view.event.BackPressedEvent;
import com.yundin.gladstest.view.event.CategoryChangedEvent;
import com.yundin.gladstest.view.event.ProductClickedEvent;
import com.yundin.gladstest.R;
import com.yundin.gladstest.view.fragment.FragmentWithActionbar;
import com.yundin.gladstest.view.fragment.ProductFragment;
import com.yundin.gladstest.view.fragment.ProductListFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    public static final String DEFAULT_CATEGORY = "Tech";
    public static final String DEFAULT_CATEGORY_REQUEST = "tech";
    private Category currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        if (savedInstanceState != null) {
            currentCategory = new Category(savedInstanceState.getString(ProductListFragment.CATEGORY_BUNDLE_KEY),
                    savedInstanceState.getString(ProductListFragment.REQUEST_NAME_BUNDLE_KEY));
        } else {
            currentCategory = new Category(DEFAULT_CATEGORY, DEFAULT_CATEGORY_REQUEST);
        }

        if (getSupportFragmentManager().getFragments().isEmpty()) {
            replaceProductListFragment(currentCategory.getTitle(), currentCategory.getRequestName());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void replaceProductListFragment(String categoryName, String requestName) {

        ProductListFragment fragment = new ProductListFragment();
        Bundle categoryBundle = new Bundle();
        categoryBundle.putString(ProductListFragment.CATEGORY_BUNDLE_KEY, categoryName);
        categoryBundle.putString(ProductListFragment.REQUEST_NAME_BUNDLE_KEY, requestName);
        fragment.setArguments(categoryBundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    @Subscribe
    public void onBackPressed(@Nullable BackPressedEvent event) {
        getSupportFragmentManager().popBackStack();
    }

    @Subscribe
    public void onItemClicked(@NonNull ProductClickedEvent event) {
        ProductFragment fragment = new ProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ProductFragment.TITLE, event.getTitle());
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.slide_from_left, R.animator.scale_down_fade, R.animator.scale_up_unfade, R.animator.slide_to_right_fade)
                .add(R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Subscribe
    public void onCategoryChanged(@NonNull CategoryChangedEvent event) {
        currentCategory = event.newCategory;
        replaceProductListFragment(event.newCategory.getTitle(), event.newCategory.getRequestName());
    }

    @Override
    public void onBackPressed() {

        FragmentWithActionbar fragment = (FragmentWithActionbar) getSupportFragmentManager().findFragmentById(R.id.content);
        if (!fragment.backPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ProductListFragment.CATEGORY_BUNDLE_KEY, currentCategory.getTitle());
        outState.putString(ProductListFragment.REQUEST_NAME_BUNDLE_KEY, currentCategory.getRequestName());
    }
}
