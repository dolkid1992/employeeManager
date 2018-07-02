package employeeManager.controller;

import employeeManager.model.Group;
import employeeManager.repository.GroupRepository;
import employeeManager.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("")
    public ModelAndView getAllGroup(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/group/list");
        Page<Group> groups = groupService.findAll(pageable);
        modelAndView.addObject("groups", groups);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/group/create");
        modelAndView.addObject("group", new Group());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveGroup (@ModelAttribute("group") Group group){
        ModelAndView modelAndView = new ModelAndView("/group/create");
        groupService.save(group);
        modelAndView.addObject("group", new Group());
        modelAndView.addObject("messenge", "new Group has created successfully");
        return modelAndView;
    }

//    @GetMapping("/{id}/edit")
//    public ModelAndView showEditForm(@PathVariable Long id) {
//        Employee employee = employeeService.findById(id);
//        if (employee != null) {
//            ModelAndView modelAndView = new ModelAndView("/employee/edit");
//            modelAndView.addObject("employee", employee);
//            return modelAndView;
//        }else {
//            ModelAndView modelAndView = new ModelAndView("/error.404");
//            return modelAndView;
//        }
//    }
//
//    @PostMapping("/{id}/edit")
//    public ModelAndView updateEmployee(@ModelAttribute("employee") Employee employee) {
//        employeeService.save(employee);
//        ModelAndView modelAndView = new ModelAndView("/employee/edit");
//        modelAndView.addObject("employee", employee);
//        modelAndView.addObject("message", "Employee has update successfully");
//        return modelAndView;
//    }
//
//    @GetMapping("/{id}/delete")
//    public ModelAndView showDeleteForm(@PathVariable Long id) {
//        Employee employee = employeeService.findById(id);
//        if (employee != null) {
//            ModelAndView modelAndView = new ModelAndView("/employee/delete");
//            modelAndView.addObject("employee", employee);
//            return modelAndView;
//        } else {
//            ModelAndView modelAndView = new ModelAndView("/error.404");
//            return modelAndView;
//        }
//    }
//
//    @PostMapping("/{id}/delete")
//    public ModelAndView deleteEmployee(@PathVariable("id") Long id) {
//        Employee employee = employeeService.findById(id);
//        ModelAndView modelAndView = new ModelAndView();
//        if (employee != null) {
//            employeeService.delete(id);
//        }
//        modelAndView.setViewName("redirect:/employees");
//        return modelAndView;
//    }
}
