package com.lz.bookstore.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lz.bookstore.common.Constants;
import com.lz.bookstore.entity.User;
import com.lz.bookstore.exception.ServiceException;
import com.lz.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
//        if (handler instanceof HandlerMethod){
//            return true;
//        }
        if (StrUtil.isBlank(token)){
            throw new ServiceException(Constants.code_401,"无token");
        }
        String userid;
        try {
            userid = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new ServiceException(Constants.code_401,"token载荷验证失败");
        }

        User user = userService.getById(userid);
        if (user == null){
            throw new ServiceException(Constants.code_401,"用户不存在");
        }

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ServiceException(Constants.code_401,"token验证失败");
        }


        return true;


    }
}
