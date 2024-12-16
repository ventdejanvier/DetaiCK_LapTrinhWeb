package com.example.tuvan.entity;

import com.example.tuvan.utils.DateUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Consultant",
        catalog = "")
@Data
public class Consultant extends AbstractEntity {
    @Basic
    @Column(name = "full_name", nullable = true, length = 255, columnDefinition = "nvarchar(255)")
    private String fullName;


    @Column(name = "address", nullable = true, length = 255, columnDefinition = "nvarchar(255)")
    private String address;

    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @Column(name = "dob", nullable = true)
    private LocalDate dob;

    @OneToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    private Department department;

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Question> questions;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
    
    
}
