package com.yundin.gladstest.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yundin.gladstest.model.dto.Category;
import com.yundin.gladstest.view.event.CategoryChangedEvent;
import com.yundin.gladstest.view.event.ProductClickedEvent;
import com.yundin.gladstest.R;
import com.yundin.gladstest.model.dto.Product;
import com.yundin.gladstest.presenter.ProductListPresenter;
import com.yundin.gladstest.view.IProductListView;
import com.yundin.gladstest.view.adapter.ProductListAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

/**
 * @author Yundin Vladislav
 */
public class ProductListFragment extends FragmentWithActionbar implements SwipeRefreshLayout.OnRefreshListener, IProductListView {

    public static final String CATEGORY_BUNDLE_KEY = "category";
    public static final String REQUEST_NAME_BUNDLE_KEY = "slug";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.no_data)
    TextView noDataTextView;
    private ProductListAdapter recyclerAdapter;
    private ProductListPresenter presenter;
    private ArrayAdapter<Category> spinnerAdapter;
    private Category currentCategory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        init();
        swipeRefreshLayout.setRefreshing(true);
        presenter.updateProductsData(super.category.getRequestName());
        presenter.updateCategories();
    }

    private void init() {

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(getContext(), getActivity().getSupportLoaderManager());
        presenter = new ProductListPresenter(this, lifecycleHandler);

        Bundle bundle = getArguments();
        currentCategory = new Category(bundle.getString(CATEGORY_BUNDLE_KEY), bundle.getString(REQUEST_NAME_BUNDLE_KEY));
        initToolbarWithoutTitle(toolbar, currentCategory);

        View.OnClickListener listener = v -> EventBus.getDefault().post(new ProductClickedEvent(v.getTag().toString()));

        recyclerAdapter = new ProductListAdapter(getContext(), presenter.getDataByCategoryTitle(super.category.getTitle()), listener);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        swipeRefreshLayout.setOnRefreshListener(this);

        spinnerAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, presenter.getCategories());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(presenter.getCategories().indexOf(currentCategory), false);
        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spinnerAdapter.getItem(position).equals(currentCategory)) {
                    EventBus.getDefault().post(new CategoryChangedEvent(spinnerAdapter.getItem(position)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner.setOnItemSelectedListener(spinnerListener);
    }

    @Override
    public void onProductsDataUpdated(List<Product> data) {
        swipeRefreshLayout.setRefreshing(false);
        recyclerAdapter.updateDataWithNotify(data);
        if (data.isEmpty()) {
            noDataTextView.setVisibility(View.VISIBLE);
        } else {
            noDataTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCategoriesDataUpdated(List<Category> data) {
        spinnerAdapter.clear();
        spinnerAdapter.addAll(data);
        spinnerAdapter.notifyDataSetChanged();
        spinner.setSelection(data.indexOf(currentCategory), false);
    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void showError(Throwable throwable) {
        throwable.printStackTrace();
        swipeRefreshLayout.setRefreshing(false);
        if (getActivity() != null && isAdded()) {
            Toast.makeText(getActivity().getApplicationContext(), R.string.no_connection, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String getTitle() {
        return super.category.getTitle();
    }

    @Override
    public void onRefresh() {
        presenter.updateProductsData(super.category.getRequestName());
    }

    @Override
    public void stopRefreshing(Throwable throwable) {
        swipeRefreshLayout.setRefreshing(false);
    }
}
