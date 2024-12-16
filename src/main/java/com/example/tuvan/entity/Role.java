package com.example.tuvan.entity;

import com.example.tuvan.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
@Getter
@Entity
@Table(name = "Roles")
public class Role extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 255, unique = true, columnDefinition = "nvarchar(255)")
    private RoleEnum name;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "roleEntities")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<User> userEntities;

	public RoleEnum getName() {
		return name;
	}

	public void setName(RoleEnum name) {
		this.name = name;
	}

	public Set<User> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(Set<User> userEntities) {
		this.userEntities = userEntities;
	}
    
    

}
