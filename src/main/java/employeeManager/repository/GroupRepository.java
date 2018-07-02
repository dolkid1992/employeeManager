package employeeManager.repository;

import employeeManager.model.Group;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {
}
