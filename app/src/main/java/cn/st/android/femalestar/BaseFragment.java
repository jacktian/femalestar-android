package cn.st.android.femalestar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by coolearth on 16-10-10.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P presenter;

    public abstract P createPresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=createPresenter();
        presenter.setView(this);
    }

    public P getPresenter(){
        return presenter;
    }
}
