package de.techdev.springtest.domain;

import de.techdev.springtest.AbstractResourceTest;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import java.util.function.Function;

import static de.techdev.springtest.DomainResourceTestMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Moritz Schulze
 */
public class EmployeeResourceTest extends AbstractResourceTest<Employee> {

    private final Function<Employee, MockHttpSession> owningEmployee;
    private final Function<Employee, MockHttpSession> otherEmployee;

    public EmployeeResourceTest() {
        owningEmployee = employee -> employee(employee.getId());
        otherEmployee = employee -> employee(employee.getId() + 1);
    }

    @Test
    public void rootWithAdmin() throws Exception {
        assertThat(rootWith(admin()), isAccessible());
    }

    @Test
    public void rootWithEmployee() throws Exception {
        assertThat(rootWith(employee()), isForbidden());
    }

    @Test
    public void oneWithOwning() throws Exception {
        assertThat(oneWith(owningEmployee), isAccessible());
    }

    @Test
    public void oneWithOther() throws Exception {
        assertThat(oneWith(otherEmployee), isForbidden());
    }

    @Test
    public void createWithAdmin() throws Exception {
        assertThat(createWith(admin()), isCreated());
    }

    @Test
    public void createWithEmployee() throws Exception {
        assertThat(createWith(employee()), isForbidden());
    }

    @Test
    public void updateWithAdmin() throws Exception {
        assertThat(updateWith(admin()), isNoContent());
    }

    @Test
    public void updateWithEmployee() throws Exception {
        assertThat(updateWith(owningEmployee), isForbidden());
    }

    @Test
    public void deleteWithAdmin() throws Exception {
        assertThat(removeWith(admin()), isMethodNotAllowed());
    }

    @Override
    protected String getResourceName() {
        return "employees";
    }

    @Override
    protected String getJsonRepresentation(Employee item) {
        //In a real world example please don't use a string builder to generate JSON, use a library instead.
        StringBuilder sb = new StringBuilder();
        String quote = "\"";
        String colon = ":";
        sb.append("{");
        sb.append(quote).append("name").append(quote).append(colon).append(quote).append(item.getName()).append(quote);
        sb.append(",\"").append("salary").append(quote).append(colon).append(item.getSalary());
        if(item.getId() != null) {
            sb.append(",\"").append("id").append(quote).append(colon).append(item.getId());
        }
        sb.append("}");
        return sb.toString();
    }
}
