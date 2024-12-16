package com.example.tuvan.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "Question")
public class Question extends AbstractEntity{
    @Basic
    @Column(length = 255,name = "title", nullable = true,  columnDefinition = "nvarchar(255)")
    private String title;

    @Basic
    @Column(name = "content", nullable = true, length = 255, columnDefinition = "nvarchar(255)")
    private String content;


    @ManyToOne
    @JoinColumn(name = "department_id")
    @EqualsAndHashCode.Exclude
    private Department department;

    @ManyToOne
    @JoinColumn(name = "consultant_id")
    @EqualsAndHashCode.Exclude
    private Consultant consultant;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @EqualsAndHashCode.Exclude
    private Customer customer;

    private String reply;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Consultant getConsultant() {
		return consultant;
	}

	public void setConsultant(Consultant consultant) {
		this.consultant = consultant;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
    
    

}
