package com.micro.demo.Model;
/**
 * Classe pour représenter un user en bbd
 *
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
//@JsonFilter("filterByEmail")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  {
    @Id
    private Long licenseNumber;
    //@JsonIgnore
    private String firstName;
    //@JsonIgnore
    private String lastName;
    private Date birthDate;
    private String email;


    public Long getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Long licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
                "id=" + licenseNumber + "\n" +
                "nom=" + lastName + "\n" +
                "prenom=" + firstName + "\n" +
                "license_date=" + birthDate + "\n" +
                "email=" + email + "\n" +
                "}";
    }
}
