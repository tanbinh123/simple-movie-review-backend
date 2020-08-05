package cc.itsc.project.movie.review.backend.dao;

import cc.itsc.project.movie.review.backend.pojo.po.MovieReviewPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MovieReviewDao {
    /**
     * delete by primary key
     *
     * @param rid primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long rid);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(MovieReviewPO record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(MovieReviewPO record);

    /**
     * select by primary key
     *
     * @param rid primary key
     * @return object by primary key
     */
    MovieReviewPO selectByPrimaryKey(Long rid);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(MovieReviewPO record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(MovieReviewPO record);

    /**
     * 查询电影的评论信息
     *
     * @param mid 电影的MID
     * @return 电影影评信息
     */
    List<MovieReviewPO> selectReviewListByMid(@Param("mid") Long mid);
}