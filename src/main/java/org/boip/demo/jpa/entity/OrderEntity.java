package org.boip.demo.jpa.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "demo_order")
public class OrderEntity {

    @Id
    @SequenceGenerator(name="id_seq", sequenceName = "odr_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="order_date", nullable = false)
    private Date orderDate;

    @Column(name="employee")
    private String employee;

    @Column(name="customer")
    private String customer;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<DetailEntity> detailList = new ArrayList<>(128);

    public void add(DetailEntity detailEntity) {
        if(detailEntity != null) {
            this.detailList.add(detailEntity);
            detailEntity.setOrderEntity(this);
        }
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<DetailEntity> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailEntity> detailList) {
        this.detailList = detailList;
    }
}
