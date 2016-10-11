package cn.st.android.femalestar.product.search;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.google.zxing.client.android.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import cn.st.android.femalestar.BaseFragment;
import cn.st.android.femalestar.R;
import cn.st.android.femalestar.data.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchProductFragment extends BaseFragment<SearchProductContract.Presenter>  implements SearchProductContract.View{

    private CoordinatorLayout mClayout;

    private EditText mSearchContent;

    private FloatingActionButton mAdd;

    private ListView mList;

    private ProductAdapter mProductAdapter;

    public static final int REQUEST_CAPTURE=1;

    private ProductItemListener productItemListener=new ProductItemListener() {
        @Override
        public void onSetAlarmClick(Product product) {
            getPresenter().showSetAlarm(product);
        }
    };

    public SearchProductFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductAdapter=new ProductAdapter(new ArrayList<Product>(0),productItemListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.search_product_frag, container, false);
        mClayout= (CoordinatorLayout) view.findViewById(R.id.cliner);
        mSearchContent= (EditText) view.findViewById(R.id.edit_search_content);
        mSearchContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return invokeScan(view,motionEvent);
            }
        });
        mAdd= (FloatingActionButton) view.findViewById(R.id.btn_add);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invokeAdd(view);
            }
        });
        mList= (ListView) view.findViewById(R.id.list_products);
        mList.setAdapter(mProductAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().loadProducts();
    }

    @Override
    public SearchProductContract.Presenter createPresenter() {
        return new SearchProductPresenter();
    }

    public boolean invokeScan(View view,MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(mSearchContent.getCompoundDrawables()[2]!=null){
                if(event.getX() >= (mSearchContent.getRight()- mSearchContent.getLeft() - mSearchContent.getCompoundDrawables()[2].getBounds().width())) {
                    getPresenter().showCapture();
                }
            }
        }
        return false;
    }

    public void invokeAdd(View view){
        getPresenter().showAdd();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getPresenter().result(requestCode, resultCode,data);
    }

    @Override
    public void showCapture() {
        Intent intent=new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent,REQUEST_CAPTURE);
    }

    @Override
    public void showCaptureResult(String result) {
        mSearchContent.setText(result);
        mSearchContent.setSelection(mSearchContent.length());
    }

    @Override
    public void showAdd() {
        Intent intent=new Intent(getActivity(),CaptureActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProducts(List<Product> productList) {
        mProductAdapter.replaceData(productList);
    }

    @Override
    public void showSetAlarm(Product product) {
        Snackbar.make(mClayout.getRootView(), product.getName(),Snackbar.LENGTH_SHORT).show();
    }

    class ProductAdapter extends BaseAdapter{
        private List<Product> mProductList;

        private ProductItemListener mProductItemListener;

        public ProductAdapter(List<Product> productList, ProductItemListener productItemListener){
            mProductList = Preconditions.checkNotNull(productList);
            mProductItemListener=productItemListener;
        }

        @Override
        public int getCount() {
            return mProductList.size();
        }

        @Override
        public Product getItem(int i) {
            return mProductList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView=view;
            if(view==null){
                LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
                rowView=inflater.inflate(R.layout.product_item,viewGroup,false);
            }
            final Product product =getItem(i);
            TextView mName = (TextView) rowView.findViewById(R.id.txt_name);
            TextView mCode = (TextView) rowView.findViewById(R.id.txt_code);
            TextView mPdate = (TextView) rowView.findViewById(R.id.txt_pdate);
            TextView mEdate = (TextView) rowView.findViewById(R.id.txt_edate);
            ImageView mAlarm= (ImageView) rowView.findViewById(R.id.img_alarm);
            mName.setText(product.getName());
            mCode.setText(product.getCode());
            mPdate.setText(product.getpDateStr());
            mEdate.setText(product.geteDateStr());
            mAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mProductItemListener.onSetAlarmClick(product);
                }
            });
            return rowView;
        }

        public void replaceData(List<Product> productList){
            this.mProductList = Preconditions.checkNotNull(productList);
            notifyDataSetChanged();
        }
    }

    interface ProductItemListener {

        void onSetAlarmClick(Product product);

    }
}
