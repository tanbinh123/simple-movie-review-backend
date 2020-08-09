package cc.itsc.project.movie.review.backend.controller;

import cc.itsc.project.movie.review.backend.annotation.Security;
import cc.itsc.project.movie.review.backend.pojo.enums.RoleEnum;
import cc.itsc.project.movie.review.backend.pojo.vo.common.ServiceResponseMessage;
import cc.itsc.project.movie.review.backend.pojo.vo.req.MomentsReq;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.DefaultHttpRsp;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.MomentsRsp;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.PageOfInfoListRsp;
import cc.itsc.project.movie.review.backend.service.MomentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */

@Api(tags = "公告模块")
@RestController
@RequestMapping(value = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsController {
    private final MomentsService momentsService;

    public NewsController(MomentsService momentsService) {
        this.momentsService = momentsService;
    }

    @Security(roles = RoleEnum.ADMIN)
    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("#* 创建公告")
    public ServiceResponseMessage<DefaultHttpRsp> insertNewMomentsDetail(@RequestBody @Validated MomentsReq momentsReq) {
        momentsService.insertNewMomentsDetail(momentsReq);
       return ServiceResponseMessage.createBySuccessCodeMessage();
    }


    @Security(roles = RoleEnum.ALL)
    @ApiOperation("# 分页拉取公告信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageNo",value = "页码数",example = "1"),
            @ApiImplicitParam(name = "pageSize",value = "页码大小",example = "20")
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<PageOfInfoListRsp<MomentsRsp>> fetchPageOfMoments(@Min(value = 1,message = "页码数最少为1")@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                                                                         @Min (value = 1,message = "每页数量最小为1")@RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize) {
        PageOfInfoListRsp<MomentsRsp> pageOfMomentsRep = momentsService.fetchPageOfMomentsByPageInfo(pageNo,pageSize);
        return ServiceResponseMessage.createBySuccessCodeMessage(pageOfMomentsRep);
    }

    @ApiOperation("# 删除News")
    @Security(roles = RoleEnum.ADMIN)
    @DeleteMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<DefaultHttpRsp> deleteMomentsByMid(@Min (value = 0,message = "NewId不能为空") @RequestParam(value = "nid") Long nid) {
        momentsService.deleteMomentsByMid(nid);
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }
}
