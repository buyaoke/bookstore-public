package com.lz.bookstore.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lz.bookstore.entity.User;
import com.lz.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {
    private static IUserService staticuserService;

    @Resource
    private IUserService userService;

    @PostConstruct
    public void setUserService(){

        staticuserService = userService;
    }

    public static String getToken(String userID,String sign){
       return JWT.create().withAudience(userID)
                    .withExpiresAt(DateUtil.offsetHour(new Date(),7*24))
                    .sign(Algorithm.HMAC256(sign));

    }

    public static User getCurrentUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");

        if (StrUtil.isNotBlank(token)){
            try {
                String userid = JWT.decode(token).getAudience().get(0);
              return  staticuserService.getById(userid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    return null;
    }

}
