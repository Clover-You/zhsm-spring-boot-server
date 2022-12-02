package top.ctong.commerce.smartcommerce.components.userPlatformStrategy;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ctong.commerce.smartcommerce.exceptions.NotFoundStrategyException;

import javax.validation.constraints.NotNull;
import java.util.Map;

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
 *
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2022-12-02 15:17
 */
@Component
public class UserPlatformStrategyBuilder {

    private Map<String, UserPlatformGranter> granters;

    public UserPlatformStrategyBuilder(Map<String, UserPlatformGranter> granters) {
        this.granters = granters;
    }

    /**
     * 根据用户登录方式获取指定兼容组件
     * @param granter 策略名称
     * @return UserPlatformGranter
     * @author Clover You
     * @date 2022/12/2 15:30
     */
    public UserPlatformGranter getGranter(@NotNull String granter) throws NotFoundStrategyException {
        UserPlatformGranter target = granters.get(granter);
        if (target == null) throw new NotFoundStrategyException("without this strategy [" + granter + "]");
        return target;
    }

}
