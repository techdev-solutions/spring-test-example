package de.techdev.springtest.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author Moritz Schulze
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    Page<Employee> findAll(Pageable pageable);

    @PreAuthorize("hasRole('ROLE_ADMIN') or ( isAuthenticated() and #employeeId == principal.id )")
    @Override
    Employee findOne(Long employeeId);

    @PostAuthorize("hasRole('ROLE_ADMIN') or ( returnObject != null && (isAuthenticated() and returnObject.id == principal.id) )")
    Employee findByName(String name);
}
