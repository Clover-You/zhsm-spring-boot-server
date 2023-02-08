package top.ctong.commerce.smartcommerce.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ctong.commerce.smartcommerce.model.UserModel;

import java.io.Serializable;

/**
 * <p>
 * 用户信息vo
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-02-08 21:43
 */
@Data
@ApiModel("用户信息")
public class UserInfoVo implements Serializable {

    private static final long serialVersionUID = 8694464201129328286L;

    /**
     * 用户信息
     */
    @ApiModelProperty("用户信息")
    private UserModel user;

}
