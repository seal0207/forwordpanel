package com.leeroy.forwordpanel.forwordpanel.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.leeroy.forwordpanel.forwordpanel.dao.UserDao;
import com.leeroy.forwordpanel.forwordpanel.dao.UserTokenDao;
import com.leeroy.forwordpanel.forwordpanel.model.User;
import com.leeroy.forwordpanel.forwordpanel.model.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户token
 */
@Service
public class UserTokenService {

    @Autowired
    private UserTokenDao userTokenDao;

    @Autowired
    private UserDao userDao;

    /**
     * 保存用户token
     * @param userToken
     */
    public void saveUserToken(UserToken userToken){
        LambdaQueryWrapper<UserToken> queryWrapper = Wrappers.<UserToken>lambdaQuery().eq(UserToken::getToken, userToken)
                .gt(UserToken::getExpireTime, System.currentTimeMillis());
        UserToken existToken = userTokenDao.selectOne(queryWrapper);
        if(existToken!=null){
            existToken.setUpdateTime(System.currentTimeMillis());
            existToken.setExpireTime(existToken.getExpireTime()+30*60*1000);
            userTokenDao.updateById(userToken);
        }else {
            userToken.setCreateTime(System.currentTimeMillis());
            userTokenDao.insert(userToken);
        }
    }

    /**
     * 获取用户token
     * @param token
     * @return
     */
    public UserToken getUserToken(String token){
        if(token==null){
            return null;
        }
        LambdaQueryWrapper<UserToken> queryWrapper = Wrappers.<UserToken>lambdaQuery().eq(UserToken::getToken, token)
                .gt(UserToken::getExpireTime, System.currentTimeMillis());
        return userTokenDao.selectOne(queryWrapper);
    }

    /**
     * 获取用户token
     * @param token
     * @return
     */
    public User getUserByToken(String token){
        if(token==null){
            return null;
        }
        LambdaQueryWrapper<UserToken> queryWrapper = Wrappers.<UserToken>lambdaQuery().eq(UserToken::getToken, token)
                .gt(UserToken::getExpireTime, System.currentTimeMillis());
        UserToken userToken = userTokenDao.selectOne(queryWrapper);
        if(userToken==null){
            return null;
        }
        return userDao.selectById(userToken.getUserId());
    }

    /**
     * 删除token
     */
    public void delToken(String token){
        LambdaQueryWrapper<UserToken> queryWrapper = Wrappers.<UserToken>lambdaQuery().eq(UserToken::getToken, token);
        userTokenDao.delete(queryWrapper);
    }
}
