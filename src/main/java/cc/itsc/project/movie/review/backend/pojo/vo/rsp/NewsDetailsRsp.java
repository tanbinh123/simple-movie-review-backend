package cc.itsc.project.movie.review.backend.pojo.vo.rsp;

import java.util.List;

/**
 * @author Leonardo iWzl
 * @version 1.0
 */
public class NewsDetailsRsp {

    private List<News> topNewsList;
    private List<News> newsList;

    public List<News> getTopNewsList() {
        return topNewsList;
    }

    public void setTopNewsList(List<News> topNewsList) {
        this.topNewsList = topNewsList;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public static class News{
        private int id;
        private String title;
        private int type;
        private String content;
        private String image;
        private int top;
        private long createTime;
        private UserProfileRsp userProfileRsp;
        private int status;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public UserProfileRsp getUserProfileRsp() {
            return userProfileRsp;
        }

        public void setUserProfileRsp(UserProfileRsp userProfileRsp) {
            this.userProfileRsp = userProfileRsp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
