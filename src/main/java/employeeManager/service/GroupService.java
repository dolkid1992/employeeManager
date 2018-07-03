package employeeManager.service;

import employeeManager.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    Iterable<Group> findAll();

    Group findById(Long id);

    void save(Group group);

    void delete(Long id);
}
