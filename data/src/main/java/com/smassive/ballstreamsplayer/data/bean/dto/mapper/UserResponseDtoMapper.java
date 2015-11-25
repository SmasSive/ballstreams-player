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
package com.smassive.ballstreamsplayer.data.bean.dto.mapper;

import com.smassive.ballstreamplayer.domain.bean.UserBo;
import com.smassive.ballstreamsplayer.data.bean.dto.UserResponseDto;
import com.smassive.ballstreamsplayer.data.bean.vo.UserVo;

public final class UserResponseDtoMapper {

    private UserResponseDtoMapper() {
    }

    public static UserBo toBo(UserResponseDto dto) {
        UserBo bo = null;

        if (dto != null) {
            bo = new UserBo();

            bo.setFavteam(dto.getFavteam());
            bo.setMembership(dto.getMembership());
            bo.setStatus(dto.getStatus());
            bo.setUsername(dto.getUsername());
        }

        return bo;
    }

    public static UserVo toVo(UserResponseDto dto) {
        UserVo vo = null;

        if (dto != null) {
            vo = new UserVo();

            vo.setFavteam(dto.getFavteam());
            vo.setMembership(dto.getMembership());
            vo.setStatus(dto.getStatus());
            vo.setUsername(dto.getUsername());
            vo.setUid(dto.getUid());
            vo.setToken(dto.getToken());
        }

        return vo;
    }
}
