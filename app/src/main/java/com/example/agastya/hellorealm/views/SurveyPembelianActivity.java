package com.example.agastya.hellorealm.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.agastya.hellorealm.R;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SurveyPembelianActivity extends AppCompatActivity {

    @BindView(R.id.activity_survey_pembelian_edittext_frek_belanja)
    EditText editTextFrekBelanja;
    @BindView(R.id.activity_survey_pembelian_edittext_jumlah_downline)
    EditText editTextJumlahBelanja;
    @BindView(R.id.activity_survey_pembelian_edittext_total_face_up)
    EditText editTextTotalFaceUp;

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_pembelian);
        ButterKnife.bind(this);

        /**
         * Initialize Stetho
         */
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        /**
         * Initialize Realm
         */
        mRealm= Realm.getDefaultInstance();
    }
}
