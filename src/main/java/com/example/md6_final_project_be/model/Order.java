package com.example.md6_final_project_be.model;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private Date createDate;

    @OneToOne
    private OrderDetail orderDetail;

    public Order() {
    }

    public Order(Long id, User user, Date createDate) {
        this.id = id;
        this.user = user;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
