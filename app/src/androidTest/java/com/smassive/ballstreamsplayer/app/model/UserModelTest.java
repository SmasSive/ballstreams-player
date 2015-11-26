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
package com.smassive.ballstreamsplayer.app.model;

import com.smassive.ballstreamplayer.domain.bean.UserBo;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserModelTest {

    private static final String FAKE_USERNAME = "fakeUsername";

    public static final String FAKE_FAVTEAM = "Chicago Bulls";

    public static final String FAKE_MEMBERSHIP = "Premium";

    private UserModel userModel;

    @Before
    public void setUp() {
        userModel = new UserModel();
        userModel.setUsername(FAKE_USERNAME);
        userModel.setFavteam(FAKE_FAVTEAM);
        userModel.setMembership(FAKE_MEMBERSHIP);
    }

    @Test
    public void testUserOk() {
        String username = userModel.getUsername();
        String favteam = userModel.getFavteam();
        String membership = userModel.getMembership();

        assertThat(username, is(FAKE_USERNAME));
        MatcherAssert.assertThat(favteam, is(FAKE_FAVTEAM));
        MatcherAssert.assertThat(membership, is(FAKE_MEMBERSHIP));
    }
}
