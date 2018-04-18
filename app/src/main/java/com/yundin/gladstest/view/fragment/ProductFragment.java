package com.yundin.gladstest.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrInterface;
import com.r0adkll.slidr.model.SlidrPosition;
import com.yundin.gladstest.view.event.BackPressedEvent;
import com.yundin.gladstest.R;
import com.yundin.gladstest.model.dto.Product;
import com.yundin.gladstest.presenter.ProductPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Yundin Vladislav
 */
public class ProductFragment extends FragmentWithActionbar{

    public static final String TITLE = "title";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.title)
    TextView titleTextView;
    @BindView(R.id.description)
    TextView descriptionTextView;
    @BindView(R.id.votes)
    TextView votesTextView;
    @BindView(R.id.get_btn)
    Button buttonGet;
    private SlidrInterface slidrInterface;
    private ProductPresenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        init();
    }

    public void init() {

        initToolbar(toolbar, toolbarTitle, getArguments().getString(ProductFragment.TITLE));
        presenter = new ProductPresenter();
        updateView(presenter.getData(super.title));
    }

    private void updateView(Product data) {

        titleTextView.setText(data.getTitle());
        descriptionTextView.setText(data.getDescription());
        votesTextView.setText(String.valueOf(data.getVotes()));
        buttonGet.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getUrl()));
            startActivity(browserIntent);
        });

        Glide
                .with(getContext())
                .load(data.getScreenshot().getURL())
                .into(imageView);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (slidrInterface == null) {
            slidrInterface = Slidr.replace(Objects.requireNonNull(getView()).findViewById(R.id.content_container), new SlidrConfig.Builder().position(SlidrPosition.LEFT).build());
        }
    }

    @Override
    public boolean backPressed() {
        EventBus.getDefault().post(new BackPressedEvent());
        return true;
    }
}
