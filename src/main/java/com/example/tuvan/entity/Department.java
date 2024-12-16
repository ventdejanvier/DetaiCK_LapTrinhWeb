package com.example.tuvan.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Data
@Entity
@Table(name = "Department")
public class Department extends AbstractEntity {
    @Basic
    @Column(name = "name", nullable = true, length = 255, columnDefinition = "nvarchar(255)")
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Question> questions;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Consultant> consultants;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Set<Consultant> getConsultants() {
		return consultants;
	}

	public void setConsultants(Set<Consultant> consultants) {
		this.consultants = consultants;
	}
    
    

}
