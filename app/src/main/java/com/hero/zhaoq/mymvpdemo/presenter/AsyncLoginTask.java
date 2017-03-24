package com.hero.zhaoq.mymvpdemo.presenter;

import android.os.Handler;

import com.hero.zhaoq.mymvpdemo.presenter.interfas.IAsyncLoginTask;
import com.hero.zhaoq.mymvpdemo.presenter.interfas.OnLoginFinishedListener;

/**
 * Package_name:com.hero.zhaoq.mymvpdemo.presenter
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/03/24   19/15
 */
public class AsyncLoginTask implements IAsyncLoginTask {

    /**
     * 异步任务  处理  登录数据信息
     * @param listener  回调处理类
     * @param username  用户名
     * @param password  密码
     */
    @Override
    public void validateCredentAsync(final OnLoginFinishedListener listener, final String username, final String password) {
        //模拟 网络访问
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ((username.equals("zhaoqiang")) && (password.equals("zhaoqiang"))){
                    //登录成功
                    listener.onLoginFishedLisSuccess();
                }else{
                    //登录失败
                    listener.onLoginFishedLisError();
                }
            }
        },3000);
    }
}
