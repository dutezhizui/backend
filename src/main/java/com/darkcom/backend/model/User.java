package com.darkcom.backend.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
public class User extends AbstractPersistable<Long> {

    @Column(unique = true, length = 20, nullable = false, columnDefinition = "varchar(20) comment '账号'")
    private String account;
    @Column(length = 15, columnDefinition = "varchar(20) comment '手机号'")
    private String phone;
    @Column(length = 20, columnDefinition = "varchar(20) comment '姓名'")
    private String name;
    @Column(length = 256, nullable = false, columnDefinition = "varchar(256) comment '密码'")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
