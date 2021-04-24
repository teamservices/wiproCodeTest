package com.wipro.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.dao.IStudentDao;
import com.wipro.dao.IStudentSubjectDao;
import com.wipro.entity.Student;
import com.wipro.entity.Subject;

import lombok.extern.slf4j.Slf4j;

/**
 * The repository for student
 *
 * @author Amitk
 */
@Slf4j
@Service
public class StudentServiceImpl implements IStudentService {

	private static Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	private IStudentDao iStudentDao;
	@Autowired
	private IStudentSubjectDao  iStudentSubjectDao;

	@Override
	public Student getStudentByIdFromList(List<Student> studentList, int id) {
		return (Student) studentList.stream().filter(s -> s.getId() == id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student save(Student student) throws Exception {
		try {
			return (Student) iStudentDao.save(student);
		} catch (Exception e) {
			log.error("Exception occurred in StudentServiceImpl.save" + e.getCause());
			throw new Exception();
		}
	}

	@Override
	public Integer numbersMeetNumbers(List<Integer> marks) throws Exception {
		try {
			return marks.stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new);
		} catch (Exception e) {
			log.error("Exception occurred in StudentServiceImpl.numbersMeetNumbers" + e.getCause());
			throw new Exception();
		}
	}

	@Override
	public Set<String> findDuplicates(List<Subject> subjects) throws Exception {
		Set<String> items = new HashSet<>();
		try {
			for(Subject subject : subjects) {
				if(!items.add(subject.getSubjectName())){
					items.add(subject.getSubjectName());
				}
			}
			return items;
		}catch(Exception e) {
			log.error("Exception occurred in StudentServiceImpl.findDuplicates" + e.getCause());
			throw new Exception();
		}
	}

	@Override
	public String whiteSpacesGalore(String address) throws Exception {
		StringBuffer addrBuff = new StringBuffer();
		try {
			char[] addrArr = address.toCharArray();

			for (int i = 0; i < addrArr.length; i++) {
				if ((addrArr[i] != ' ') && (addrArr[i] != '\t')) {
					addrBuff.append(addrArr[i]);
				}
			}
			return addrBuff.toString();
		} catch (Exception e) {
			log.error("Exception occurred in StudentServiceImpl.whiteSpacesGalore" + e.getCause());
			throw new Exception();
		}
	}

	@Override
	public Optional<Student> findStudentById(int id) throws Exception {
		try {
			return iStudentDao.findById(id);
		} catch (Exception e) {
			log.error("Exception occurred in StudentServiceImpl.whiteSpacesGalore" + e.getCause());
			throw new Exception();
		}
	}

	@Override
	public List<Integer> findStudentMarksById(int id) throws Exception {
		try {
			return iStudentSubjectDao.getMarksOfStudent(id);
		} catch (Exception e) {
			log.error("Exception occurred in StudentServiceImpl.whiteSpacesGalore" + e.getCause());
			throw new Exception();
		}
	}

	@Override
	public String findStudentAddressById(int id) throws Exception {
		try {
			return iStudentDao.getAddressOfStudent(id);
		} catch (Exception e) {
			log.error("Exception occurred in StudentServiceImpl.whiteSpacesGalore" + e.getCause());
			throw new Exception();
		}
	}

}
