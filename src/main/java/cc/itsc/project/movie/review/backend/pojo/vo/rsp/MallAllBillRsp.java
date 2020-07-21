package cc.itsc.project.movie.review.backend.pojo.vo.rsp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MallAllBillRsp {
    List<Bill> bills;

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public static class Bill{
        /**
         * 兑换的账单ID
         */
        private String id;

        /**
         * 账单所有人的ID
         */
        private Integer uid;


        /**
         * 账单所有人的ID
         */
        private User userInfo;

        /**
         * 账单兑换的商品ID
         */
        private Goods goodsDetail;

        /**
         * 账单的创建时间
         */
        private Long createTime;

        /**
         * 账单的物流编号
         */
        private String trackId;

        /**
         * 账单的处理状态
         */
        private Integer billStatus;

        /**
         * 处理人UID
         */
        private User operatorInfo;

        /**
         * 关联的地址ID
         */
        private Address addressDetail;

        /**
         * 默认状态
         */
        private Integer status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getUid() {
            return uid;
        }

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public Goods getGoodsDetail() {
            return goodsDetail;
        }

        public void setGoodsDetail(Goods goodsDetail) {
            this.goodsDetail = goodsDetail;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public String getTrackId() {
            return trackId;
        }

        public void setTrackId(String trackId) {
            this.trackId = trackId;
        }

        public Integer getBillStatus() {
            return billStatus;
        }

        public void setBillStatus(Integer billStatus) {
            this.billStatus = billStatus;
        }

        public User getOperatorInfo() {
            return operatorInfo;
        }

        public void setOperatorInfo(User operatorInfo) {
            this.operatorInfo = operatorInfo;
        }

        public Address getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(Address addressDetail) {
            this.addressDetail = addressDetail;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public User getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(User userInfo) {
            this.userInfo = userInfo;
        }
    }


    public static class Address{
        /**
         * 地址ID
         */
        private int id;

        /**
         * 地址绑定人的uid
         */
        private int uid;

        /**
         * 收件人名称
         */
        private String name;

        /**
         * 收件人电话
         */
        private String phone;

        /**
         * 省
         */
        private String state;

        /**
         * 城市
         */
        private String city;

        /**
         * 行政街道
         */
        private String district;

        /**
         * 收件人门牌号
         */
        private String street;

        /**
         * 地址条数状态
         */
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }


    public static class User{
        private int id;
        private String nikeName;
        private String avatar;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String userName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNikeName() {
            return nikeName;
        }

        public void setNikeName(String nikeName) {
            this.nikeName = nikeName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }


    public static class Goods{
        private int id;
        private String goodsName;
        private int price;
        private String goodsUrl;
        private String goodsDescription;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getGoodsUrl() {
            return goodsUrl;
        }

        public void setGoodsUrl(String goodsUrl) {
            this.goodsUrl = goodsUrl;
        }

        public String getGoodsDescription() {
            return goodsDescription;
        }

        public void setGoodsDescription(String goodsDescription) {
            this.goodsDescription = goodsDescription;
        }


    }

}
