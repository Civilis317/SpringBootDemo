package org.boip.demo.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "demo_detail")
public class DetailEntity {

    @Id
    @SequenceGenerator(name="id_seq", sequenceName = "dts_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Long id;

    @Column(name="code", nullable = false)
    private String code;

    @Column(name="name")
    private String description;

    @Column(name="piece_price", nullable = false)
    private Double price;

    @Column(name="amount", nullable = false)
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "odr_id")
    private OrderEntity orderEntity;

    @Override
    public String toString() {
        return "DetailEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
