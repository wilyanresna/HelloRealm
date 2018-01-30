package com.example.agastya.hellorealm.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.agastya.hellorealm.R;
import com.example.agastya.hellorealm.models.OutletUpline;

import butterknife.BindView;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ResultOutletUplineActivity extends BaseActivity {
    private static final String TAG = "wil";

    @BindView(R.id.activity_result_outlet_upline_loading)
    LinearLayout linearLayoutLoading;
    @BindView(R.id.activity_result_outlet_upline_loading_progressbar)
    ProgressBar progressBarLoading;
    @BindView(R.id.activity_result_outlet_upline_loading_textview)
    TextView textViewLoading;
    @BindView(R.id.activity_result_outlet_upline_listview)
    ListView listView;

    private Realm mRealm;
    private ResultOutletUplineAdapter mAdapter;
    private OutletUpline mOutletUpline;
    private String mNickname;
    private RealmResults<OutletUpline> mUplines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_outlet_upline);
        mOutletUpline = getIntent().getParcelableExtra("outletUpline");
        mNickname = getIntent().getStringExtra("nickname");
        mRealm = Realm.getDefaultInstance();

        mUplines = mRealm.where(OutletUpline.class)
                .contains("outletName", mOutletUpline.getOutletName(), Case.INSENSITIVE)
                .contains("outletCode", mOutletUpline.getOutletCode(), Case.INSENSITIVE)
                .contains("address", mOutletUpline.getAddress(), Case.INSENSITIVE)
                .contains("district", mOutletUpline.getDistrict(), Case.INSENSITIVE)
                .contains("village", mOutletUpline.getVillage(), Case.INSENSITIVE)
                .contains("nicknames.nickname", mNickname, Case.INSENSITIVE)
                .findAll().sort("outletName");
        mAdapter = new ResultOutletUplineAdapter(ResultOutletUplineActivity.this, mUplines);
        listView.setAdapter(mAdapter);

        /*mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

            }
        });*/

        /*mRealm.executeTransactionAsync(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                    }
                }
                , new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        linearLayoutLoading.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                    }
                }
                , new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        progressBarLoading.setVisibility(View.GONE);
                        textViewLoading.setVisibility(View.VISIBLE);
                        textViewLoading.setText(error.getMessage());
                        Log.d(TAG, "onError: " + error.getMessage());
                    }
                }
        );*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
