package cc.itsc.project.movie.review.backend.pojo.vo.req;

/**
 * @author Leonardo iWzl
 * @version 1.0
 * @date 2020/3/22 09:37
 */
public class GoodsDetailReq {
    private String goodsName;
    private int total;
    private int price;
    private String goodsUrl;
    private String goodsDescription;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
