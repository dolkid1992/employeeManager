package employeeManager.service.impl;

import employeeManager.model.Group;
import employeeManager.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupService groupService;

    @Override
    public Page<Group> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Group findById(Long id) {
        return null;
    }

    @Override
    public void save(Group group) {

    }

    @Override
    public void delete(Long id) {

    }
}
