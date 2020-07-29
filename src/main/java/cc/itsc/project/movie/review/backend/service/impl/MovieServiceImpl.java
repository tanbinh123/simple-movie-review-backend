package cc.itsc.project.movie.review.backend.service.impl;

import cc.itsc.project.movie.review.backend.dao.MovieDao;
import cc.itsc.project.movie.review.backend.service.MovieService;
import org.springframework.stereotype.Service;

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
}
