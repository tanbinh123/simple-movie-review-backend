package cc.itsc.project.movie.review.backend.controller;

import cc.itsc.project.movie.review.backend.annotation.Security;
import cc.itsc.project.movie.review.backend.pojo.enums.RoleEnum;
import cc.itsc.project.movie.review.backend.pojo.vo.common.ServiceResponseMessage;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.DefaultHttpRsp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */

@Api(tags = "影视模块")
@RestController
@RequestMapping("/movie")
public class MovieController {

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
