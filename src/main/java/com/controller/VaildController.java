package com.controller;

import com.validator.CheckVOValidator;
import com.validator.Create;
import com.vo.CheckVO;
import com.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @create: 2019-08-13 12:00
 * @author: zxw
 **/

@Controller
public class VaildController {
    private final static Logger logger = LoggerFactory.getLogger(VaildController.class);

    @Resource
    private LocaleResolver localeResolver;

    /**
     * @Description: 对表单(或者是那种键值对的数据, 或者简单的实体类)进行验证 valid
     * 这里有一个说明就是@ModelAttribute 还有 @requestParam 是可以验证的
     * <p>
     * 但是 @RequestBody 是用Jackson 封装,代码中没有经过数据绑定流程和验证流程,所以验证的RequestBody无效
     * <p>
     * 要用表单封装实体可以这样
     * http://localhost:9999/check?
     * date=1996-11-11 11:11:11
     * &money=CNY211.111
     * &age=-1
     * &user.id=123
     * &user.name=name
     * @Date: 2019/8/13
     * @Param:
     * @return:
     * @Author: zxw
     */
    @ResponseBody
    @RequestMapping("/check")
    public Object check(@Validated({Create.class}) @ModelAttribute CheckVO checkVO, @Validated User user, BindingResult bindingResult) {

        logger.warn(checkVO.toString());
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError e : errors) {
                logger.warn("valid error = , {}:{}:{}", e.getObjectName(), e.getCode(), e.getDefaultMessage());
            }
        }
        return checkVO;
        // 还有一个说明就是 checkVO.User.name 的字段加了annotation也无法进行验证 只能验证最表层的属性
    }


    /**
     * @Description: 对json格式封装的实体类进行验证
     * @Date: 2019/8/13
     * @Author: zxw
     */
    @ResponseBody
    @RequestMapping("/check2")
    public Object check2(@RequestBody CheckVO checkVO) {
        logger.warn(checkVO.toString());

        DataBinder binder = new DataBinder(checkVO);
        CheckVOValidator validator = new CheckVOValidator();
        binder.setValidator(validator);
        binder.validate();
        BindingResult bindingResult = binder.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError e : errors) {
                logger.warn("{}:{}:{}", e.getObjectName(), e.getCode(), e.getDefaultMessage());
            }
        }
        return checkVO;
    }

    /**
     * 数据绑定时的初始化，
     *
     * @Date: 2019/8/13
     * @Param:
     * @return:
     * @Author: zxw
     */
    @InitBinder
    @Deprecated

    protected void initBinder(WebDataBinder binder) {
        System.out.println("I am initBinder " + (++i));
        Object target = binder.getTarget();
        if (target instanceof CheckVO) {
            binder.addValidators(new CheckVOValidator());
        }
//        binder.addValidators(new UserValidator());
    }

    int i = 0;
}
