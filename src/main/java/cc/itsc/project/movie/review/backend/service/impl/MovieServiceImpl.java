package cc.itsc.project.movie.review.backend.service.impl;

import cc.itsc.project.movie.review.backend.config.BackendProfileConfig;
import cc.itsc.project.movie.review.backend.dao.MovieDao;
import cc.itsc.project.movie.review.backend.dao.MovieReviewDao;
import cc.itsc.project.movie.review.backend.pojo.po.MoviePO;
import cc.itsc.project.movie.review.backend.pojo.po.MovieReviewPO;
import cc.itsc.project.movie.review.backend.pojo.vo.req.ModifyMovieReq;
import cc.itsc.project.movie.review.backend.pojo.vo.req.MovieDetailReq;
import cc.itsc.project.movie.review.backend.pojo.vo.req.MovieReviewReq;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.MovieDetailRsp;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.MovieReviewDetailRsp;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.MovieReviewRsp;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.PageOfInfoListRsp;
import cc.itsc.project.movie.review.backend.service.AccountService;
import cc.itsc.project.movie.review.backend.service.MovieService;
import cc.itsc.project.movie.review.backend.utils.HttpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Resource
    MovieDao movieDao;
    @Resource
    MovieReviewDao movieReviewDao;
    @Resource
    AccountService accountService;

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

    @Override
    public PageOfInfoListRsp<MovieDetailRsp> searchMovieDetailsByPageInfo(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<MoviePO> pageOfMovie = new PageInfo<>(movieDao.selectAllMovieList());
        return buildPageOfMovieDetailRspByPageHelperInfo(pageOfMovie);
    }

    @Override
    public MovieDetailRsp fetchMovieDetailsByMid(Long mid) {
        MoviePO movieInfo = movieDao.selectByPrimaryKey(mid);
        MovieDetailRsp movieDetailRsp = new MovieDetailRsp();
        BeanUtils.copyProperties(movieInfo, movieDetailRsp);
        List<MovieReviewPO> movieReviewList = movieReviewDao.selectReviewListByMid(mid);
        List<MovieReviewDetailRsp> movieReviewDetailRspList = new ArrayList<>();
        for (MovieReviewPO movieReview : movieReviewList) {
            MovieReviewDetailRsp movieReviewDetailRsp = new MovieReviewDetailRsp();
            BeanUtils.copyProperties(movieReview, movieReviewDetailRsp);
            movieReviewDetailRsp.setUserProfileRsp(accountService.fetchUserProfileByUid(movieReview.getUid()));
            movieReviewDetailRspList.add(movieReviewDetailRsp);
        }
        movieDetailRsp.setMovieReviewDetailRspList(movieReviewDetailRspList);
        return movieDetailRsp;
    }

    @Override
    public MovieDetailRsp insertNewMovieReview(MovieReviewReq movieReviewReq) {
        MovieReviewPO movieReview = new MovieReviewPO();
        BeanUtils.copyProperties(movieReviewReq,movieReview);
        movieReview.setUid(HttpUtil.getUserUid());
        movieReview.setReviewTime(System.currentTimeMillis());
        movieReviewDao.insertSelective(movieReview);
        return fetchMovieDetailsByMid(movieReviewReq.getMid());
    }

    @Override
    public PageOfInfoListRsp<MovieReviewRsp> fetchReviewPageOfMomentsReview(Integer pageNo, Integer pageSize) {
        PageOfInfoListRsp<MovieReviewRsp> movieReviewRspPageOfInfoListRsp = new PageOfInfoListRsp<>();
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<MovieReviewPO> movieReviewsList = new PageInfo<>(movieReviewDao.selectAllReviewList());
        BeanUtils.copyProperties(movieReviewsList, movieReviewRspPageOfInfoListRsp);
        movieReviewRspPageOfInfoListRsp.setDataList(movieReviewsList.getList().stream().map(review->{
            MovieReviewRsp movieReviewRsp = new MovieReviewRsp();
            BeanUtils.copyProperties(review, movieReviewRsp);
            movieReviewRsp.setUserProfileRsp(accountService.fetchUserProfileByUid(review.getUid()));
            MoviePO movie =  movieDao.selectByPrimaryKey(review.getMid());
            if(null != movie){
                MovieDetailRsp movieDetailRsp = new MovieDetailRsp();
                BeanUtils.copyProperties(movie,movieDetailRsp);
                movieReviewRsp.setMovieDetailRsp(movieDetailRsp);
            }
            return movieReviewRsp;
        }).collect(Collectors.toList()));
        return movieReviewRspPageOfInfoListRsp;
    }


    private PageOfInfoListRsp<MovieDetailRsp> buildPageOfMovieDetailRspByPageHelperInfo(PageInfo<MoviePO>  pageOfMovie){
        PageOfInfoListRsp<MovieDetailRsp> pageOfMoviesRsp = new PageOfInfoListRsp<>();
        pageOfMoviesRsp.setDataList(pageOfMovie.getList().stream().map((movieInfo->{
            MovieDetailRsp movieDetailRsp = new MovieDetailRsp();
            BeanUtils.copyProperties(movieInfo, movieDetailRsp);
            movieDetailRsp.setClassifyList(movieDao.selectMovieClassifyByMid(movieDetailRsp.getMid()));
            return movieDetailRsp;
        })).collect(Collectors.toList()));
        BeanUtils.copyProperties(pageOfMovie, pageOfMoviesRsp);
        return pageOfMoviesRsp;
    }


    @Override
    public void deleteReviewMovieWithRid(Long rid) {
        movieDao.deleteReviewMovieWithRid(rid);
    }
}
