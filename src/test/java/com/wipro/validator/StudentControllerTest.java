package com.wipro.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wipro.controller.StudentController;
import com.wipro.entity.Student;
import com.wipro.entity.Subject;
import com.wipro.service.IStudentService;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

	@InjectMocks
	private StudentController studentControllerMock;

	@Mock
	private IStudentService studentServiceMock;
	
	private static final int STUDENT_ID = 1;

	private List<Student> studentsList;
	

	@Before
	public void setup() throws Exception {
		// GIVEN
		int studentId = 1;
		List<Subject> subjectsList = new ArrayList<>();
		Subject subject1 = new Subject();
		subject1.setSubjectId(1);
		subject1.setSubjectName("Enlgish");
		Subject subject2 = new Subject();
		subject2.setSubjectId(2);
		subject2.setSubjectName("Maths");
		subjectsList.add(subject1);
		subjectsList.add(subject2);

		studentsList = new ArrayList<>();
		Student student = new Student();
		student.setId(1);
		student.setName("Amit");
		student.setSubjects(subjectsList);

		// WHEN
		Mockito.when(studentServiceMock.getStudentByIdFromList(Matchers.anyListOf(Student.class), Matchers.anyInt()))
				.thenReturn(student);
		Mockito.when(studentServiceMock.save(Matchers.any(Student.class))).thenReturn(student);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void saveStudentDataPassTest() throws Exception {
		// GIVEN
		// setup()

		// WHEN
		ResponseEntity<Student> response = studentControllerMock.saveStudentData(STUDENT_ID, studentsList);

		// THEN
		Mockito.verify(studentServiceMock, Mockito.times(1)).getStudentByIdFromList(Matchers.anyListOf(Student.class),
				Matchers.anyInt());
		Mockito.verify(studentServiceMock, Mockito.times(1)).save(Matchers.any(Student.class));
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void saveStudentDataExceptionTest() throws Exception {
		// GIVEN
		// setup()

		// WHEN
		ResponseEntity<Student> response = studentControllerMock.saveStudentData(STUDENT_ID, studentsList);

		// THEN
		Mockito.verify(studentServiceMock, Mockito.times(1)).getStudentByIdFromList(Matchers.anyListOf(Student.class),
				Matchers.anyInt());
		Mockito.verify(studentServiceMock, Mockito.times(1)).save(Matchers.any(Student.class));
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
}
