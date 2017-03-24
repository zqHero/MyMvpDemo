package com.hero.zhaoq.mymvpdemo.presenter.interfas;

/**
 * Package_name:com.hero.zhaoq.mymvpdemo.presenter
 * Author:zhaoqiang
 * Email:zhaoq_hero@163.com
 * Date:2017/03/24   19/16
 *
 * 异步任务的  处理接口
 */
public interface IAsyncLoginTask {
    //回调 方法
    void  validateCredentAsync(OnLoginFinishedListener listener,
                               String username, String password);
}
