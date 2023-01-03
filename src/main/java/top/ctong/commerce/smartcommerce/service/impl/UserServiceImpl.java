package top.ctong.commerce.smartcommerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ctong.commerce.smartcommerce.dao.UserDao;
import top.ctong.commerce.smartcommerce.model.UserModel;
import top.ctong.commerce.smartcommerce.service.UserService;
import top.ctong.commerce.smartcommerce.utils.IdUtils;

import java.util.Date;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀     ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒      ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░      ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄      ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄     ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒     ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 * Copyright 2022 Clover You.
 * <p>
 * 用户服务
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2022-12-06 21:59
 */
@Service
public class UserServiceImpl implements UserService {

    @Setter(onMethod = @__(@Autowired))
    private UserDao userDao;

    /**
     * 通过用户名查找用户是否存在
     * @param username 用户名
     * @return Boolean
     * @author Clover You
     * @date 2022/12/6 21:48
     */
    @Override
    public Boolean checkExistByUsername(String username) {
        QueryWrapper<UserModel> wrapper = new QueryWrapper<>();
        wrapper.eq("email", username);
        return userDao.exists(wrapper);
    }

    /**
     * 通过邮箱注册账号
     * @param emailNo 邮箱
     * @param password 密码
     * @return Boolean
     * @author Clover You
     * @date 2022/12/6 21:58
     */
    @Transactional
    @Override
    public String registerByEmail(String emailNo, String password) {
        UserModel userModel = new UserModel();
        userModel.setEmail(emailNo);
        userModel.setPass(password);
        userModel.setCreateAt(new Date());
        userModel.setUserId(IdUtils.get32UUID());

        userDao.registerByEmail(userModel);
        return userModel.getUserId();
    }
}
