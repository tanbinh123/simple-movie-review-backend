package cc.itsc.project.movie.review.backend.pojo.vo.rsp;

import java.util.List;

public class AllUserProfileRsp {
    List<UserProfileRsp> userProfileRspList;

    public List<UserProfileRsp> getUserProfileRspList() {
        return userProfileRspList;
    }

    public void setUserProfileRspList(List<UserProfileRsp> userProfileRspList) {
        this.userProfileRspList = userProfileRspList;
    }
}
