package cc.itsc.project.movie.review.backend.service;

import java.util.List;

public interface MovieService {
    void insertNewMovieClassifyDetail(List<String> classifyList);

    List<String> searchAllMovieClassify();
}
