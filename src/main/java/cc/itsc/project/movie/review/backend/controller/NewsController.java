package cc.itsc.project.movie.review.backend.controller;

import cc.itsc.project.movie.review.backend.annotation.Security;
import cc.itsc.project.movie.review.backend.pojo.enums.RoleEnum;
import cc.itsc.project.movie.review.backend.pojo.vo.common.ServiceResponseMessage;
import cc.itsc.project.movie.review.backend.pojo.vo.req.ModifyNewsDetailReq;
import cc.itsc.project.movie.review.backend.pojo.vo.req.NewsDetailReq;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */

@Api("新闻模块")
@RestController
@RequestMapping(value = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsController {

//    @Autowired
//    NewsService newsService;
//
//    @Security(roles = RoleEnum.ALL, checkToken = false)
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage fetchAllNews() {
//        return newsService.fetchAllNews();
//    }
//
//    @Security(roles = RoleEnum.OSS)
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage postNewsDetail(@RequestBody NewsDetailReq newsDetailReq) {
//        return newsService.postNewsDetail(newsDetailReq);
//    }
//
//    @Security(roles = RoleEnum.OSS)
//    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage deleteNewsDetailByNewsId(@RequestParam("newsId") int newsId) {
//        return newsService.deleteNewsDetailByNewsId(newsId);
//    }
//
//    @Security(roles = RoleEnum.OSS)
//    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage modifyNewsDetailByNewsId(@RequestBody ModifyNewsDetailReq modifyNewsDetailReq) {
//        return newsService.modifyNewsDetailByNewsId(modifyNewsDetailReq);
//    }

}
