package cc.itsc.project.movie.review.backend.service.impl;

import cc.itsc.project.movie.review.backend.config.BackendProfileConfig;
import cc.itsc.project.movie.review.backend.dao.MovieDao;
import cc.itsc.project.movie.review.backend.pojo.po.MoviePO;
import cc.itsc.project.movie.review.backend.pojo.vo.req.MovieDetailReq;
import cc.itsc.project.movie.review.backend.service.MovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Resource
    MovieDao movieDao;

    @Override
    public void insertNewMovieClassifyDetail(List<String> classifyList) {
        movieDao.insertMovieClassifyList(classifyList);
    }

    @Override
    public List<String> searchAllMovieClassify() {
        return movieDao.selectAllMovieClassify();
    }

    @Override
    @Transactional
    public void insertNewMovieDetail(MovieDetailReq movieDetailReq) {
        MoviePO movieDetail = new MoviePO();
        BeanUtils.copyProperties(movieDetailReq,movieDetail);
        if(movieDao.insertSelective(movieDetail) == BackendProfileConfig.ROW_NUMBER_1){
            List<String> classifyList =  movieDetailReq.getClassifyList();
            movieDao.insertBindClassifyList(movieDetail.getMid(),classifyList);
        }
    }

    @Override
    @Transactional
    public void deleteMovieByMid(Long mid) {
        movieDao.deleteByPrimaryKey(mid);
        movieDao.deleteClassifyByMid(mid);
    }
}
