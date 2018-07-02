package employeeManager.service;

import employeeManager.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    Page<Group> findAll(Pageable pageable);

    Group findById(Long id);

    void save(Group group);

    void delete(Long id);
}
