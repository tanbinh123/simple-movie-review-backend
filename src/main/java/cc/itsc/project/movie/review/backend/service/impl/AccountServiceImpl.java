package cc.itsc.project.movie.review.backend.service.impl;

import cc.itsc.project.movie.review.backend.config.BackendProfileConfig;
import cc.itsc.project.movie.review.backend.dao.AccountDao;
import cc.itsc.project.movie.review.backend.exception.AccountAlwaysExistException;
import cc.itsc.project.movie.review.backend.exception.AccountNotFoundException;
import cc.itsc.project.movie.review.backend.exception.AccountVerificationFailedException;
import cc.itsc.project.movie.review.backend.pojo.enums.RoleEnum;
import cc.itsc.project.movie.review.backend.pojo.po.AccountPO;
import cc.itsc.project.movie.review.backend.pojo.vo.req.ModifyProfileReq;
import cc.itsc.project.movie.review.backend.pojo.vo.req.SignInReq;
import cc.itsc.project.movie.review.backend.pojo.vo.req.SignUpReq;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.SignRsp;
import cc.itsc.project.movie.review.backend.pojo.vo.rsp.UserProfileRsp;
import cc.itsc.project.movie.review.backend.service.AccountService;
import cc.itsc.project.movie.review.backend.utils.AESUtil;
import cc.itsc.project.movie.review.backend.utils.HttpUtil;
import cc.itsc.project.movie.review.backend.utils.JWTUtil;
import cc.itsc.project.movie.review.backend.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Override
    public SignRsp signIn(SignInReq signInReq) {
        AccountPO accountInfo = accountDao.selectAccountInfoByUserName(signInReq.getAccount());
        if (null == accountInfo) {
            throw new AccountNotFoundException();
        }
        boolean isVerificationSuccess = accountInfo.getRole().equalsIgnoreCase(signInReq.getRole())
                && AESUtil.decryptAES(accountInfo.getSecretKey(),accountInfo.getPassword()).equals(signInReq.getPassword());
        if(!isVerificationSuccess){
            throw new AccountVerificationFailedException();
        }else {
            SignRsp signRsp = new SignRsp();
            signRsp.setUid(accountInfo.getUid());
            signRsp.setToken(JWTUtil.createToken(accountInfo.getUid(),
                    RoleEnum.getRole(accountInfo.getRole()), accountInfo.getPassword()));
            return signRsp;
        }

    }

    @Override
    public SignRsp signIn(SignUpReq signUpReq) {
        AccountPO accountInfo = accountDao.selectAccountInfoByUserName(signUpReq.getAccount());
        if (accountInfo != null) {
            throw new AccountAlwaysExistException();
        } else {
            accountInfo = new AccountPO();
            BeanUtils.copyProperties(signUpReq, accountInfo);
            accountInfo.setCreateTime(System.currentTimeMillis());
            String secretKey = UUID.randomUUID().toString();
            accountInfo.setSecretKey(secretKey);
            accountInfo.setPassword(AESUtil.encryptAES(secretKey, signUpReq.getPassword()));
            if (accountDao.insertAccountDetail(accountInfo) == BackendProfileConfig.ROW_NUMBER_1) {
                SignInReq signInReq = new SignInReq();
                signInReq.setAccount(signUpReq.getAccount());
                signInReq.setPassword(signUpReq.getPassword());
                signInReq.setRole(signUpReq.getRole());
               return signIn(signInReq);
            } else {
                return null;
            }
        }
    }

    @Override
    public UserProfileRsp modifyProfile(ModifyProfileReq modifyProfileReq) {
        Integer uid = null;
        if (ObjectUtil.isNotEmpty(modifyProfileReq.getUid())) {
            uid = modifyProfileReq.getUid();
        } else {
            uid = HttpUtil.getUserUid();
        }
        if (ObjectUtil.isNotEmpty(modifyProfileReq.getAvatar())) {
            accountDao.updateProfileAvatarByUid(modifyProfileReq.getAvatar(), uid);
        }
        if (ObjectUtil.isNotEmpty(modifyProfileReq.getNikeName())) {
            accountDao.updateProfileNikeNameByUid(modifyProfileReq.getNikeName(), uid);
        }
        if (ObjectUtil.isNotEmpty(modifyProfileReq.getSignature())) {
            accountDao.updateProfileSignatureByUid(modifyProfileReq.getSignature(), uid);
        }
        if (ObjectUtil.isNotEmpty(modifyProfileReq.getBirthday())) {
            accountDao.updateProfileBirthdayByUid(modifyProfileReq.getBirthday(), uid);
        }
        return fetchUserProfileByUid(uid);
    }

    @Override
    public UserProfileRsp fetchUserProfileByUid(Integer uid) {
        if (uid == null || 0 == uid) {
            uid = HttpUtil.getUserUid();
        }
        AccountPO accountInfo = accountDao.selectAccountInfoByUid(uid);
        if (null == accountInfo) {
            return null;
        } else {
            UserProfileRsp userProfileRsp = new UserProfileRsp();
            BeanUtils.copyProperties(accountInfo, userProfileRsp);
            userProfileRsp.setPassword(null);
            return userProfileRsp;
        }
    }


//
//    @Override
//    public ServiceResponseMessage fetchAllAddressByUid(Integer uid) {
//        if (uid == null || 0 == uid) {
//            uid = HttpUtil.getUserUid();
//        }
//        AddressRsp addressRsp = new AddressRsp();
//        List<AccountAddressPO> accountAddressList = addressDao.selectAccountAddressByUid(uid);
//        if (null == accountAddressList) {
//            return ServiceResponseMessage.createBySuccessCodeMessage("获取成功", addressRsp);
//        }
//        List<AddressRsp.Address> addressList = new ArrayList<>();
//        for (AccountAddressPO accountAddressInfo : accountAddressList) {
//            AddressRsp.Address address = new AddressRsp.Address();
//            BeanUtils.copyProperties(accountAddressInfo, address);
//            addressList.add(address);
//        }
//        addressRsp.setAddressList(addressList);
//        return ServiceResponseMessage.createBySuccessCodeMessage("获取成功", addressRsp);
//    }
//
//    @Override
//    public ServiceResponseMessage createNewAddress(AddressReq addressReq) {
//        if (null != addressReq) {
//            AccountAddressPO accountAddressEntity = new AccountAddressPO();
//            BeanUtils.copyProperties(addressReq, accountAddressEntity);
//            accountAddressEntity.setUid(HttpUtil.getUserUid());
//            addressDao.insertNewAddress(accountAddressEntity);
//            return ServiceResponseMessage.createBySuccessCodeMessage("获取成功", "");
//        }
//        return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.BAD_REQUEST, "");
//    }
//
//
//    @Override
//    public ServiceResponseMessage deleteAddressByIdAndUid(int addressId, Integer uid) {
//        if (null != uid && uid != 0) {
//            if (!uid.equals(HttpUtil.getUserUid())) {
//                return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.UNAUTHORIZED, "权限限制");
//            } else {
//                uid = HttpUtil.getUserUid();
//            }
//        } else {
//            uid = HttpUtil.getUserUid();
//        }
//        addressDao.deleteAddressByIdAndUid(addressId, uid);
//        return ServiceResponseMessage.createBySuccessCodeMessage("删除成功", "");
//    }
//
//    @Override
//    public ServiceResponseMessage fetchAllProfile() {
//        List<AccountPO> accountList = accountDao.selectAllAccountInfo();
//        List<UserProfileRsp> userProfileRspList = new ArrayList<>();
//        AllUserProfileRsp allUserProfileRsp = new AllUserProfileRsp();
//        accountList.forEach(accountInfo -> {
//            UserProfileRsp userProfileRsp = new UserProfileRsp();
//            BeanUtils.copyProperties(accountInfo, userProfileRsp);
//            userProfileRsp.setPassword(AESUtil.decryptAES(accountInfo.getSecretKey(),accountInfo.getPassword()));
//            userProfileRsp.setCoins(accountBookService.fetchAccountRemainingPointsByUid(accountInfo.getUid()));
//            userProfileRspList.add(userProfileRsp);
//        });
//        allUserProfileRsp.setUserProfileRspList(userProfileRspList);
//        return ServiceResponseMessage.createBySuccessCodeMessage("获取成功", allUserProfileRsp);
//    }
//
//    @Override
//    public ServiceResponseMessage modifyUserIdentityPromotion(int uid, String role) {
//        accountDao.updateProfileRoleByAccountUid(uid, role);
//        return ServiceResponseMessage.createBySuccessCodeMessage("变更用户权限成功");
//    }
//
//    @Override
//    public ServiceResponseMessage deleteAccountProfileByUid(Integer uid) {
//        accountDao.deleteAccountProfileByUid(uid);
//        return ServiceResponseMessage.createBySuccessCodeMessage("删除用户账号信息");
//    }
//
//    @Override
//    public ServiceResponseMessage modifyAccountPassword(ModifyAccountPasswordReq modifyAccountPasswordReq) {
//        if (ObjectUtil.isNotEmpty(modifyAccountPasswordReq.getUid())) {
//            AccountPO accountInfo = accountDao.selectAccountInfoByUid(modifyAccountPasswordReq.getUid());
//            if (null != accountInfo) {
//                String newPassword = AESUtil.encryptAES(accountInfo.getSecretKey(), modifyAccountPasswordReq.getPassword());
//                accountDao.updateAccountPasswordByUid(modifyAccountPasswordReq.getUid(), newPassword);
//                return ServiceResponseMessage.createBySuccessCodeMessage("更新成功");
//            }
//        }
//        return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.BAD_REQUEST, "更新失败");
//    }
}
