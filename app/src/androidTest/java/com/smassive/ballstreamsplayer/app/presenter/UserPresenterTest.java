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
package com.smassive.ballstreamsplayer.app.presenter;

import com.smassive.ballstreamplayer.domain.interactor.GetUserUseCase;
import com.smassive.ballstreamsplayer.app.view.activity.LoginActivity;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import android.content.Context;
import android.test.AndroidTestCase;

import rx.Subscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class UserPresenterTest extends AndroidTestCase {

    private static final String FAKE_USERNAME = "Michael Jordan";

    private static final String FAKE_PASSWORD = "Mj23";

    private UserPresenter userPresenter;

    @Mock
    private Context mockContext;

    @Mock
    private LoginActivity mockLoginActivity;

    @Mock
    private GetUserUseCase mockGetUserUseCase;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        //MockitoAnnotations.initMocks(this);
        // http://stackoverflow.com/questions/30590470/npe-when-calling-mockitoannotations-initmocks-in-androidtestcase
        System.setProperty("dexmaker.dexcache", getContext().getCacheDir().getPath());

        userPresenter = new UserPresenter(mockGetUserUseCase);
        userPresenter.setView(mockLoginActivity);
    }

    public void testUserPresenter() {
        given(mockLoginActivity.getBaseContext()).willReturn(mockContext);

        userPresenter.getUser(FAKE_USERNAME, FAKE_PASSWORD);

        verify(mockLoginActivity).hideMessage();
        verify(mockLoginActivity).showLoading();
        verify(mockGetUserUseCase).execute(FAKE_USERNAME, FAKE_PASSWORD, any(Subscriber.class));
    }
}
