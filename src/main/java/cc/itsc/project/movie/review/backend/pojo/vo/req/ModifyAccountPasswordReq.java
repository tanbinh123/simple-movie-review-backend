package cc.itsc.project.movie.review.backend.pojo.vo.req;

public class ModifyAccountPasswordReq {
    private Integer uid;
    private String password;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
