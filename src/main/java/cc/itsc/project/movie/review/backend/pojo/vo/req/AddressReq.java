package cc.itsc.project.movie.review.backend.pojo.vo.req;

/**
 * @author Leonardo iWzl
 * @version 1.0
 * @date 2020/3/19 22:07
 */
public class AddressReq {
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
}
