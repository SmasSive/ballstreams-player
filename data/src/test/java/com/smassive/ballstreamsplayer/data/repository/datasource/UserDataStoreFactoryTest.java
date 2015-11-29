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

import com.smassive.ballstreamsplayer.data.ApplicationTestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDataStoreFactoryTest extends ApplicationTestCase {

    private UserDataStoreFactory userDataStoreFactory;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userDataStoreFactory = new UserDataStoreFactory(RuntimeEnvironment.application);
    }

    @Test
    public void testCreateDbDataStore() throws Exception {
        UserDataStore userDataStore = userDataStoreFactory.create(false);
        DbUserDataStore dbUserDataStore = userDataStoreFactory.createLocalDataStore();

        assertThat(userDataStore, is(notNullValue()));
        assertThat(userDataStore, is(instanceOf(DbUserDataStore.class)));
        assertThat(dbUserDataStore, is(notNullValue()));
    }

    @Test
    public void testCreateCloudDataStore() throws Exception {
        UserDataStore userDataStore = userDataStoreFactory.create(true);
        CloudUserDataStore cloudUserDataStore = userDataStoreFactory.createRemoteDataStore();

        assertThat(userDataStore, is(notNullValue()));
        assertThat(userDataStore, is(instanceOf(CloudUserDataStore.class)));
        assertThat(cloudUserDataStore, is(notNullValue()));
    }
}
