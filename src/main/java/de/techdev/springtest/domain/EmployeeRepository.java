package de.techdev.springtest.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author Moritz Schulze
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    Page<Employee> findAll(Pageable pageable);

    @PreAuthorize("hasRole('ROLE_ADMIN') or ( isAuthenticated() and #id == principal.id )")
    @Override
    //Caution: The parameter name *must* be id, otherwise the PreAuthorize can fail.
    Employee findOne(Long id);

    @PostAuthorize("hasRole('ROLE_ADMIN') or ( returnObject != null && (isAuthenticated() and returnObject.id == principal.id) )")
    Employee findByName(String name);

    @RestResource(exported = false)
    @Override
    void delete(Long aLong);
}
