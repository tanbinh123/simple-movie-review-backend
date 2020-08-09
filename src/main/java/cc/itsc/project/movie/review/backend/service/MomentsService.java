package cc.itsc.project.movie.review.backend.service;


import cc.itsc.project.movie.review.backend.pojo.vo.req.MomentsReq;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.MomentsRsp;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.PageOfInfoListRsp;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */
public interface MomentsService {
    /**
     * 添加新的电影信息请求
     *
     * @param momentsReq moments动态请求
     */
    void insertNewMomentsDetail(MomentsReq momentsReq);

    /**
     * 分页获取电影信息请求信息
     *
     * @param pageNo 分页页码
     * @param pageSize 每页的数量
     * @return 查询后的每页的影评信息,带分页信息
     */
    PageOfInfoListRsp<MomentsRsp> fetchPageOfMomentsByPageInfo(Integer pageNo, Integer pageSize);
}
