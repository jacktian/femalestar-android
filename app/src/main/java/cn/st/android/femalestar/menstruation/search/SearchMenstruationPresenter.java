package cn.st.android.femalestar.menstruation.search;

import com.google.common.base.Preconditions;

/**
 * Created by coolearth on 16-10-9.
 */

public class SearchMenstruationPresenter implements SearchMenstruationContract.Presenter {
    private SearchMenstruationContract.View mView;

    @Override
    public void setView(SearchMenstruationContract.View view) {
        mView= Preconditions.checkNotNull(view);

    }
}
