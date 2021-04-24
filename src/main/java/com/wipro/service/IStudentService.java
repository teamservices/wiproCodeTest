package com.wipro.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.wipro.entity.Student;
import com.wipro.entity.Subject;
/**
 * The repository for student
 *
 * @author Amitk
 */
@Service
public interface IStudentService {

	public <S> S save(Student student) throws Exception;
	
	public Integer numbersMeetNumbers(List<Integer> marks)  throws Exception;;
	
	public Set<String> findDuplicates(List<Subject> subjects)  throws Exception;;
	
	public String whiteSpacesGalore(String address) throws Exception;

	public Student getStudentByIdFromList(List<Student> studentList, int id)  throws Exception;;

	public Optional<Student> findStudentById(int id)  throws Exception;;

	public List<Integer> findStudentMarksById(int id)  throws Exception;;
	
	public String findStudentAddressById(int id)  throws Exception;;
}
