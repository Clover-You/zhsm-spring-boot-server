package top.ctong.commerce.smartcommerce.components.userPlatformStrategy.granter;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import top.ctong.commerce.smartcommerce.components.userPlatformStrategy.UserPlatformGranter;
import top.ctong.commerce.smartcommerce.constant.RedisKeys;
import top.ctong.commerce.smartcommerce.enums.RespStatus;
import top.ctong.commerce.smartcommerce.exceptions.ParamsErrorException;
import top.ctong.commerce.smartcommerce.exceptions.PassErrorException;
import top.ctong.commerce.smartcommerce.exceptions.UserNotFoundException;
import top.ctong.commerce.smartcommerce.model.UserModel;
import top.ctong.commerce.smartcommerce.model.dto.UserLoginDto;
import top.ctong.commerce.smartcommerce.model.redis.RedisEmailCodeEntity;
import top.ctong.commerce.smartcommerce.service.UserService;
import top.ctong.commerce.smartcommerce.utils.JwtUtils;
import top.ctong.commerce.smartcommerce.utils.R;
import top.ctong.commerce.smartcommerce.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

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
 * 邮箱
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2022-12-02 15:46
 */
@Component
@Slf4j
public class EmailGranter implements Serializable, UserPlatformGranter {

    private static final long serialVersionUID = 4064314388959468507L;

    @Setter(onMethod = @__(@Autowired))
    private RedisTemplate<String, Object> redisTemplate;

    @Setter(onMethod = @__(@Autowired))
    private UserService userService;

    @Setter(onMethod = @__(@Autowired))
    private HttpServletRequest req;

    @Setter(onMethod = @__(@Autowired))
    private HttpServletResponse resp;

    /**
     * 用户注册
     * @return R
     * @author Clover You
     * @date 2022/12/2 15:32
     */
    @Override
    public R register(String username, String password, String verifyCode) throws ParamsErrorException {
        String usernameMd5 = DigestUtils.md5DigestAsHex(username.getBytes(StandardCharsets.UTF_8));
        String redisKey = RedisKeys.USER_REGISTER_EMAIL_CODE + usernameMd5;

        BoundValueOperations<String, Object> ops = redisTemplate.boundValueOps(redisKey);

        RedisEmailCodeEntity emailCodeEntity = (RedisEmailCodeEntity) ops.get();
        if (emailCodeEntity == null || !emailCodeEntity.getCode().equals(verifyCode)) {
            throw new ParamsErrorException(RespStatus.VERIFY_CODE_ERROR);
        }

        Boolean userExist = userService.checkExistByUsername(username);
        if (Boolean.TRUE.equals(userExist)) {
            // 邮箱已经被注册了
            throw new ParamsErrorException(RespStatus.EMAIL_HAS_BEEN_REGISTERED);
        }

        // 开始注册
        String userId = userService.registerByEmail(username, password);
        if (!StringUtils.hasText(userId)) return R.fail();

        log.debug("user id == {}", userId);
        return R.ok();
    }

    /**
     * 用户登录
     * @param dto 登录参数
     * @return R
     * @author Clover You
     * @date 2023/1/3 14:41
     */
    @Override
    public R login(UserLoginDto dto) throws UserNotFoundException, PassErrorException, ParamsErrorException {
        if (!StringUtils.hasText(dto.getEmail())) {
            throw new ParamsErrorException(RespStatus.EMAIL_CANNOT_BE_EMPTY);
        }

        if (!StringUtils.hasText(dto.getPass())) {
            throw new ParamsErrorException(RespStatus.PASSWORD_CANNOT_BE_EMPTY);
        }

        UserModel userModel = userService.loginByEmailAndPass(dto.getEmail(), dto.getPass());
        String jwt = JwtUtils.generatorToken(userModel);
        resp.addHeader(JwtUtils.TOKEN_HEADER, jwt);
        return R.ok(jwt);
    }
}
