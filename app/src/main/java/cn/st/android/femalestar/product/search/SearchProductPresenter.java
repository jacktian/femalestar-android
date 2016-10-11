package cn.st.android.femalestar.product.search;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

import cn.st.android.femalestar.data.Product;

/**
 * Created by coolearth on 16-10-10.
 */

public class SearchProductPresenter implements SearchProductContract.Presenter{

    private SearchProductContract.View mView;

    @Override
    public void setView(SearchProductContract.View view) {
        mView= Preconditions.checkNotNull(view);
    }

    @Override
    public void showCapture() {
        mView.showCapture();
    }

    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if (SearchProductFragment.REQUEST_CAPTURE == requestCode && Activity.RESULT_OK == resultCode) {
            String result=data.getStringExtra("result");
            mView.showCaptureResult(result);
        }
    }

    @Override
    public void showAdd() {
        mView.showAdd();
    }

    @Override
    public void loadProducts() {
        Date now=new Date();
        List<Product> productList = Lists.newArrayList();
        productList.add(new Product("嘻嘻嘻","11222222222",now,now ));
        productList.add(new Product("嘻嘻嘻","11222222222",now,now ));
        productList.add(new Product("嘻嘻嘻","11222222222",now,now ));
        productList.add(new Product("嘻嘻嘻","11222222222",now,now ));
        productList.add(new Product("嘻嘻嘻","11222222222",now,now ));
        productList.add(new Product("嘻嘻嘻","11222222222",now,now ));
        mView.showProducts(productList);
    }

    @Override
    public void showSetAlarm(@NonNull Product product) {
        Preconditions.checkNotNull(product);
        mView.showSetAlarm(product);
    }
}
