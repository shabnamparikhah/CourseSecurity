package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;


    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String showRegisterationPage(Model model)

    {
        model.addAttribute("user",new User());
        return "registration";
    }
    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String processRegisterationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model)

    {
        model.addAttribute("user",new User());
        if (result.hasErrors())
        {
            return "registration";
        }
        else
        {
            userService.saveUser(user);
            model.addAttribute("message","User Account Successfully Created");
        }
        return "index";
    }
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/secure")
    public String secure(HttpServletRequest request, Authentication authentication, Principal principal){

        Boolean isAdmin = request.isUserInRole("ADMIN");
        Boolean isUser = request.isUserInRole("USER");
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        String username = principal.getName();
        return "secure";
    }

     @RequestMapping(value = "/addcourse" , method = RequestMethod.GET)
    public String addCourse(Model model)

     {
         model.addAttribute("course" , new Course());
         return "addcourse";
    }
    @RequestMapping(value = "/addcourse" , method = RequestMethod.POST)
      public String processForm(@Valid Course cource , BindingResult result)
    {
        if(result.hasErrors())
        {
            return "list";
        }
        courseRepository.save(cource);
        return "list";
    }

    @RequestMapping(value = "/addstudent" , method = RequestMethod.GET)
    public String addstudent(Model model)

    {
        model.addAttribute("student" , new Student());
        return "addstudent";
    }
    @RequestMapping(value = "/addstudent" , method = RequestMethod.POST)
    public String processFormstudent(@Valid Student student , BindingResult result)
    {
        if(result.hasErrors())
        {
            return "list";
        }
        studentRepository.save(student);
        return "studentlist";
    }

    @RequestMapping("/list")
     public String listCourses(@ModelAttribute Course course, Model model)
    {
        model.addAttribute("course",courseRepository.findAll());
        return "list";
    }
    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id , Model model)
    {
        model.addAttribute("course",courseRepository.findById(id).get());
        return  "show";
    }
    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id , Model model)
    {
        model.addAttribute("course",courseRepository.findById(id).get());
        return  "addcourse";
    }
    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id )
    {
        courseRepository.deleteById(id);
        return "list";
    }



}
