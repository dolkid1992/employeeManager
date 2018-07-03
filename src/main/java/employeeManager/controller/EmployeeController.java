package employeeManager.controller;

import employeeManager.model.Employee;
import employeeManager.model.Group;
import employeeManager.service.EmployeeService;
import employeeManager.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private GroupService groupService;

    @ModelAttribute("groups")
    public Iterable<Group> groups(){
        return groupService.findAll();
    }

    @GetMapping("")
    public ModelAndView getAllEmployee(@RequestParam("s")Optional<String>s, Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/employee/list");
        Page<Employee> employees;
        if (s.isPresent()) {
            employees = employeeService.findAllByNameContains(s.get(), pageable);
        }else {
            employees = employeeService.findAll(pageable);
        }
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveEmployee (@ModelAttribute("employee") Employee employee){
        ModelAndView modelAndView = new ModelAndView("/employee/create");
        employeeService.save(employee);
        modelAndView.addObject("employee", new Employee());
        modelAndView.addObject("messenge", "new Employee has created successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/details")
    public ModelAndView detailsEmployee(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/employee/details");
        modelAndView.addObject("details", employee);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            ModelAndView modelAndView = new ModelAndView("/employee/edit");
            modelAndView.addObject("employee", employee);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("/employee/edit");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("message", "Employee has update successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            ModelAndView modelAndView = new ModelAndView("/employee/delete");
            modelAndView.addObject("employee", employee);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        if (employee != null) {
            employeeService.delete(id);
        }
        modelAndView.setViewName("redirect:/employees");
        return modelAndView;
    }
}
