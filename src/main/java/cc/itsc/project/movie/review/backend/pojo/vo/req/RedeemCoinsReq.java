package cc.itsc.project.movie.review.backend.pojo.vo.req;

/**
 * @author Leonardo iWzl
 * @version 1.0
 * @date 2020/3/19 23:07
 */
public class RedeemCoinsReq {
    private String lanaId;
    private String checkKey;
    private String source;
    private int point;

    public String getLanaId() {
        return lanaId;
    }

    public void setLanaId(String lanaId) {
        this.lanaId = lanaId;
    }

    public String getCheckKey() {
        return checkKey;
    }

    public void setCheckKey(String checkKey) {
        this.checkKey = checkKey;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
