csdn:


随着Android多年的发展和积累，其开发的架构模式也逐渐发展的多种多样。最近研究了下安卓开发的MVP模式。本文做下总结。和简单介绍。以及学习中demo。


##基础：

MVC: Model-view-controller
MVP: Model-view-presenter

View层: 视图层，包含界面相关的功能。如各种：Activity，Fragment，View.

Presenter层：逻辑控制层，充当中间人角色。用来隔离View层和Model层。

Model层：封装各种数据源信息。实体类。

Controller: 逻辑业务层。

##一，简单介绍：

###1，概念：
####1.1传统的mvc模式：
mvc是一种程序开发设计模式,它实现了显示模块与功能模块的分离。提高了程序的可维护性、可移植性、可扩展性与可重用性，降低了程序的开发难度。它主要分模型、视图、控制器三层。

Mvc中：Model层和View层是直接相互通信的，而且Controller是基于行为的，可以被多个View共享。

其中MVC关系如图:

![这里写图片描述](http://img.blog.csdn.net/20170325151120852?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMzIzMzA5Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

####1.2Mvp模式：
mvp是经典的mvc的延伸和改进，

Mvp中View层和Model并没有直接通信，而是通过中间人Presenter来间接通信的。**Presenter和View以及model交互都是通过接口进行的。**  通常View和Presenter是一对的，当然，复杂的View可能需要多个Presenter来共同处理，这些需要根据具体的业务需求而定。

关系图：
![这里写图片描述](http://img.blog.csdn.net/20170325152044403?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMzIzMzA5Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

##2.LoginDemo:

1，demo逻辑：
![这里写图片描述](http://img.blog.csdn.net/20170325152758678?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMzIzMzA5Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)



2，
demo中：Presenter  持有modle 的引用：
view 持有 Presenter的  引用。    view
和modle   通过Presenter交互。

LoginActiivty.java:

```
/**
 * 登录界面   view
 * 实现  ILoginView Presenter 和 LoginActivity 通过接口交互。  并
 */
public class LoginActivity extends BaseActivity implements ILoginView{

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

```

LoginPresenter.java:

数据控制器：

```
/**
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

```

本例子  只是实现了  简单的  登录界面，并没有真正实现网络数据访问。对于modle的封装也比较简化。需要看源码的同学请移步github。

https://github.com/229457269/MyMvpDemo

若想看完整版的mvpdemo：请移步下面：
完整版的实例：使用retrofit+butterknife+mvp :  modle 也进行了完整封装：
https://github.com/229457269/Mvp-demo2


参考：Android-高级进阶，顾浩鑫  一书（电子工业出版社）
感谢：https://github.com/jpotts18    android-mvp 代码。

//====================================================================
# Android MVP

This repository demonstrates the [Model View Presenter](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) architecture and was inspired by [Antonio Leiva's Android MVP](https://github.com/antoniolg/androidmvp). You can find an example with updated libraries in the [master-2](https://github.com/jpotts18/android-mvp/tree/master-2) branch from [RajuSe](https://github.com/RajuSE).

## Videos

[Android MVP Playlist - Youtube](https://www.youtube.com/playlist?list=PLfbTKxZYb1mhQQaajZw0OntPcioSPdfKM)

## Getting Started

1. Clone the Repo - ``git clone git@github.com:jpotts18/android-mvp.git``
1. Look at all of the branches - ``git branch -a``
2. **Read about the branches**
1. Don't forget to checkout the `master-2` it has updated libraries like RxJava, Butterknife, etc.

This repository contains a **chain of branches** that shows the logical progression of the Android MVP.

* ``git checkout 1-login-view`` - demonstates a simple View which is a LoginActivity
* ``git checkout 2-synchronous-login-mvp`` - demonstrates Login MVP implementation with a synchronous Model (or interactor).
* ``git checkout 3-async-login-mvp`` - demonstrates Login MVP with an asynchronous Model which does not change any code in the LoginActivity
* ``git checkout 4-list-activity-view`` - demonstates a more complex View (RepoListActivity) and an Asynchronous Networked Model which shows a users Github repositories sorted by stars.
* ``git checkout 5-list-fragment`` - substitutes the RepoListActivity for a Fragment without changing any code in the Model.
* The ``master`` branch contains the completed project.

## What is MVP?

[Model-View-Presenter](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) is a user interface architectural pattern engineered to facilitate automated unit testing and improve the separation of concerns in presentation logic:

* The **Model** is an interface defining the data to be displayed or otherwise acted upon in the user interface.
* The **View** is a passive interface that displays data (the Model) and routes user commands (events) to the presenter to act upon that data.
* The **Presenter** acts upon the model and the view. It retrieves data from repositories (the model), and formats it for display in the view.


## Benefits

* **Loose Coupling** - The Presenter is an intermediary between the View code and the Model. This allows the View and the Model to evolve independently of each other.
* **[Separation of Concerns](http://en.wikipedia.org/wiki/Separation_of_concerns)** - Individual sections can be reused, as well as developed and updated independently.
* **More Testable** – By isolating each major component (UI, Presenter, and Model) it is easier to write unit tests. This is especially true when using the MVP pattern which only interacts with the view using an interface.
* **Code Reuse** – By using a separation of concerns/responsible design approach you will increase code reuse.
* **Flexibility** - By isolating most of your code into the Presenter and Model components your code base is more flexible to change in the View.

## Key differences between MVC and MVP

MVP Pattern
* View is more loosely coupled to the model. The presenter is responsible for binding the model to the view.
* Easier to unit test because interaction with the view is through an interface
* Usually view to presenter map one to one. Complex views may have multi presenters.

MVC Pattern
* Controller are based on behaviors and can be shared across views
* Can be responsible for determining which view to display (Front Controller Pattern)

#### References
[MVC or MVP Pattern – Whats the difference?](http://www.infragistics.com/community/blogs/todd_snyder/archive/2007/10/17/mvc-or-mvp-pattern-whats-the-difference.aspx)

[Model-View-Presenter - Wikipedia](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter)

[Separation of Concerns - Wikipedia](http://en.wikipedia.org/wiki/Separation_of_concerns)
