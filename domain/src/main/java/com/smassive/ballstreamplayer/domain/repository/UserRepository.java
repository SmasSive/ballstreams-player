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
package com.smassive.ballstreamplayer.domain.repository;

import com.smassive.ballstreamplayer.domain.bean.UserBo;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link UserBo} related data.
 */
public interface UserRepository {

    /**
     * Get an {@link rx.Observable} which will emit a {@link UserBo}.
     *
     * @param username Name of the user to login.
     * @param password Password of the user to login.
     */
    Observable<UserBo> getUser(String username, String password);
}
