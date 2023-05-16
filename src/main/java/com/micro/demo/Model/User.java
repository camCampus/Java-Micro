package com.micro.demo.Model;
/**
 * Classe pour représenter un user en bbd
 *
 */

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
@JsonFilter("filterByEmail")
public class User  {
    private Long license_number;
    //@JsonIgnore
    private String first_name;
    //@JsonIgnore
    private String last_name;
    private Date license_date;
    private String email;
    public User() {

    }

    public User(Long license_number, String first_name, String last_name, Date license_date, String email) {
        this.license_number = license_number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.license_date = license_date;
        this.email = email;
    }

    public Long getLicense_number() {
        return license_number;
    }

    public void setLicense_number(Long license_number) {
        this.license_number = license_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getLicense_date() {
        return license_date;
    }

    public void setLicense_date(Date license_date) {
        this.license_date = license_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User {" + "\n" +
                "id=" + license_number + "\n" +
                "nom=" + last_name + "\n" +
                "prenom=" + first_name + "\n" +
                "license_date=" + license_date + "\n" +
                "email=" + email + "\n" +
                "}";
    }
}
