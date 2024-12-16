package com.example.tuvan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "Users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        },
        catalog = "")
@Data
public class User extends AbstractEntity {
    @NotBlank
    @Size(max = 50)
    private String email;

    @Size(max = 120)
    private String password;

    @Basic
    @Column(name = "phone_number", nullable = true, length = 45)
    private String phoneNumber;

    @OneToOne(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Customer customer;

    @OneToOne(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Consultant consultant;


    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roleEntities;


    @Basic
    @Column(name = "avatar", nullable = true, length = 255, columnDefinition = "nvarchar(255)")
    private String avatar;


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Consultant getConsultant() {
		return consultant;
	}


	public void setConsultant(Consultant consultant) {
		this.consultant = consultant;
	}


	public Set<Role> getRoleEntities() {
		return roleEntities;
	}


	public void setRoleEntities(Set<Role> roleEntities) {
		this.roleEntities = roleEntities;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
    
    

}
