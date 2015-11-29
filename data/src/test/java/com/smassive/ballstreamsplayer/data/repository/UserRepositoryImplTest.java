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
import com.smassive.ballstreamsplayer.data.repository.datasource.UserDataStore;
import com.smassive.ballstreamsplayer.data.repository.datasource.UserDataStoreFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserRepositoryImplTest {

    public static final String FAKE_USERNAME = "Michael Jordan";

    public static final String FAKE_PASSWORD = "Mj23";

    private UserRepositoryImpl userRepository;

    @Mock
    UserDataStoreFactory mockUserDataStoreFactory;

    @Mock
    UserDataStore mockUserDataStore;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userRepository = new UserRepositoryImpl(mockUserDataStoreFactory);
        given(mockUserDataStoreFactory.create(false)).willReturn(mockUserDataStore);
        given(mockUserDataStoreFactory.create(true)).willReturn(mockUserDataStore);
    }

    @Test
    public void testGetUser() throws Exception {
        UserBo userBo = new UserBo();
        given(mockUserDataStore.getUser(FAKE_USERNAME, FAKE_PASSWORD)).willReturn(Observable.just(userBo));
        userRepository.getUser(FAKE_USERNAME, FAKE_PASSWORD);

        verify(mockUserDataStore).getUser(FAKE_USERNAME, FAKE_PASSWORD);
    }
}
