package cn.st.android.femalestar.menstruation.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.st.android.femalestar.BaseFragment;
import cn.st.android.femalestar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchMenstruationFragment extends BaseFragment<SearchMenstruationContract.Presenter> implements SearchMenstruationContract.View{

    public SearchMenstruationFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.search_menstruation_frag, container, false);
    }


    @Override
    public SearchMenstruationContract.Presenter createPresenter() {
        return new SearchMenstruationPresenter();
    }
}
