package com.wipro.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.constants.ControllerConstants;
import com.wipro.entity.Student;
import com.wipro.service.IStudentService;
import com.wipro.service.StudentServiceImpl;

import lombok.extern.slf4j.Slf4j;
/**
 * The controller for student
 *
 * @author Amitk
 */
@Slf4j
@RestController
@RequestMapping(ControllerConstants.CONTROLLER_REQUEST_PATH)
public class StudentController {

	private static Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	IStudentService studentService;

	/**
	 * This method gets student by id from request and saves in db
	 *
	 * @param studentId   studentId
	 * @param studentList the list of students
	 * @return ResponseEntity with related status codes.
	 */
	@PostMapping(value = ControllerConstants.CONTROLLER_SAVE_REQUEST_PATH, consumes = ControllerConstants.APPLICATION_JSON)
	public ResponseEntity<Student> saveStudentData(@PathVariable(ControllerConstants.STUDENT_ID) int studentId,
			@RequestBody List<Student> studentList) {

		try {
			Student student = studentService.getStudentByIdFromList(studentList, studentId);
			student = studentService.save(student);
			if (student != null) {
				return new ResponseEntity<Student>(HttpStatus.OK);
			} else {
				// can define custom error codes
				return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// can define custom error codes
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method is to use utility APIs
	 *
	 * @param utilityName   name of utility to be used
	 * @param studentId the id of student.
	 * @return ResponseEntity with related status codes.
	 */
	@GetMapping(value = ControllerConstants.UTILITIES)
	public ResponseEntity<Student> saveDataUsingUtilies(
			@PathVariable(ControllerConstants.UTILITY_NAME) String utilityName,
			@PathVariable(ControllerConstants.STUDENT_ID) int studentId) {
		 ResponseEntity<Student> reponse = null;
		try {
			if (!StringUtils.isEmpty(utilityName)
					&& ControllerConstants.FIND_DUPLICATES.equalsIgnoreCase(utilityName)) {
				Student student = studentService.findStudentById(studentId).orElse(null);
				if (student != null && student.getSubjects().size() > 0) {
					studentService.findDuplicates(student.getSubjects());
					reponse = new ResponseEntity<Student>(HttpStatus.OK);
				}
			} else if (!StringUtils.isEmpty(utilityName)
					&& ControllerConstants.LARGEST_NUMBER.equalsIgnoreCase(utilityName)) {
				List<Integer> studentMarks = studentService.findStudentMarksById(studentId);
				if (studentMarks != null && studentMarks.size() > 0) {
					studentService.numbersMeetNumbers(studentMarks);
					reponse = new ResponseEntity<Student>(HttpStatus.OK);
				}
			} else if (!StringUtils.isEmpty(utilityName)
					&& ControllerConstants.REMOVE_WHITESPACE.equalsIgnoreCase(utilityName)) {
				String address = studentService.findStudentAddressById(studentId);
				studentService.whiteSpacesGalore(address);
				reponse = new ResponseEntity<Student>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			// can define custom error codes
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
