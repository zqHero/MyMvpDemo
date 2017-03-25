package com.hero.zhaoq.mymvpdemo.presenter;

import com.hero.zhaoq.mymvpdemo.modle.UserBean;
import com.hero.zhaoq.mymvpdemo.presenter.interfas.ILoginPresenter;
import com.hero.zhaoq.mymvpdemo.presenter.interfas.OnLoginFinishedListener;
import com.hero.zhaoq.mymvpdemo.presenter.tasks.AsyncLoginTask;
import com.hero.zhaoq.mymvpdemo.view.interfas.ILoginView;

/**
 * Package_name:com.hero.zhaoq.mymvpdemo.presenter
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/03/24   18/56
 *
 * 登录的  数据控制器  实现登录数据控制器接口
 * 实现逻辑业务
 *
 * 去登陆
 * 登录成功    失败回调   实现数据回调   并将回调数据返回  View
 */
public class LoginPresenter implements ILoginPresenter,OnLoginFinishedListener {

    //present 持有  view 和 modle 的 引用   modle 和View  通过p  交互
    private ILoginView view;
    private AsyncLoginTask task;  //处理  异步任务

    /**
     * 将  当前的需要处理的View 传进来   这边用于回调处理数据
     * @param loginView  需要处理的View    实现 ILoginView 接口
     */
    public LoginPresenter(ILoginView loginView) {
        this.view = loginView;
        this.task = new AsyncLoginTask(); //创建  异步任务类
    }

    /**
     * 去登陆  传入 账号密码   将当前类作为 回调类
     * @param username 账号
     * @param password 密码
     */
    @Override
    public void iLoginPrToLogin(String username, String password) {
        //执行 异步任务：  请求数据   将this  作为监听器
        task.validateCredentAsync(this,new UserBean(username,password));
    }

    @Override
    public void onLoginFishedLisSuccess() {
        view.iLoginViewSuccess();
    }

    @Override
    public void onLoginFishedLisError() {
        view.iLoginViewFailed();
    }

}
