package cc.itsc.project.movie.review.backend.service;

import cc.itsc.project.movie.review.backend.pojo.vo.req.ModifyProfileReq;
import cc.itsc.project.movie.review.backend.pojo.vo.req.SignInReq;
import cc.itsc.project.movie.review.backend.pojo.vo.req.SignUpReq;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.SignRsp;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.UserProfileRsp;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */
public interface AccountService {

    /**
     * 用户的登录请求
     *
     * @param signInReq 包含用户账号和用户密码
     * @return 登陆的返回结果
     */
    SignRsp signIn(SignInReq signInReq);

    /**
     * 用户的在注册接口
     *
     * @param signUpReq 注册需要的信息接口
     * @return 注册的登录结果
     */
    SignRsp signIn(SignUpReq signUpReq);

    /**
     * 修改用户Profile信息的结构
     *
     * @param modifyProfileReq 修改用户Profile信息
     * @return 修改用户Profile信息
     */
    UserProfileRsp modifyProfile(ModifyProfileReq modifyProfileReq);

    /**
     * 通过用户UID查询用户Profile信息
     *
     * @param uid 用户UID
     * @return 用户Profile的查询结果
     */
    UserProfileRsp fetchUserProfileByUid(Integer uid);
//
//
//    /**
//     * 通过用户UID查询用户Profile信息
//     *
//     * @param uid 用户UID
//     * @return 用户Profile的查询结果
//     */
//    UserProfileRsp fetchUserProfileByUid(Integer uid);
//
//    /**
//     * 根据UIN查询用户地址信息
//     *
//     * @param uid 用户UIN
//     * @return 用户地址信息
//     */
//    ServiceResponseMessage fetchAllAddressByUid(Integer uid);
//
//    /**
//     * 新增自己的邮件地址
//     *
//     * @param addressReq 地址请求
//     * @return 新增地址的处理结果
//     */
//    ServiceResponseMessage createNewAddress(AddressReq addressReq);
//
//    /**
//     * 删除用户地址信息通过地址ID和用户Uid
//     *
//     * @param addressId 用户地址ID
//     * @param uid 用户Uid
//     * @return 删除地址信息的处理结果
//     */
//    ServiceResponseMessage deleteAddressByIdAndUid(int addressId, Integer uid);
//
//    /**
//     * 查询所有的用户信息
//     *
//     * @return 查询到的用户信息的查询结果
//     */
//    ServiceResponseMessage fetchAllProfile();
//
//    /**
//     * 修改用户权限
//     *
//     * @param uid 用户Uid
//     * @param role 用户权限
//     * @return 修改权限后的用户查询结果
//     */
//    ServiceResponseMessage modifyUserIdentityPromotion(int uid, String role);
//
//    /**
//     * 删除商户账号
//     *
//     * @param uid 用户uid
//     * @return 返回用户账号删除的处理状态
//     */
//    ServiceResponseMessage deleteAccountProfileByUid(Integer uid);
//
//    /**
//     * 修改用户的
//     *
//     * @param modifyAccountPasswordReq 修改用户密码
//     * @return 密码修改的处理结果
//     */
//    ServiceResponseMessage modifyAccountPassword(ModifyAccountPasswordReq modifyAccountPasswordReq);
}
