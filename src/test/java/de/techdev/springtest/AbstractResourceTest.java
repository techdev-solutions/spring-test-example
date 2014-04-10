package de.techdev.springtest;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import java.util.function.Function;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

/**
 * Base class for tests for Spring-Data-Rest exported resources.
 *
 * @author Moritz Schulze
 */
@ContextConfiguration(classes = DataOnDemandConfiguration.class)
public abstract class AbstractResourceTest<T> extends MockMvcSecurityTest {

    @Autowired
    private CrudRepository<T, Long> repository;

    @Autowired
    private AbstractDataOnDemand<T> dataOnDemand;

    /**
     * @return The exported resource name, e.g. "employees" (from http://localhost:8080/employees).
     */
    protected abstract String getResourceName();

    /**
     * @param item The item to transform.
     * @return The JSON representation of one item, used in POST and PUT requests.
     */
    protected abstract String getJsonRepresentation(T item);

    @Before
    public void setUp() throws Exception {
        dataOnDemand.init();
    }

    /**
     * Access the root of a resource via GET.
     *
     * @param session The mock session to use, e.g. admin or employee
     * @return The result actions to perform further tests on.
     * @throws Exception
     */
    protected ResultActions rootWith(MockHttpSession session) throws Exception {
        return mockMvc.perform(
                get("/" + getResourceName())
                        .session(session)
        );
    }

    /**
     * Access a single random object via GET.
     *
     * @param session The mock session to use, e.g. admin or employee
     * @return The result actions to perform further tests on.
     * @throws Exception
     */
    protected ResultActions oneWith(MockHttpSession session) throws Exception {
        return oneWith((object) -> session);
    }

    /**
     * Access a single random object via GET.
     *
     * @param sessionProvider Converts the random object to a {@link org.springframework.mock.web.MockHttpSession}. This can be used to set the session to a specific employee.
     * @return The result actions to perform further tests on.
     * @throws Exception
     */
    protected ResultActions oneWith(Function<T, MockHttpSession> sessionProvider) throws Exception {
        T randomObject = dataOnDemand.getRandomObject();
        return oneUrlWith(sessionProvider.apply(randomObject), "/" + getResourceName() + "/" + dataOnDemand.getId(randomObject));
    }

    /**
     * Access a URL via GET.
     *
     * @param session The The mock session to use, e.g. admin or employee
     * @param url     The URL to access.
     * @return The result actions to perform further tests on.
     * @throws Exception
     */
    protected ResultActions oneUrlWith(MockHttpSession session, String url) throws Exception {
        return mockMvc.perform(
                get(url)
                        .session(session)
        );
    }

    /**
     * Get a new transient object and try to POST it to the resource path.
     *
     * @param session The mock session to use, e.g. admin or employee
     * @return The result actions to perform further tests on.
     * @throws Exception
     */
    protected ResultActions createWith(MockHttpSession session) throws Exception {
        return createWith((object) -> session);
    }

    /**
     * Get a new transient object and try to POST it to the resource path.
     *
     * @param sessionProvider Converts the random object to a {@link org.springframework.mock.web.MockHttpSession}. This can be used to set the session to a specific employee.
     * @return The result actions to perform further tests on.
     * @throws Exception
     */
    protected ResultActions createWith(Function<T, MockHttpSession> sessionProvider) throws Exception {
        T newObject = dataOnDemand.getNewTransientObject(500);
        return mockMvc.perform(
                post("/" + getResourceName() + "/")
                        .session(sessionProvider.apply(newObject))
                        .content(getJsonRepresentation(newObject))
        );
    }

    /**
     * Get a random object and try to PUT it to the resource path.
     *
     * @param session The mock session to use, e.g. admin or employee
     * @return The result actions to perform further tests on.
     * @throws Exception
     */
    protected ResultActions updateWith(MockHttpSession session) throws Exception {
        return updateWith((object) -> session);
    }

    /**
     * Get a random object and try to PUT it to the resource path
     *
     * @param sessionProvider Converts the random object to a {@link org.springframework.mock.web.MockHttpSession}. This can be used to set the session to a specific employee.
     * @return The result actions to perform further tests on.
     * @throws Exception
     */
    protected ResultActions updateWith(Function<T, MockHttpSession> sessionProvider) throws Exception {
        T randomObject = dataOnDemand.getRandomObject();
        return mockMvc.perform(
                put("/" + getResourceName() + "/" + dataOnDemand.getId(randomObject))
                        .session(sessionProvider.apply(randomObject))
                        .content(getJsonRepresentation(randomObject))
        );
    }

    /**
     * Get a random object and try to DELETE it.
     *
     * @param session The mock session to use, e.g. admin or employee
     * @return The result actions to perform further tests on.
     * @throws Exception
     */
    protected ResultActions removeWith(MockHttpSession session) throws Exception {
        return removeWith((object) -> session);
    }

    /**
     * Get a random object and try to DELETE it.
     *
     * @param sessionProvider Converts the random object to a {@link org.springframework.mock.web.MockHttpSession}. This can be used to set the session to a specific employee.
     * @return The result actions to perform further tests on.
     * @throws Exception
     */
    protected ResultActions removeWith(Function<T, MockHttpSession> sessionProvider) throws Exception {
        T randomObject = dataOnDemand.getRandomObject();
        return removeUrlWith(sessionProvider.apply(randomObject), "/" + getResourceName() + "/" + dataOnDemand.getId(randomObject));
    }

    /**
     * Perform a DELETE on a URL.
     *
     * @param session The mock session to use, e.g. admin or employee
     * @param url     The URL to access.
     * @return The result actions to perform further tests on.
     * @throws Exception
     */
    protected ResultActions removeUrlWith(MockHttpSession session, String url) throws Exception {
        return mockMvc.perform(
                delete(url)
                        .session(session)
        );
    }
}
