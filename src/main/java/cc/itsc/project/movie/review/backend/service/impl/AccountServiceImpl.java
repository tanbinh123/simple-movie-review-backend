package cc.itsc.project.movie.review.backend.service.impl;

import cc.itsc.project.movie.review.backend.config.BackendProfileConfig;
import cc.itsc.project.movie.review.backend.dao.AccountDao;
import cc.itsc.project.movie.review.backend.exception.AccountAlwaysExistException;
import cc.itsc.project.movie.review.backend.exception.AccountNotFoundException;
import cc.itsc.project.movie.review.backend.exception.AccountVerificationFailedException;
import cc.itsc.project.movie.review.backend.pojo.enums.RoleEnum;
import cc.itsc.project.movie.review.backend.pojo.po.AccountPO;
import cc.itsc.project.movie.review.backend.pojo.vo.req.ModifyAccountPasswordReq;
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
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

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

    @Override
    public boolean modifyAccountPassword(ModifyAccountPasswordReq modifyAccountPasswordReq) {
        if (ObjectUtil.isNotEmpty(modifyAccountPasswordReq.getUid())) {
            AccountPO accountInfo = accountDao.selectAccountInfoByUid(modifyAccountPasswordReq.getUid());
            if (null != accountInfo) {
                String newPassword = AESUtil.encryptAES(accountInfo.getSecretKey(), modifyAccountPasswordReq.getPassword());
                accountDao.updateAccountPasswordByUid(modifyAccountPasswordReq.getUid(), newPassword);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteAccountProfileByUid(Integer uid) {
        int row = accountDao.deleteAccountProfileByUid(uid);
        return row == BackendProfileConfig.ROW_NUMBER_1;
    }
}
