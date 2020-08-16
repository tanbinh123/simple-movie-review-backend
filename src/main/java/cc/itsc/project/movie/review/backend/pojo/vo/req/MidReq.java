package cc.itsc.project.movie.review.backend.pojo.vo.req;

import io.swagger.annotations.ApiModel;

@ApiModel("Mid 请求")
public class MidReq {
    private Long mid;

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }
}
