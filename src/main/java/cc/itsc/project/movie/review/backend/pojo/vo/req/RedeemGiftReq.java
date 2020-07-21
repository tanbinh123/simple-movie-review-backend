package cc.itsc.project.movie.review.backend.pojo.vo.req;

public class RedeemGiftReq {
    private int goodsId;
    private int addressId;
    private String lanaId;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getLanaId() {
        return lanaId;
    }

    public void setLanaId(String lanaId) {
        this.lanaId = lanaId;
    }
}
