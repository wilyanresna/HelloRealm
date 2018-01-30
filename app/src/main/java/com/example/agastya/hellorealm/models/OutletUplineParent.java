package com.example.agastya.hellorealm.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by AgastyA on 1/26/2018.
 */

public class OutletUplineParent extends RealmObject {
    @SuppressWarnings("unused")
    private RealmList<OutletUpline> uplines;

    public RealmList<OutletUpline> getUplines() {
        return uplines;
    }
}
