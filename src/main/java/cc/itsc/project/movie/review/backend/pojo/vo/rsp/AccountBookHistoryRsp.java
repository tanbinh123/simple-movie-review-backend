package cc.itsc.project.movie.review.backend.pojo.vo.rsp;

import java.util.List;

public class AccountBookHistoryRsp {
    List<AccountBook> accountBooks;

    public List<AccountBook> getAccountBooks() {
        return accountBooks;
    }

    public void setAccountBooks(List<AccountBook> accountBooks) {
        this.accountBooks = accountBooks;
    }

    public static class AccountBook{
        /**
         * 账单ID
         */
        private String lanaId;

        /**
         * 账单所有人UID
         */
        private Integer uid;

        /**
         * 账单类型
         */
        private String type;

        /**
         * 数量
         */
        private Integer count;

        /**
         * 标记
         */
        private String remark;

        /**
         * 创建时间
         */
        private Long createTime;

        /**
         * 默认状态
         */
        private Integer status;

        public String getLanaId() {
            return lanaId;
        }

        public void setLanaId(String lanaId) {
            this.lanaId = lanaId;
        }

        public Integer getUid() {
            return uid;
        }

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }

}

