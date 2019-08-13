package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.sleep;

/**
 * @description: 异步的Controller
 * @create: 2019-08-13 18:31
 * @author: zxw
 **/

@RestController
public class AysncController {


    /**
    * @Description: 同步请求 dispatcherHandler需要等待
    * @Date: 2019/8/13
    * @Param:
    * @return:
    * @Author: zxw
    *
    */
    @GetMapping("getSync")
    public String longTask() {

        Callable<String> callable;

        callable = () -> {
            sleep(5000);
            return "abc";
        };
        FutureTask<String> task = new FutureTask<>(callable);
        task.run();
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * @Description: 异步请求
     *
     * WebAsyncManager 利用taskExecutor 开启一个线程让来获取callable的值
     * 然后用asyncWebRequest 分发这个请求
    * @Date: 2019/8/13
    * @Param:
    * @return:
    * @Author: zxw
    *
    */
    @GetMapping("getAsync")
    public Callable<String> longTask2(){
        return () -> {
            // 长时间操作
            sleep(5000);
            return "abc";
        };
    }

}
