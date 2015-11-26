/**
 * Copyright (C) 2015 Sergi Castillo Open Source Project
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
package com.smassive.ballstreamsplayer.data.repository.datasource;

import com.smassive.ballstreamplayer.domain.bean.UserBo;
import com.smassive.ballstreamsplayer.data.BuildConfig;
import com.smassive.ballstreamsplayer.data.bean.dto.UserResponseDto;
import com.smassive.ballstreamsplayer.data.bean.dto.mapper.UserResponseDtoMapper;
import com.smassive.ballstreamsplayer.data.bean.vo.UserVo;
import com.smassive.ballstreamsplayer.data.net.ApiConstants;
import com.smassive.ballstreamsplayer.data.net.ApiService;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import android.content.Context;

import io.realm.Realm;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Action1;

public class CloudUserDataStore implements UserDataStore {

    private ApiService apiService;

    private Context context;

    private final Action1<UserResponseDto> saveToDb = userResponseDto -> {
        UserVo userVo = UserResponseDtoMapper.toVo(userResponseDto);

        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        realm.copyToRealm(userVo);
        realm.commitTransaction();
        realm.close();
    };

    public CloudUserDataStore(Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient();
        // add logging as last interceptor
        httpClient.interceptors().add(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        this.context = context;
    }

    /**
     * Get an {@link Observable} which will emit a {@link UserBo}.
     *
     * @param username Name of the user to login.
     * @param password Password of the user to login.
     */
    @Override
    public Observable<UserBo> getUser(String username, String password) {
        return apiService.login(username, password, BuildConfig.API_KEY).doOnNext(saveToDb).map(UserResponseDtoMapper::toBo);
    }
}
