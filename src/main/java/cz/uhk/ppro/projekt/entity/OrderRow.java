package cz.uhk.ppro.projekt.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_rows", schema = "cukrarna", catalog = "")
public class OrderRow {
    private int orderRowId;
    private int count;
    private int pricePerUnit;
    private int productId;
    private int orderId;

    public OrderRow() {
    }

    public OrderRow(int count, int pricePerUnit, int productId, int orderId) {
        this.count = count;
        this.pricePerUnit = pricePerUnit;
        this.productId = productId;
        this.orderId = orderId;
    }

    @Id
    @Column(name = "ORDER_ROW_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getOrderRowId() {
        return orderRowId;
    }

    public void setOrderRowId(int orderRowId) {
        this.orderRowId = orderRowId;
    }

    @Basic
    @Column(name = "COUNT", nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Basic
    @Column(name = "PRICE_PER_UNIT", nullable = false)
    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Basic
    @Column(name = "PRODUCT_ID", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    @Basic
    @Column(name = "ORDER_ID", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderRow orderRow = (OrderRow) o;

        if (orderRowId != orderRow.orderRowId) return false;
        if (count != orderRow.count) return false;
        if (pricePerUnit != orderRow.pricePerUnit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderRowId;
        result = 31 * result + count;
        result = 31 * result + pricePerUnit;
        return result;
    }
}