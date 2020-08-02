package cc.itsc.project.movie.review.backend.dao;

import cc.itsc.project.movie.review.backend.pojo.po.MoviePO;
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

    /**
     * 绑定电影分类
     *
     * @param mid          电影ID
     * @param classifyList 电影分类
     */
    void insertBindClassifyList(@Param("mid") Long mid, @Param("classifyList") List<String> classifyList);

    int deleteByPrimaryKey(Long mid);

    int insert(MoviePO record);

    int insertSelective(MoviePO record);

    MoviePO selectByPrimaryKey(Long mid);

    int updateByPrimaryKeySelective(MoviePO record);

    int updateByPrimaryKey(MoviePO record);

    void deleteClassifyByMid(@Param("mid") Long mid);

    List<MoviePO> selectMovieListName(@Param("name") String name);

    /**
     * 查询电影详细信息通过电影分类信息
     *
     * @param classify 电影的分类信息
     * @return 查询到的电影属性信息
     */
    List<MoviePO> selectMovieListByClassify(String classify);

    /**
     * 查询电影分类信息通过电影ID
     *
     * @param mid 电影唯一ID
     * @return 查询到的电影ID
     */
    List<String> selectMovieClassifyByMid(@Param("mid") Long mid);
}
