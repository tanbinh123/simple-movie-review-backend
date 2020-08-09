package cc.itsc.project.movie.review.backend.controller;

import cc.itsc.project.movie.review.backend.annotation.Security;
import cc.itsc.project.movie.review.backend.config.BackendProfileConfig;
import cc.itsc.project.movie.review.backend.exception.AccountAlwaysExistException;
import cc.itsc.project.movie.review.backend.exception.AccountNotFoundException;
import cc.itsc.project.movie.review.backend.exception.AccountVerificationFailedException;
import cc.itsc.project.movie.review.backend.pojo.enums.RoleEnum;
import cc.itsc.project.movie.review.backend.pojo.vo.common.ResultCodeEnum;
import cc.itsc.project.movie.review.backend.pojo.vo.common.ServiceResponseMessage;
import cc.itsc.project.movie.review.backend.pojo.vo.req.*;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.*;
import cc.itsc.project.movie.review.backend.service.AccountService;
import cc.itsc.project.movie.review.backend.service.MomentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */

@Api(tags = "影圈模块")
@RestController
@RequestMapping(value = "/moment", produces = MediaType.APPLICATION_JSON_VALUE)
public class MomentController {
    private final MomentsService momentsService;

    public MomentController(MomentsService momentsService) {
        this.momentsService = momentsService;
    }

    @Security(roles = RoleEnum.USER)
    @PostMapping(value = "",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("#* 创建影圈")
    public ServiceResponseMessage<DefaultHttpRsp> insertNewMomentsDetail(@RequestBody @Validated MomentsReq momentsReq) {
        momentsService.insertNewMomentsDetail(momentsReq);
       return ServiceResponseMessage.createBySuccessCodeMessage();
    }


    @Security(roles = RoleEnum.USER)
    @ApiOperation("# 分页拉取Moment信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageNo",value = "页码数",example = "1"),
            @ApiImplicitParam(name = "pageSize",value = "页码大小",example = "20")
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<PageOfInfoListRsp<MomentsRsp>> fetchPageOfMoments(@Min(value = 1,message = "页码数最少为1")@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                                                                         @Min (value = 1,message = "每页数量最小为1")@RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize) {
        PageOfInfoListRsp<MomentsRsp> pageOfMomentsRep = momentsService.fetchPageOfMomentsByPageInfo(pageNo,pageSize);
        return ServiceResponseMessage.createBySuccessCodeMessage(pageOfMomentsRep);
    }


    @Security(roles = RoleEnum.USER)
    @ApiOperation("# 分页自己的Moment信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageNo",value = "页码数",example = "1"),
            @ApiImplicitParam(name = "pageSize",value = "页码大小",example = "20")
    })
    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<PageOfInfoListRsp<MomentsRsp>> fetchPageOfMomentsByMe(@Min(value = 1,message = "页码数最少为1")@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                                                                          @Min (value = 1,message = "每页数量最小为1")@RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize) {
        PageOfInfoListRsp<MomentsRsp> pageOfMomentsRep = momentsService.fetchPageOfMomentsByMe(pageNo,pageSize);
        return ServiceResponseMessage.createBySuccessCodeMessage(pageOfMomentsRep);
    }


    @Security(roles = RoleEnum.ADMIN)
    @ApiOperation("# 分页待审核的Moment信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageNo",value = "页码数",example = "1"),
            @ApiImplicitParam(name = "pageSize",value = "页码大小",example = "20")
    })
    @GetMapping(value = "/review", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<PageOfInfoListRsp<MomentsRsp>> fetchReviewPageOfMoments(@Min(value = 1,message = "页码数最少为1")@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                                                                    @Min (value = 1,message = "每页数量最小为1")@RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize) {
        PageOfInfoListRsp<MomentsRsp> pageOfMomentsRep = momentsService.fetchReviewPageOfMoments(pageNo,pageSize);
        return ServiceResponseMessage.createBySuccessCodeMessage(pageOfMomentsRep);
    }

    @ApiOperation("# 删除Moment")
    @Security(roles = RoleEnum.ADMIN)
    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<DefaultHttpRsp> deleteMomentsByMid(@Min (value = 0,message = "MomentsId不能为空") @RequestParam(value = "mid") Long mid) {
        momentsService.deleteMomentsByMid(mid);
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }
}
