package cc.itsc.project.movie.review.backend.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MovieDao {

    /**
     * 插入新的分类
     *
     * @param classifyList 新的电影分类列表
     */
    void insertMovieClassifyList(@Param("classifyList") List<String> classifyList);

    /**
     * 拉取所有的电影信息
     *
     * @return 拉取到的所有的分类信息
     */
    List<String> selectAllMovieClassify();
}
