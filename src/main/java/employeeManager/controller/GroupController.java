package employeeManager.controller;

import employeeManager.model.Group;
import employeeManager.repository.GroupRepository;
import employeeManager.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("")
    public ModelAndView getAllGroup(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/group/list");
        Iterable<Group> groups = groupService.findAll();
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

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Group group = groupService.findById(id);
        if (group != null) {
            ModelAndView modelAndView = new ModelAndView("/group/edit");
            modelAndView.addObject("group", group);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateGroup(@ModelAttribute("group") Group group) {
        groupService.save(group);
        ModelAndView modelAndView = new ModelAndView("/group/edit");
        modelAndView.addObject("group", group);
        modelAndView.addObject("message", "Group has update successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Group group = groupService.findById(id);
        if (group != null) {
            ModelAndView modelAndView = new ModelAndView("/group/delete");
            modelAndView.addObject("group", group);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteGroup(@PathVariable("id") Long id) {
        Group group = groupService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        if (group != null) {
            groupService.delete(id);
        }
        modelAndView.setViewName("redirect:/groups");
        return modelAndView;
    }
}
