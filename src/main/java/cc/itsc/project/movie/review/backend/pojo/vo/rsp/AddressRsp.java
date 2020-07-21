package cc.itsc.project.movie.review.backend.pojo.vo.rsp;

import java.util.List;

/**
 * @author Leonardo iWzl
 * @version 1.0
 * @date 2020/3/17 23:59
 */
public class AddressRsp {
    private List<Address>  addressList;

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
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

}
