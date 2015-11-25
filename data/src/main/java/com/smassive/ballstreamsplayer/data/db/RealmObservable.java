/**
 * Copyright 2015 Kirill Boyarshinov
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smassive.ballstreamsplayer.data.db;


import android.content.Context;


import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;


public final class RealmObservable {
    private RealmObservable() {
    }


    public static <T extends RealmObject> Observable<T> object(Context context, final Func1<Realm, T> function) {
        return Observable.create(new OnSubscribeRealm<T>(context) {
            @Override
            public T get(Realm realm) {
                return function.call(realm);
            }
        });
    }


    public static <T extends RealmObject> Observable<T> object(Context context, String fileName, final Func1<Realm, T> function) {
        return Observable.create(new OnSubscribeRealm<T>(context, fileName) {
            @Override
            public T get(Realm realm) {
                return function.call(realm);
            }
        });
    }


    public static <T extends RealmObject> Observable<RealmList<T>> list(Context context,
            final Func1<Realm, RealmList<T>> function) {
        return Observable.create(new OnSubscribeRealm<RealmList<T>>(context) {
            @Override
            public RealmList<T> get(Realm realm) {
                return function.call(realm);
            }
        });
    }


    public static <T extends RealmObject> Observable<RealmList<T>> list(Context context, String fileName,
            final Func1<Realm, RealmList<T>> function) {
        return Observable.create(new OnSubscribeRealm<RealmList<T>>(context, fileName) {
            @Override
            public RealmList<T> get(Realm realm) {
                return function.call(realm);
            }
        });
    }


    public static <T extends RealmObject> Observable<RealmResults<T>> results(Context context,
            final Func1<Realm, RealmResults<T>> function) {
        return Observable.create(new OnSubscribeRealm<RealmResults<T>>(context) {
            @Override
            public RealmResults<T> get(Realm realm) {
                return function.call(realm);
            }
        });
    }


    public static <T extends RealmObject> Observable<RealmResults<T>> results(Context context, String fileName,
            final Func1<Realm, RealmResults<T>> function) {
        return Observable.create(new OnSubscribeRealm<RealmResults<T>>(context, fileName) {
            @Override
            public RealmResults<T> get(Realm realm) {
                return function.call(realm);
            }
        });
    }
}

