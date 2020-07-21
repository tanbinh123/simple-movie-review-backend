package cc.itsc.project.movie.review.backend.pojo.vo.req;

/**
 * @author Leonardo iWzl
 * @version 1.0
 * @date 2020/2/23 14:11
 */
public class ModifyProfileReq {
    private Integer uid;
    private Long birthday;
    private String nikeName;
    private String signature;
    private String avatar;

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }
    public void setUid(Integer uid){
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
