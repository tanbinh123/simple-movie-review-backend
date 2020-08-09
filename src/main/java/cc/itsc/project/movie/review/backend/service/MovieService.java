package cc.itsc.project.movie.review.backend.service;

import cc.itsc.project.movie.review.backend.pojo.vo.req.ModifyMovieReq;
import cc.itsc.project.movie.review.backend.pojo.vo.req.MovieDetailReq;
import cc.itsc.project.movie.review.backend.pojo.vo.req.MovieReviewReq;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.MovieDetailRsp;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.PageOfInfoListRsp;

import java.util.List;

public interface MovieService {
    /**
     * 创建电影分类信息
     *
     * @param classifyList 电影分类信息
     */
    void insertNewMovieClassifyDetail(List<String> classifyList);

    /**
     * 查询所有说有的电影分类信息
     *
     * @return 所有的电影属性分类
     */
    List<String> searchAllMovieClassify();

    /**
     * 插入新的电影信息
     *
     * @param movieDetailReq 新电影信息请求
     */
    void insertNewMovieDetail(MovieDetailReq movieDetailReq);

    /**
     *
     * 通过电影mid删除电影信息
     * @param mid 电影ID
     */
    void deleteMovieByMid(Long mid);

    /**
     * 修改详细信息
     *
     * @param modifyMovieReq 修改电影信息
     */
    void modifyMovieDetailByMid(ModifyMovieReq modifyMovieReq);

    /**
     * 通过电影名称和分页信息检索电影信息
     *
     * @param name 检索的电影的Key
     * @param pageNo 页码数
     * @param pageSize 每页的数量
     * @return 查询出的电影结果
     */
    PageOfInfoListRsp<MovieDetailRsp> searchMovieDetailsByKeyWithPageInfo(String name, Integer pageNo, Integer pageSize);

    /**
     * 分页按分类获取电影的详细信息
     *
     * @param classify 分类信息
     * @param pageNo 页面数
     * @param pageSize 分页的数据大小
     * @return 成功的查询结果
     */
    PageOfInfoListRsp<MovieDetailRsp> searchMovieDetailsByClassifyWithPageInfo(String classify, Integer pageNo, Integer pageSize);

    /**
     * 插入电影的影评信息
     *
     * @param movieReviewReq 电影的详细信息
     * @return 电影的详细信息
     */
    MovieDetailRsp insertNewMovieReview(MovieReviewReq movieReviewReq);

    /**
     *  根据电影唯一标示符号 拉取电影详细信息
     *
     * @param mid 电影的MID
     * @return 电影的详细详细拉取结果
     */
    MovieDetailRsp fetchMovieDetailsByMid(Long mid);

    void deleteReviewMovieWithRid(Long rid);
}
