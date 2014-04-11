spring-example-test
===================
Small example application showing off how to implement tests for web security in a Spring-Data-Rest project (secured with Spring-Security) running
on Java 8. A DSL is implemented to allow tests like this

    @Test
    public void rootWithAdmin() throws Exception {
        assertThat(rootWith(admin()), isAccessible());
    }

    @Test
    public void createWithEmployee() throws Exception {
        assertThat(createWith(employee()), isForbidden());
    }

    @Test
    public void deleteWithAdmin() throws Exception {
        assertThat(removeWith(admin()), isMethodNotAllowed());
    }

Blog post: [blog.techdev.de](http://blog.techdev.de)

Requirements
------------
You need the JDK8 for compiling and a Java 8 compatible servlet container to run the WAR file.

Building
--------
After cloning run

    ./gradlew build

This will download Gradle locally, run the tests and build the WAR file into 'build/libs/spring-test-example-1.0.war'.
