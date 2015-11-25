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
package com.smassive.ballstreamsplayer.data.repository;

import com.smassive.ballstreamplayer.domain.bean.UserBo;
import com.smassive.ballstreamplayer.domain.repository.UserRepository;
import com.smassive.ballstreamsplayer.data.repository.datasource.CloudUserDataStore;
import com.smassive.ballstreamsplayer.data.repository.datasource.DbUserDataStore;
import com.smassive.ballstreamsplayer.data.repository.datasource.UserDataStoreFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton
public class UserRepositoryImpl implements UserRepository {

    private UserDataStoreFactory userDataStoreFactory;

    @Inject
    public UserRepositoryImpl(UserDataStoreFactory userDataStoreFactory) {
        this.userDataStoreFactory = userDataStoreFactory;
    }

    /**
     * Get an {@link Observable} which will emit a {@link UserBo}.
     *
     * @param username Name of the user to login.
     * @param password Password of the user to login.
     */
    @Override
    public Observable<UserBo> getUser(String username, String password) {
        DbUserDataStore localDataStore = userDataStoreFactory.createLocalDataStore();
        CloudUserDataStore remoteDataStore = userDataStoreFactory.createRemoteDataStore();

        return Observable.concat(localDataStore.getUser(username, password), remoteDataStore.getUser(username, password)).first();
    }
}
