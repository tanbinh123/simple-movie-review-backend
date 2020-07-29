package cc.itsc.project.movie.review.backend.controller;

import cc.itsc.project.movie.review.backend.annotation.Security;
import cc.itsc.project.movie.review.backend.pojo.enums.RoleEnum;
import cc.itsc.project.movie.review.backend.pojo.vo.common.ServiceResponseMessage;
import cc.itsc.project.movie.review.backend.pojo.vo.req.ClassifiesReq;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.ClassifiesRsp;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.DefaultHttpRsp;
import cc.itsc.project.movie.review.backend.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */

@Api(tags = "影视模块")
@RestController
@RequestMapping("/movie")
public class MovieController {
    private final
    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @ApiOperation("# 新增电影分类")
    @Security(roles = RoleEnum.ADMIN)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<DefaultHttpRsp> insertNewMovieClassifyDetail(@RequestBody @Validated ClassifiesReq classifiesReq) {
        movieService.insertNewMovieClassifyDetail(classifiesReq.getClassifyList());
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }
    @ApiOperation("#* 拉取电影分类")
    @Security(roles = RoleEnum.ALL)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<ClassifiesRsp> searchAllMovieClassify() {
        List<String> classifyList = movieService.searchAllMovieClassify();
        ClassifiesRsp classifiesRsp = new ClassifiesRsp();
        classifiesRsp.setClassifyList(classifyList);
        return ServiceResponseMessage.createBySuccessCodeMessage(classifiesRsp);
    }

    @ApiOperation("#* 新增电影信息")
    @Security(roles = RoleEnum.ADMIN)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<DefaultHttpRsp> insertNewMovieDetail() {
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }

    @ApiOperation("#* 修改电影信息")
    @Security(roles = RoleEnum.ADMIN)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<DefaultHttpRsp> modifyMovieDetailByMid() {
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }

    @ApiOperation("#* 删除电影信息")
    @Security(roles = RoleEnum.ADMIN)
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<DefaultHttpRsp> deleteMovieByMid() {
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }

    @ApiOperation(value = "#* 按名称搜索电影基本信息",notes = "不包括电影影评")
    @Security(roles = RoleEnum.ALL)
    @GetMapping(value = "/name",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<DefaultHttpRsp> searchMovieDetailsByKey(String name) {
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }

    @ApiOperation(value = "#* 分类检索电影基本信息",notes = "不包括电影影评")
    @Security(roles = RoleEnum.ALL)
    @GetMapping(value = "/classify",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<DefaultHttpRsp> searchMovieDetailsByClassify(String classify) {
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }

    @ApiOperation(value = "#* 检索电影详细信息ByMid",notes = "包括电影影评")
    @Security(roles = RoleEnum.ALL)
    @GetMapping(value = "/mid",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<DefaultHttpRsp> fetchMovieDetailsByMid(Integer mid) {
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }


    @ApiOperation(value = "#* 发起影评",notes = "包括电影影评")
    @Security(roles = RoleEnum.USER)
    @PostMapping(value = "/review",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<DefaultHttpRsp> reviewMovieWithMid(Integer mid) {
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }
}
