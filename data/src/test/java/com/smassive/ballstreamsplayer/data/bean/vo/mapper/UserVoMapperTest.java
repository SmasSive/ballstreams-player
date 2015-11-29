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
package com.smassive.ballstreamsplayer.data.bean.vo.mapper;

import com.smassive.ballstreamplayer.domain.bean.UserBo;
import com.smassive.ballstreamsplayer.data.ApplicationTestCase;
import com.smassive.ballstreamsplayer.data.bean.vo.UserVo;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserVoMapperTest extends ApplicationTestCase {

    public static final String FAKE_USERNAME = "Michael Jordan";

    public static final String FAKE_FAVTEAM = "Chicago Bulls";

    public static final String FAKE_MEMBERSHIP = "Premium";

    @Test
    public void testToBo() throws Exception {
        UserVo userVo = new UserVo();
        userVo.setUsername(FAKE_USERNAME);
        userVo.setFavteam(FAKE_FAVTEAM);
        userVo.setMembership(FAKE_MEMBERSHIP);

        UserBo userBo = UserVoMapper.toBo(userVo);

        assertThat(userBo, is(notNullValue()));
        assertThat(userBo, is(instanceOf(UserBo.class)));
        assertThat(userBo.getUsername(), is(FAKE_USERNAME));
        assertThat(userBo.getMembership(), is(FAKE_MEMBERSHIP));
        assertThat(userBo.getFavteam(), is(FAKE_FAVTEAM));
    }
}
