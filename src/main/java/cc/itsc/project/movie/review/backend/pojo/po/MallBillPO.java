package cc.itsc.project.movie.review.backend.pojo.po;

public class MallBillPO {
    /**
     * 兑换的账单ID
     */
    private String id;

    /**
     * 账单所有人的ID
     */
    private Integer uid;

    /**
     * 账单兑换的商品ID
     */
    private Integer goodsId;

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
    private Integer operator;

    /**
     * 关联的地址ID
     */
    private Integer addressId;

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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

