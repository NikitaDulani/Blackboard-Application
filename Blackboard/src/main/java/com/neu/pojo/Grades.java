package com.neu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grades_table")
public class Grades {
	
	@Column(name="grade")
	private float grade;
	
	@ManyToOne
	@JoinColumn(name="personID", nullable=false)
	private Person person;
	
//	@ManyToOne
//	@JoinColumn()
}
