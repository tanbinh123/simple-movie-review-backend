package cc.itsc.project.movie.review.backend.service.impl;

import cc.itsc.project.movie.review.backend.config.BackendProfileConfig;
import cc.itsc.project.movie.review.backend.dao.MovieDao;
import cc.itsc.project.movie.review.backend.pojo.po.AccountPO;
import cc.itsc.project.movie.review.backend.pojo.po.MoviePO;
import cc.itsc.project.movie.review.backend.pojo.vo.req.ModifyMovieReq;
import cc.itsc.project.movie.review.backend.pojo.vo.req.MovieDetailReq;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.MovieDetailRsp;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.PageOfInfoListRsp;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.UserProfileRsp;
import cc.itsc.project.movie.review.backend.service.MovieService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void modifyMovieDetailByMid(ModifyMovieReq modifyMovieReq) {
        MoviePO movieDetail = new MoviePO();
        BeanUtils.copyProperties(modifyMovieReq,movieDetail);
        movieDao.updateByPrimaryKeySelective(movieDetail);
        if(null != modifyMovieReq.getClassifyList() && !modifyMovieReq.getClassifyList().isEmpty()){
            movieDao.deleteClassifyByMid(modifyMovieReq.getMid());
            movieDao.insertBindClassifyList(modifyMovieReq.getMid(),modifyMovieReq.getClassifyList());
        }
    }

    @Override
    public PageOfInfoListRsp<MovieDetailRsp> searchMovieDetailsByKeyWithPageInfo(String name, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<MoviePO> pageOfMovie = new PageInfo<>(movieDao.selectMovieListName(name));
        return buildPageOfMovieDetailRspByPageHelperInfo(pageOfMovie);
    }


    @Override
    public PageOfInfoListRsp<MovieDetailRsp> searchMovieDetailsByClassifyWithPageInfo(String classify, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<MoviePO> pageOfMovieMidWithClassify = new PageInfo<>(movieDao.selectMovieListByClassify(classify));
        return buildPageOfMovieDetailRspByPageHelperInfo(pageOfMovieMidWithClassify);
    }


    private PageOfInfoListRsp<MovieDetailRsp> buildPageOfMovieDetailRspByPageHelperInfo(PageInfo<MoviePO>  pageOfMovie){
        PageOfInfoListRsp<MovieDetailRsp> pageOfMoviesRsp = new PageOfInfoListRsp<>();
        pageOfMoviesRsp.setDataList(pageOfMovie.getList().stream().map((movieInfo->{
            MovieDetailRsp movieDetailRsp = new MovieDetailRsp();
            BeanUtils.copyProperties(movieInfo, movieDetailRsp);
            return movieDetailRsp;
        })).collect(Collectors.toList()));
        BeanUtils.copyProperties(pageOfMovie, pageOfMoviesRsp);
        return pageOfMoviesRsp;
    }
}
