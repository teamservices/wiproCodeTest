package com.wipro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wipro.entity.StudentSubject;
/**
 * The repository for student
 *
 * @author Amitk
 */
@Repository
public interface IStudentSubjectDao extends JpaRepository<StudentSubject, Integer>{

	/**
	 * This method gets marks of student from student_subject by student id
	 *
	 * @param studentId   studentId
	 * @return List<Integer> marks.
	 */
	@Query("select t.marks from StudentSubject t where t.studentId = :studentId")
	public List<Integer> getMarksOfStudent(int id);

}
