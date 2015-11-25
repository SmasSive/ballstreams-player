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
package com.smassive.ballstreamplayer.domain.interactor;

import com.smassive.ballstreamplayer.domain.executor.PostExecutionThread;
import com.smassive.ballstreamplayer.domain.executor.ThreadExecutor;
import com.smassive.ballstreamplayer.domain.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class GetUserUseCase extends UseCase {

    private final UserRepository userRepository;

    private String username;

    private String password;

    @Inject
    public GetUserUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);

        this.userRepository = userRepository;
    }

    /**
     * Executes the current use case.
     *
     * @param username          Name of the user to login.
     * @param password          Password of the user to login.
     * @param UseCaseSubscriber The guy who will be listen to the observable build with {@link #buildUseCaseObservable()}.
     */
    public void execute(String username, String password, Subscriber UseCaseSubscriber) {
        this.username = username;
        this.password = password;

        super.execute(UseCaseSubscriber);
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.getUser(username, password);
    }
}
