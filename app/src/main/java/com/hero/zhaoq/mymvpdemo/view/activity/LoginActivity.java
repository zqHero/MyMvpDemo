package com.hero.zhaoq.mymvpdemo.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.EditText;
import android.widget.Toast;

import com.hero.zhaoq.mymvpdemo.MainActivity;
import com.hero.zhaoq.mymvpdemo.R;
import com.hero.zhaoq.mymvpdemo.presenter.LoginPresenter;
import com.hero.zhaoq.mymvpdemo.view.interfas.ILoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录界面   view
 * 实现  ILoginView Presenter 和 LoginActivity 通过接口交互。  并回调数据。
 */
public class LoginActivity extends BaseActivity implements ILoginView{

    @Bind(R.id.account)
    TextInputEditText account;

    @Bind(R.id.pwd)
    TextInputEditText pwd;

    private ProgressDialog preDialog;

    // 初始化   登录数据控制器
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        account.setText("zhaoqiang");
        pwd.setText("zhaoqiang");
        //初始化  控制器
        loginPresenter = new LoginPresenter(this);
    }

    @OnClick(R.id.submit_btn)
    public void submitBtnclick(){
        String acc = account.getText().toString().trim();
        String passWd = pwd.getText().toString().trim();
        preDialog = ProgressDialog.show(this,"正在登录...",null);
        //TODO  创建进度框：
        loginPresenter.iLoginPrToLogin(acc,passWd);
    }

    /**
     * 该方法  由  ILoginview 回调
     */
    @Override
    public void iLoginViewSuccess() {
        preDialog.dismiss();
        preDialog = null;
        //登录  成功  处理ui数据
        Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * 该方法  由   ILoginview  回调
     */
    @Override
    public void iLoginViewFailed() {
        //登录 失败 处理UI数据
        preDialog.dismiss();
        preDialog = null;

        Toast.makeText(this,"登录失败,请验证账号密码",Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.cancel_btn)
    public void cancelBtnclick(){
        finish();
    }
}
