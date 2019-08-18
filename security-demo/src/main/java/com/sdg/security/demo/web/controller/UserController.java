package com.sdg.security.demo.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sdg.security.core.Enum.MessageEnum;
import com.sdg.security.core.exception.UserException;
import com.sdg.security.demo.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


    /**
     * 参数检验测试用例
     * @param user
     * @param errors
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加用户")
    public User addUser(@Valid User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> {
               throw new UserException(MessageEnum.USER_NO_EXIST);
            });
        }
        log.info(user.toString());
        return user;

    }


    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "测试查询用户，返回全User对象")
    public User getUserById(@ApiParam(value = "用户ID") @PathVariable BigInteger id, Pageable pageable){
        log.info("getUserById,id = {}",id);
        log.info("pageable = {}",pageable);
        User user = new User();
        user.setId(id);
        user.setUsername("ssssdddddgggg");
        return user;
    }@GetMapping("/1/{id:\\d+}")
    @JsonView(User.simpleJsonView.class)
    @ApiOperation(value = "获取用户缺省字段")
    public User getUserById1(@ApiParam("用户ID")@PathVariable BigInteger id, Pageable pageable){
        log.info("getUserById,id = {}",id);
        log.info("pageable = {}",pageable);
        User user = new User();
        user.setId(id);
        user.setUsername("ssssdddddgggg");
        return user;
    }
    @GetMapping("/2/{id:\\d+}")
    @JsonView(User.detailJsonView.class)
    public User getUserById2(@PathVariable BigInteger id, Pageable pageable){
        log.info("getUserById,id = {}",id);
        log.info("pageable = {}",pageable);
        User user = new User();
        user.setId(id);
        user.setUsername("ssssdddddgggg");
        return user;
    }

    @GetMapping("/me")
    @ApiOperation(value = "用户获取认证信息")
    public Authentication getMe(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
