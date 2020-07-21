package cc.itsc.project.movie.review.backend.pojo.vo.req;

import cc.itsc.project.movie.review.backend.pojo.vo.HttpRequset;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */
@ApiModel(description = "用户登录")
public class LoginReq implements HttpRequset {
    @ApiModelProperty(value = "用户名",example = "Leonardo",required = true)
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @ApiModelProperty(value = "密码",example = "12345678",required = true)
    @NotBlank(message = "密码不能为空")
    @Length(min = 8,max = 16,message = "密码长度在8-16之间")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
