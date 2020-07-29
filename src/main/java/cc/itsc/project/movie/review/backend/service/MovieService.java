package cc.itsc.project.movie.review.backend.service;

import cc.itsc.project.movie.review.backend.pojo.vo.req.MovieDetailReq;

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
}
