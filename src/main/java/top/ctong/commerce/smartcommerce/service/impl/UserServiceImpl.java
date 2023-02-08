package top.ctong.commerce.smartcommerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ctong.commerce.smartcommerce.constant.RedisKeys;
import top.ctong.commerce.smartcommerce.dao.UserDao;
import top.ctong.commerce.smartcommerce.enums.RespStatus;
import top.ctong.commerce.smartcommerce.exceptions.PassErrorException;
import top.ctong.commerce.smartcommerce.exceptions.UserNotFoundException;
import top.ctong.commerce.smartcommerce.model.UserDetailsModel;
import top.ctong.commerce.smartcommerce.model.UserModel;
import top.ctong.commerce.smartcommerce.model.vo.UserInfoVo;
import top.ctong.commerce.smartcommerce.service.UserService;
import top.ctong.commerce.smartcommerce.utils.IdUtils;

import javax.servlet.http.HttpServletRequest;
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

    @Setter(onMethod = @__(@Autowired))
    private PasswordEncoder passwordEncoder;

    @Setter(onMethod = @__(@Autowired))
    private HttpServletRequest req;

    /**
     * 通过用户名查找用户是否存在
     * @param username 用户名
     * @return Boolean
     * @author Clover You
     * @date 2022/12/6 21:48
     */
    @Override
    public Boolean checkExistByUsername(@NotNull String username) {
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
    public String registerByEmail(@NotNull String emailNo, @NotNull String password) {
        UserModel userModel = new UserModel();
        userModel.setEmail(emailNo);
        userModel.setPass(password);
        userModel.setCreateAt(new Date());
        userModel.setUserId(IdUtils.get32UUID());

        userDao.registerByEmail(userModel);
        return userModel.getUserId();
    }

    /**
     * 用户邮箱登录
     * @param email 邮箱
     * @param pass 密码
     * @return UserModel
     * @author Clover You
     * @date 2023/1/4 13:28
     */
    @Override
    public UserModel loginByEmailAndPass(@NotNull String email, @NotNull String pass)
        throws UserNotFoundException, PassErrorException {
        UserModel userModel = userDao.selectOne(new QueryWrapper<UserModel>().eq("email", email));

        if (userModel == null) {
            // 用户不存在
            throw new UserNotFoundException(RespStatus.USER_NOT_FOUND);
        }

        String userPassRaw = userModel.getPass();
        if (!passwordEncoder.matches(pass, userPassRaw)) {
            throw new PassErrorException(RespStatus.USER_LOGIN_PASS_ERROR);
        }

        // 将用户信息保存到 Security 上下文
        UserDetailsModel userDetails = new UserDetailsModel();
        userDetails.setUserModel(userModel);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities()
        );
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authToken);

        return userModel;
    }


    /**
     * 通过用户id查找用户信息
     * @param userId 用户id
     * @return UserModel
     * @author Clover You
     * @date 2023/2/8 21:58
     */
    @Override
    public UserModel queryUserById(@NotNull String userId) {
        UserModel userModel = userDao.selectById(userId);
        if (userModel != null) {
            return userModel;
        }
        throw new UserNotFoundException(RespStatus.USER_NOT_FOUND);
    }

    /**
     * 获取当前登录用户完整信息
     * @return UserInfoVo
     * @author Clover You
     * @date 2023/2/8 22:20
     */
    @Cacheable(
        cacheNames = RedisKeys.USER_SECURITY_DATA,
        key = "T(org.springframework.security.core.context.SecurityContextHolder).context.authentication.principal",
        unless = "true")
    @Override
    public UserInfoVo currentUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取当前登录的用户ID
        UserDetailsModel userDetail = (UserDetailsModel) authentication.getPrincipal();

        UserModel userModel = userDetail.getUserModel();

        UserInfoVo vo = new UserInfoVo();
        vo.setUser(userModel);

        return vo;
    }

    /**
     * 通过用户ID获取用户信息
     * @param userId 用户id
     * @return UserDetails
     * @author Clover You
     * @date 2023/2/8 22:51
     */
    @Override
    public UserDetailsModel userModelWrapperDetail(String userId) {
        UserModel userModel = queryUserById(userId);
        UserDetailsModel detailsModel = new UserDetailsModel();
        detailsModel.setUserModel(userModel);

        return detailsModel;
    }

}
