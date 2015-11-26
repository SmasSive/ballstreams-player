package com.smassive.ballstreamsplayer.app.view.activity;

import com.smassive.ballstreamsplayer.app.R;
import com.smassive.ballstreamsplayer.app.internal.di.HasComponent;
import com.smassive.ballstreamsplayer.app.internal.di.component.DaggerUserComponent;
import com.smassive.ballstreamsplayer.app.internal.di.component.UserComponent;
import com.smassive.ballstreamsplayer.app.presenter.UserPresenter;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class LoginActivity extends BaseActivity implements HasComponent<UserComponent> {

    @Bind(R.id.username)
    EditText username;

    @Bind(R.id.password)
    EditText password;

    @Bind(R.id.rememberme)
    CheckBox rememberme;

    @Bind(R.id.signin)
    Button signin;

    @Bind(R.id.messageText)
    TextView messageText;

    @Bind(R.id.loginProgressBar)
    MaterialProgressBar loginProgressBar;

    @Inject
    UserPresenter userPresenter;

    private UserComponent userComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        initializeInjector();

        getComponent().inject(this);

        userPresenter.setView(this);
    }

    private void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder().applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule()).build();
    }

    @OnClick(R.id.signin)
    public void signin() {
        hideMessage();
        showLoading();
        userPresenter.getUser(username.getText().toString(), password.getText().toString());
    }

    public void showLoading() {
        loginProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        loginProgressBar.setVisibility(View.GONE);
    }

    public void showErrorMessage(String error) {
        messageText.setText(error);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            messageText.setTextAppearance(R.style.text_message_ko);
        } else {
            messageText.setTextAppearance(this, R.style.text_message_ko);
        }
        messageText.setVisibility(View.VISIBLE);
    }

    public void showMessageOk() {
        messageText.setText(R.string.message_user_ok);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            messageText.setTextAppearance(R.style.text_message_ok);
        } else {
            messageText.setTextAppearance(this, R.style.text_message_ok);
        }
        messageText.setVisibility(View.VISIBLE);
    }

    public void hideMessage() {
        messageText.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userPresenter.destroy();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }
}
