package com.example.agastya.hellorealm.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.agastya.hellorealm.R;
import com.example.agastya.hellorealm.utils.Utils;
import com.example.agastya.hellorealm.models.Nickname;
import com.example.agastya.hellorealm.models.OutletUpline;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class SearchOutletUplineActivity extends BaseActivity {
    @BindView(R.id.activity_search_outlet_upline_edittext_nama_outlet)
    EditText editTextNamaOutlet;
    @BindView(R.id.activity_search_outlet_upline_edittext_kode_outlet)
    EditText editTextKodeOutlet;
    @BindView(R.id.activity_search_outlet_upline_edittext_alamat)
    EditText editTextAlamat;
    @BindView(R.id.activity_search_outlet_upline_edittext_kecamatan)
    EditText editTextKecamatan;
    @BindView(R.id.activity_search_outlet_upline_edittext_kelurahan)
    EditText editTextKelurahan;
    @BindView(R.id.activity_search_outlet_upline_edittext_nickname)
    EditText editTextNickname;
    @BindView(R.id.activity_search_outlet_upline_button_search)
    Button buttonSearch;
    @BindView(R.id.activity_search_outlet_upline_button_add_data)
    Button buttonAddDataDummy;
    @BindView(R.id.activity_search_outlet_upline_button_delete_data)
    Button buttonDeleteDataDummy;

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_outlet_upline);
        mRealm= Realm.getDefaultInstance();

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutletUpline upline = new OutletUpline();
                upline.setOutletName(editTextNamaOutlet.getText().toString());
                upline.setOutletCode(editTextKodeOutlet.getText().toString());
                upline.setAddress(editTextAlamat.getText().toString());
                upline.setDistrict(editTextKecamatan.getText().toString());
                upline.setVillage(editTextKelurahan.getText().toString());

                Intent intent = new Intent(SearchOutletUplineActivity.this, ResultOutletUplineActivity.class);
                intent.putExtra("outletUpline", upline);
                intent.putExtra("nickname", editTextNickname.getText().toString());
                startActivity(intent);
            }
        });

        buttonAddDataDummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDataDummy();
            }
        });

        buttonDeleteDataDummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDataDummy();
            }
        });
    }

    public void addDataDummy() {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // insert outlet upline
                List<String> name = new ArrayList<>();
                name.add("Berkah");
                name.add("Barokah");
                name.add("Anjay");
                name.add("Realm");
                name.add("Android");
                name.add("Nu Aing");
                name.add("No 7");

                List<String> address = new ArrayList<>();
                address.add("Jalan Saturnus");
                address.add("Uranus No.1");
                address.add("Di rumah");
                address.add("Nggak dimana-mana");
                address.add("Disini");
                address.add("Udah digusur");
                address.add("Masih Dibangun");

                List<String> village = new ArrayList<>();
                village.add("Pelanet");
                village.add("Konoha");
                village.add("Gotham");
                village.add("Jatinangor");
                village.add("Disini");
                village.add("Sunter");
                village.add("Cempaka");

                List<String> district = new ArrayList<>();
                district.add("Kec Pelanet");
                district.add("Kec Konoha");
                district.add("Kec Gotham");
                district.add("Kec Jatinangor");
                district.add("Kec Disini");
                district.add("Kec Sunter");
                district.add("Kec Cempaka");

                List<String> channel = new ArrayList<>();
                channel.add("Trade");
                channel.add("Transaksi");
                channel.add("Store DC");

                OutletUpline upline;
                Nickname nickname;

                for (int j = 0; j < 70000; j++) {
                    String named = name.get((int) (Math.random() * 7)) + " " + j;

                    // insert nickname
                    List<String> nicknames = new ArrayList<>();
                    nicknames.add("Bray");
                    nicknames.add("Cuy");
                    nicknames.add("Boy");
                    nicknames.add("Girl");
                    nicknames.add("Gaes");
                    int x = (int) (Math.random() * 14);

                    // insert outlet
                    upline = realm.createObject(OutletUpline.class, UUID.randomUUID().toString());
                    upline.setOuCode("JKT");
                    upline.setTerritoryCode("01");
                    upline.setDistrictCode("002");
                    upline.setRoute(93);
                    upline.setOutletId("INIOutlet" + j);
                    upline.setOutletCode("CodeOutlet" + j);
                    upline.setOutletName(named);
                    upline.setAddress(address.get((int) (Math.random() * 7)) + " " + j);
                    upline.setVillage(village.get((int) (Math.random() * 7)) + " " + j);
                    upline.setDistrict(district.get((int) (Math.random() * 7)) + " " + j);
                    upline.setSubChannel(channel.get((int) (Math.random() * 3)));
                    for (int i = 0; i < x; i++) {
                        nickname = realm.createObject(Nickname.class, UUID.randomUUID().toString());
                        nickname.setOutletId("INIOutlet" + j);
                        nickname.setNickName(nicknames.get((int) (Math.random() * 5)) + named);
                        upline.getNicknames().add(nickname);
                    }
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Utils.showToast(SearchOutletUplineActivity.this, "Add Data Dummy Success!");
                buttonAddDataDummy.setText("DONE");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Utils.showToast(SearchOutletUplineActivity.this, "Add Data Dummy Fail!");
                Log.d("wily", "onError: " + error.getMessage());
            }
        });
    }

    public void deleteDataDummy() {
        RealmResults<OutletUpline> result1 = mRealm.where(OutletUpline.class).findAll();
        RealmResults<Nickname> result2 = mRealm.where(Nickname.class).findAll();

        mRealm.beginTransaction();

        result1.deleteAllFromRealm();
        result2.deleteAllFromRealm();

        mRealm.commitTransaction();
    }
}
