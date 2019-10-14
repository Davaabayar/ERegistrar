package edu.mum.cs.cs425.labs.eregistrar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.cs.cs425.labs.eregistrar.model.Student;
import edu.mum.cs.cs425.labs.eregistrar.service.StudentService;


@Controller
public class StudentController {
	
	//@Autowired
	private StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping(value = {"/eregistrar/students/list"})
	public ModelAndView listStudents(@RequestParam(defaultValue = "0") int pageno) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("students", studentService.getAllStudentsPaged(pageno));
		mav.addObject("currentPageNo", pageno);
		mav.setViewName("student/list");
		return mav;
	}
	
	@GetMapping(value = {"/eregistrar/student/new"})
	public String displayNewStudentForm(Model model) {
		model.addAttribute("student",new Student());
		return "student/new";
	}
	
	@PostMapping(value={"/eregistrar/student/new"})
	public String addNewStudent(@Valid @ModelAttribute("student") Student student,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("errors",bindingResult.getAllErrors());
			return "student/new";
		}
		student = studentService.saveStudent(student);
		return "redirect:/eregistrar/students/list";  
	}
	
	@GetMapping(value= {"/eregistrar/student/edit/{studentId}"})
	public String editStudent(@PathVariable Long studentId, Model model) {
		Student student = studentService.getStudentByStudentId(studentId);
		if(student!=null) {
			model.addAttribute("student", student);
			return "student/edit";
		}
		return "student/list";
	}
	
	@PostMapping(value= {"/eregistrar/student/edit"})
	public String updateStudent(@Valid @ModelAttribute("student") Student student, 
			BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());
			return "student/edit";
		}
		student = studentService.saveStudent(student);
		return "redirect:/eregistrar/students/list";
	}
	
	@GetMapping(value= {"/eregistrar/student/delete/{studentId}"})
	public String deleteStudent(@PathVariable Long studentId, Model model) {
		studentService.deleteStudentById(studentId);
		return "redirect:/eregistrar/students/list";
	}
}
