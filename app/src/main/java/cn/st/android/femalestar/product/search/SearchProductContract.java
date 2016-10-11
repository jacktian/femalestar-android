package cn.st.android.femalestar.product.search;

import android.content.Intent;

import java.util.List;

import cn.st.android.femalestar.BasePresenter;
import cn.st.android.femalestar.BaseView;
import cn.st.android.femalestar.data.Product;

/**
 * Created by coolearth on 16-10-10.
 */

public interface SearchProductContract {
    interface View extends BaseView{
        void showCapture();

        void showCaptureResult(String result);

        void showAdd();

        void showProducts(List<Product> productList);

        void showSetAlarm(Product product);
    }

    interface Presenter extends BasePresenter<View>{
        void showCapture();
        void result(int requestCode, int resultCode, Intent data);

        void showAdd();

        void loadProducts();

        void showSetAlarm(Product product);
    }
}
