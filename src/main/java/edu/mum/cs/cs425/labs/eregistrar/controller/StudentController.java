package edu.mum.cs.cs425.labs.eregistrar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
//		if(bindingResult.hasErrors()) {
//			model.addAttribute("errors",bindingResult.getAllErrors());
//			return "student/new";
//		}
		student = studentService.saveStudent(student);
		return "redirect:/eregistrar/students/list";
		
		
	}
}
