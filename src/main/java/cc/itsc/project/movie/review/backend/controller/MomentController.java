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
    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
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
    @GetMapping(value = "/moments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<PageOfInfoListRsp<MomentsRsp>> fetchPageOfMoments(@Min(value = 1,message = "页码数最少为1")@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                                                                         @Min (value = 1,message = "每页数量最小为1")@RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize) {
        PageOfInfoListRsp<MomentsRsp> pageOfMomentsRep = momentsService.fetchPageOfMomentsByPageInfo(pageNo,pageSize);
        return ServiceResponseMessage.createBySuccessCodeMessage(pageOfMomentsRep);
    }
//
//    @ApiOperation("#* 用户注册")
//    @Security(roles = RoleEnum.USER)
//    @PostMapping(value = "/sign/up", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage<SignRsp> signUp(@RequestBody @Validated SignUpReq signUpReq) {
//        return null;
//    }
//
//    @ApiOperation("#* 修改用户信息")
//    @Security(roles = {RoleEnum.USER, RoleEnum.ADMIN})
//    @PutMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage<UserProfileRsp> modifyProfile(@RequestBody @Validated ModifyProfileReq modifyProfileReq) {
//        return null;
//    }
//
//    @ApiOperation("#* 获取用户信息")
//    @Security(roles = {RoleEnum.USER, RoleEnum.ADMIN})
//    @GetMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiImplicitParam(name = "uid",value = "用户Uid",example = "123456")
//    public ServiceResponseMessage<UserProfileRsp> fetchProfileByUid(@RequestParam(value = "uid",required = false) Integer uid) {
//        return null;
//    }
//
//    @ApiOperation("#* 修改用户密码")
//    @Security(roles = {RoleEnum.ADMIN,RoleEnum.USER})
//    @PutMapping(value = "/profile/password", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage<DefaultHttpRsp> modifyAccountPassword(@RequestBody @Validated ModifyAccountPasswordReq modifyAccountPasswordReq) {
//        return null;
//    }
//
//    @ApiOperation("# 删除用户")
//    @Security(roles = RoleEnum.ADMIN)
//    @DeleteMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage<DefaultHttpRsp> deleteAccountProfile(@RequestParam(value = "uid") Integer uid) {
//        return null;
//    }
}
