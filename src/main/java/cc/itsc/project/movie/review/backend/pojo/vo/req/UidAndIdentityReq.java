package cc.itsc.project.movie.review.backend.pojo.vo.req;

/**
 * @author Leonardo iWzl
 * @version 1.0
 * @date 2020/3/24 22:25
 */
public class UidAndIdentityReq {
    private int uid;
    private String role;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
