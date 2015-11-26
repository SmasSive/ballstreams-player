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
package com.smassive.ballstreamsplayer.app.model.mapper;

import com.smassive.ballstreamplayer.domain.bean.UserBo;
import com.smassive.ballstreamsplayer.app.model.UserModel;

import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModelMapperTest extends TestCase {

    public static final String FAKE_USERNAME = "fakeUsername";

    public static final String FAKE_FAVTEAM = "Chicago Bulls";

    public static final String FAKE_MEMBERSHIP = "Premium";

    public void testToBo() throws Exception {
        UserBo userBo = new UserBo();
        userBo.setUsername(FAKE_USERNAME);
        userBo.setFavteam(FAKE_FAVTEAM);
        userBo.setMembership(FAKE_MEMBERSHIP);

        UserModel userModel = UserModelMapper.toBo(userBo);

        assertThat(userModel, is(instanceOf(UserModel.class)));
        assertThat(userModel.getUsername(), is(FAKE_USERNAME));
        assertThat(userModel.getFavteam(), is(FAKE_FAVTEAM));
        assertThat(userModel.getMembership(), is(FAKE_MEMBERSHIP));
    }
}
