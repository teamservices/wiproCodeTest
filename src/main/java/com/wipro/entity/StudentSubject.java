package com.wipro.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Student_Subject")
public class StudentSubject implements Serializable {
	
	  /** The constant serialVersionUID. */
	  private static final long serialVersionUID = 7752179635872082313L;

	  /** The entity id */
	  @Id
	  @Column(name = "id", updatable = false, nullable = false, unique = true)
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	  
	  @Column(name = "subject_id", nullable = false)
	  private String subjectId;
	  @Column(name = "student_id", nullable = false)
	  private List<Integer> studentId;
	  @Column(name = "marks", nullable = false)
	  private Integer marks;

}
