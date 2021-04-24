package com.wipro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wipro.entity.Student;

/**
 * The repository for student
 *
 * @author Amitk
 */
@Repository
public interface IStudentDao extends JpaRepository<Student, Integer>{

	/**
	 * This method gets student address by student id
	 *
	 * @param studentId   studentId
	 * @return String address string.
	 */
	@Query("select t.address from Student t where t.studentId = :studentId")
	public String getAddressOfStudent(int studentId);

}
