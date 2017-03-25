package com.hero.zhaoq.mymvpdemo.presenter.tasks;

import android.os.Handler;

import com.hero.zhaoq.mymvpdemo.modle.UserBean;
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
     * @param bean  登陆数据对象的  封装类
     */
    @Override
    public void validateCredentAsync(final OnLoginFinishedListener listener, final UserBean bean) {
        //模拟 网络访问
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ((bean.getUsername().equals("zhaoqiang")) && (bean.getUserPassword().equals("zhaoqiang"))){
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
