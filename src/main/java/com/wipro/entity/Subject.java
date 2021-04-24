package com.wipro.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Subject")
public class Subject implements Serializable {

	/** The constant serialVersionUID. */
	private static final long serialVersionUID = 7752179635872082313L;

	/** The entity id */
	@Id
	@Column(name = "subject_id", updatable = false, nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subjectId;
	@Column(name = "subject_name", nullable = false)
	private String subjectName;
	@ManyToMany(mappedBy = "student")
	private Set<Student> student = new HashSet<>();

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectId(int subjectId) {
      this.subjectId = subjectId;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
