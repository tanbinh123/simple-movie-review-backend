package cc.itsc.project.movie.review.backend.controller;

import cc.itsc.project.movie.review.backend.annotation.Security;
import cc.itsc.project.movie.review.backend.config.BackendProfileConfig;
import cc.itsc.project.movie.review.backend.exception.AccountAlwaysExistException;
import cc.itsc.project.movie.review.backend.exception.AccountNotFoundException;
import cc.itsc.project.movie.review.backend.exception.AccountVerificationFailedException;
import cc.itsc.project.movie.review.backend.pojo.enums.RoleEnum;
import cc.itsc.project.movie.review.backend.pojo.vo.common.ResultCodeEnum;
import cc.itsc.project.movie.review.backend.pojo.vo.common.ServiceResponseMessage;
import cc.itsc.project.movie.review.backend.pojo.vo.req.ModifyProfileReq;
import cc.itsc.project.movie.review.backend.pojo.vo.req.SignInReq;
import cc.itsc.project.movie.review.backend.pojo.vo.req.SignUpReq;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.SignRsp;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.UserProfileRsp;
import cc.itsc.project.movie.review.backend.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */

@Api(tags = "账户模块")
@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    @Autowired
    AccountService accountService;

    @Security(roles = RoleEnum.ALL, checkToken = false)
    @PostMapping(value = "/sign/in",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("用户登录")
    public ServiceResponseMessage<SignRsp> signIn(@RequestBody @Validated SignInReq signInReq) {
        try {
            SignRsp signRsp = accountService.signIn(signInReq);
            if(null != signInReq){
                return ServiceResponseMessage.createBySuccessCodeMessage(signRsp);
            }else {
                return ServiceResponseMessage.createByFailCodeMessage(BackendProfileConfig.ERROR_WORDING_SIGN_IN_ERROR);
            }
        }catch (AccountNotFoundException accountNotFoundException){
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.NOT_FIND,BackendProfileConfig.ERROR_WORDING_SIGN_NOT_FOUND);
        }catch (AccountVerificationFailedException accountVerificationFailedException){
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.USERNAME_OR_PASSWORD_ERROR,BackendProfileConfig.ERROR_WORDING_SIGN_VERIFICATION_FAILED);
        }
    }

    @ApiOperation("用户注册")
    @Security(roles = RoleEnum.ALL, checkToken = false)
    @PostMapping(value = "/sign/up", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<SignRsp> signUp(@RequestBody SignUpReq signUpReq) {
        try {
            SignRsp signRsp = accountService.signIn(signUpReq);
            if(null != signRsp){
                return ServiceResponseMessage.createBySuccessCodeMessage(signRsp);
            }else {
                return ServiceResponseMessage.createByFailCodeMessage(BackendProfileConfig.ERROR_WORDING_SIGN_IN_ERROR);
            }
        }catch (AccountNotFoundException accountNotFoundException){
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.NOT_FIND,BackendProfileConfig.ERROR_WORDING_SIGN_NOT_FOUND);
        }catch (AccountVerificationFailedException accountVerificationFailedException){
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.USERNAME_OR_PASSWORD_ERROR,BackendProfileConfig.ERROR_WORDING_SIGN_VERIFICATION_FAILED);
        }catch (AccountAlwaysExistException accountAlwaysExistException){
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.ACCOUNT_ALWAYS_EXISTS,BackendProfileConfig.ERROR_WORDING_SIGN_ALWAYS_EXIST);
        }
    }

    @ApiOperation("修改用户信息")
    @Security(roles = {RoleEnum.USER, RoleEnum.ADMIN})
    @PutMapping(value = "/modify", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<UserProfileRsp> modifyProfile(@RequestBody ModifyProfileReq modifyProfileReq) {
        UserProfileRsp userProfileRsp = accountService.modifyProfile(modifyProfileReq);
        if(null != userProfileRsp){
            return ServiceResponseMessage.createBySuccessCodeMessage(BackendProfileConfig.SUCCESS_WORDING_MODIFY_SUCCESS,userProfileRsp);
        }else {
           return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.BAD_REQUEST,BackendProfileConfig.ERROR_WORDING_MODIFY_FAILED);
        }
    }

    @ApiOperation("获取用户信息")
    @Security(roles = {RoleEnum.USER, RoleEnum.ADMIN})
    @GetMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage<UserProfileRsp> fetchProfileByUid(@RequestParam(value = "uid",required = false) Integer uid) {
        UserProfileRsp userProfileRsp = accountService.fetchUserProfileByUid(uid);
        if(null != userProfileRsp){
            return ServiceResponseMessage.createBySuccessCodeMessage(userProfileRsp);
        }else {
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.BAD_REQUEST,BackendProfileConfig.ERROR_WORDING_FETCH_FAILED);
        }
    }



//    @Autowired
//    AccountService accountService;
//
//    @Security(roles = RoleEnum.ALL, checkToken = false)
//    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage login(@RequestBody LoginReq loginReq) {
//        return accountService.login(loginReq);
//    }
//

//

//
//    @Security(roles = RoleEnum.OSS)
//    @PutMapping(value = "/modify/password", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage modifyAccountPassword(@RequestBody ModifyAccountPasswordReq modifyAccountPasswordReq) {
//        return accountService.modifyAccountPassword(modifyAccountPasswordReq);
//    }
//

//    @Security(roles = {RoleEnum.USER, RoleEnum.OSS})
//    @DeleteMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage deleteAccountProfileByUid(@RequestParam(value = "uid") Integer uid) {
//        return accountService.deleteAccountProfileByUid(uid);
//    }
//
//    @Security(roles = RoleEnum.OSS)
//    @GetMapping(value = "/all/user", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage fetchAllProfile() {
//        return accountService.fetchAllProfile();
//    }
//
//
//    @Security(roles = RoleEnum.OSS)
//    @PostMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage modifyUserIdentityPromotion(@RequestBody UidAndIdentityReq uidAndIdentityReq) {
//        return accountService.modifyUserIdentityPromotion(uidAndIdentityReq.getUid(),uidAndIdentityReq.getRole());
//    }
//
//    @Security(roles = {RoleEnum.USER, RoleEnum.OSS})
//    @GetMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage fetchAllAddressByUid(@RequestParam(value = "uid",required = false) Integer uid) {
//        return accountService.fetchAllAddressByUid(uid);
//    }
//
//    @Security(roles = {RoleEnum.USER, RoleEnum.OSS})
//    @PostMapping(value = "/address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage postNewAddress(@RequestBody AddressReq addressReq) {
//        return accountService.createNewAddress(addressReq);
//    }
//
//    @Security(roles = {RoleEnum.USER, RoleEnum.OSS})
//    @DeleteMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ServiceResponseMessage deleteAddressByIdAndUid(@RequestParam(value = "addressId",defaultValue = "0") int addressId, @RequestParam(value = "uid",required = false) Integer uid) {
//        return accountService.deleteAddressByIdAndUid(addressId,uid);
//    }
}
