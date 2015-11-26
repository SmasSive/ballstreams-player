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

import com.smassive.ballstreamplayer.domain.bean.UserBo;
import com.smassive.ballstreamplayer.domain.exception.DefaultErrorBundle;
import com.smassive.ballstreamplayer.domain.exception.ErrorBundle;
import com.smassive.ballstreamplayer.domain.interactor.DefaultSubscriber;
import com.smassive.ballstreamplayer.domain.interactor.GetUserUseCase;
import com.smassive.ballstreamplayer.domain.interactor.UseCase;
import com.smassive.ballstreamsplayer.app.exception.ErrorMessageFactory;
import com.smassive.ballstreamsplayer.app.internal.di.PerActivity;
import com.smassive.ballstreamsplayer.app.view.activity.LoginActivity;
import com.smassive.ballstreamsplayer.data.exception.InvalidUserException;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class UserPresenter implements Presenter {

    private LoginActivity activity;

    private final UseCase getUserUseCase;

    @Inject
    public UserPresenter(@Named("getUserUseCase") UseCase getUserUseCase) {
        this.getUserUseCase = getUserUseCase;
    }

    public void setView(@NonNull LoginActivity activity) {
        this.activity = activity;
    }

    public void getUser(String username, String password) {
        if (getUserUseCase instanceof GetUserUseCase) {
            ((GetUserUseCase) getUserUseCase).execute(username, password, new GetUserSubscriber());
        }
        hideMessage();
        showLoading();
    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    @Override
    public void resume() {

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    @Override
    public void pause() {

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    @Override
    public void destroy() {
        getUserUseCase.unsubscribe();
    }

    private void showLoading() {
        activity.showLoading();
    }

    private void hideLoading() {
        activity.hideLoading();
    }

    private void showMessageOk() {
        activity.showMessageOk();
    }

    private void showError(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(activity, errorBundle.getException());
        activity.showErrorMessage(errorMessage);
    }

    private void hideMessage() {
        activity.hideMessage();
    }

    private class GetUserSubscriber extends DefaultSubscriber<UserBo> {
        @Override
        public void onCompleted() {
            super.onCompleted();

            hideLoading();
            showMessageOk();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);

            hideLoading();
            showError(new DefaultErrorBundle(new InvalidUserException(e)));
        }

        @Override
        public void onNext(UserBo userBo) {
            super.onNext(userBo);
        }
    }
}
