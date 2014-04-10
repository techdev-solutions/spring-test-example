package de.techdev.springtest.domain;

import de.techdev.springtest.AbstractResourceTest;
import org.junit.Test;

import static de.techdev.springtest.DomainResourceTestMatchers.isAccessible;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Moritz Schulze
 */
public class EmployeeResourceTest extends AbstractResourceTest<Employee> {

    @Test
    public void root() throws Exception {
        assertThat(rootWith(admin()), isAccessible());
    }

    @Override
    protected String getResourceName() {
        return "employees";
    }

    @Override
    protected String getJsonRepresentation(Employee item) {
        return "";
    }
}
