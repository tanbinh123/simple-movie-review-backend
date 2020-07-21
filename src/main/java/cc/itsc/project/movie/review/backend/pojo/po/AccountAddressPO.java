package cc.itsc.project.movie.review.backend.pojo.po;

/**
 * @author Leonardo iWzl
 * @version 1.0
 * @date 2020/3/17 23:59
 */
public class AccountAddressPO {
    /**
     * 用户绑定地址相关信息
     */
    private Integer id;

    /**
     * 用户UID
     */
    private Integer uid;

    /**
     * 收件人名
     */
    private String name;

    /**
     * 收件人手机号码
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
     * 默认状态
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
