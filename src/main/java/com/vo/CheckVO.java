package com.vo;

import com.annotation.UserFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.validator.UserConstrain;
import org.javamoney.moneta.Money;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @description: 用来对SpringMvc 数据绑定时候的测试实体类
 * @create: 2019-08-13 12:02
 * @author: zxw
 **/
public class CheckVO {

    @NotNull(message = "CheckVO name 不能为空")
    private String name;
    @Min(0)
    private int age;
    private Money money;

    @UserFormat
    @UserConstrain(message = "用户的名字不能为name")
    private User user;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CheckVO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}
