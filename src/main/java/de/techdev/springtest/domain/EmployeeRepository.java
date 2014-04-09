package de.techdev.springtest.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author Moritz Schulze
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @Override
    Page<Employee> findAll(Pageable pageable);
}
