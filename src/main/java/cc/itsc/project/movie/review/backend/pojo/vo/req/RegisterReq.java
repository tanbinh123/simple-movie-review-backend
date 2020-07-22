package cc.itsc.project.movie.review.backend.pojo.vo.req;

import cc.itsc.project.movie.review.backend.pojo.vo.HttpRequset;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */

@ApiModel(description = "用户注册属性")
public class RegisterReq implements HttpRequset {
    @ApiModelProperty(value = "用户名(登录)",example = "Leonardo",required = true)
    @Length(min = 4,max = 16,message = "用户名最小长度最小为4最大为16")
    private String userName;

    @ApiModelProperty(value = "用户密码",example = "12345678",required = true)
    @Length(min = 8,max = 16,message = "密码最小长度最小为8最大为16")
    private String password;

    @ApiModelProperty(value = "昵称(显示)",example = "Leonardo",required = true)
    @Length(min = 4,max = 16,message = "昵称最小长度最小为4最大为16")
    private String nikeName;

    @ApiModelProperty(value = "用户名",example = "Leonardo",required = true)
    @Length(min = 4,max = 16,message = "用户名最小长度最小为4最大为16")
    private String gender;

    @ApiModelProperty(value = "用户名",example = "Leonardo",required = true)
    @Length(min = 4,max = 16,message = "用户名最小长度最小为4最大为16")
    private String avatar;

    @ApiModelProperty(value = "用户名",example = "Leonardo",required = true)
    @Length(min = 4,max = 16,message = "用户名最小长度最小为4最大为16")
    private String role;

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

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
