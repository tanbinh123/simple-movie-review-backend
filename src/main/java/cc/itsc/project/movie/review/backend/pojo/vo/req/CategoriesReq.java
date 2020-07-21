package cc.itsc.project.movie.review.backend.pojo.vo.req;


/**
 * @author Leonardo iWzl
 * @version 1.0
 * @date 2020/3/22 10:32
 */
public class CategoriesReq {
    private String classKey;
    private Integer classNum;

    public String getClassKey() {
        return classKey;
    }

    public void setClassKey(String classKey) {
        this.classKey = classKey;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }
}
