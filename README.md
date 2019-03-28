
#### testing overview

[The Practical Testing Pyramid](https://martinfowler.com/articles/practical-test-pyramid.html)

Testing Pyramid
1. UI Tests
1. Service Tests
1. Unit Tests

![](pyramid.jpg)
<br />
[Mike Cohn, Succeeding with Agile](https://www.amazon.com/Succeeding-Agile-Development-Addison-Wesley-Signature-ebook/dp/B002TIOYWQ)

+ simple approach, only use as a rule of thumb
+ dont get attached to each tier's name 
+ the two most important takeaways
    - write tests with different granularity
    - the more high-level you get the fewer tests you should have
+ ui vs e2e can be used interchangeable ...
    - testing an application end to end often means driving your tests through your user interface however the inverse is not always true

#### specifics

1. test structure
    + set up test data
    + call method under test
    + assert expectations
    + [3A](https://xp123.com/articles/3a-arrange-act-assert/)
    + [Given, When, Then](https://martinfowler.com/bliki/GivenWhenThen.html)
1. unit tests
    + quickest feedback loop
    + number of these tests should outweigh all other tests
    + single function/entire class
    + solitary unit tests v sociable unit tests
        - mocked/stubbed collaborators or not
        - slowness of the collaborator often determines when mocs/stubs are used
    + [Test Doubles](https://martinfowler.com/bliki/TestDouble.html)
        - dummy: objects are passed but not used; fill parameter lists
        - fake: objects with working implementations; some sort of shortcut; i.e. `InMemoryTestDatabase`
        - stub: provide canned responses; only exist in tests
        - spies: stubs that record information on how it was invoked
        - mocks: pre-programmed with expectations to respond under specific circumstances
    + one test class per production class
    + at least test the public interface of the class 
    + ensure all non trivial code paths are covered
    + testing getters and setters is dumb
1. integration tests
    + fewer tests that have a longer feedback loop
    + test the integration with all of the parts that live outside of your application
    + test all pieces of code where you either serialize/deserialize data
        - calls to REST API
        - reading from and writing to databases
        - call other application's API's
        - reading from and writing to queues
        - writing to the filesystem
    + tools for fakes
        - [h2](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-embedded-database-support)
        - [WireMock](http://wiremock.org/)
1. contract tests
    + provider + consumer messaging agreement tests (HTTP, gRPC, ?)
        - verify message structure
        - verify expected data types and/or values
    + shared CI/CD environment to have visibility in breaking changes b/w teams
 
1. terminology
    + varies by team; agree as a team and be clear

1. clean test code
     + test code == production code, dont skimp
     + test one condition per test
     + use mnemonics from [here](https://xp123.com/articles/3a-arrange-act-assert/) or [here](https://martinfowler.com/bliki/GivenWhenThen.html)
     + readability matters, you wont remember the clever thing you did
     + use the [rules of three](https://blog.codinghorror.com/rule-of-three/) when deciding to refactor
        - it is three times as difficult to build reusable components as single use components
        - a reusable component should be tried out in three different applications before it will be sufficiently general to accept into a reuse library

## fast feedback


#### tdd

1. Three Laws of TDD
    + you are not allowed to write any production code unless it is to make a failing unit test pass.
    + you are not allowed to write any more of a unit test than is sufficient to fail.
    + you are not allowed to write any more production code than is sufficient to pass the one failing test.

1. [The bad parts](https://www.youtube.com/watch?v=xPL84vvLwXA)
    + why tdd?
        - go fast forever
        - clean code
        - refactoring
        - confidence
    + problems?
        - outside in bdd (acceptance -> controller -> model)
            + slow
            + flaky
            + brittle
            + coupled
            - outside != gui
                + start at the outside of what you want to discover
        - mocking
            + you dont have to mock everything
            + accurately testing business logic > speed
            + [The Little Mocker](https://blog.cleancoder.com/uncle-bob/2014/05/14/TheLittleMocker.html)
            + [SOLID](https://hackernoon.com/solid-principles-made-easy-67b1246bcdf)
        - unit testing
            + common themes
                - every class should be paired with a well-designed unit test
                - every public method of every class should be paired with a well designed unit test
            + test coupling
                - refactoring into different design patterns will make these types of 
                tests tightly coupled to their implementation class
            + instead, every __behavior__ should be paired with a well designed unit test
        - code coverage
            + easy to cheat this system when developers do not buy in to its application
            + having a more relaxed approach tends to get to good coverage

    + [What is a Unit Test?](https://content.pivotal.io/blog/what-is-a-unit-test-the-answer-might-surprise-you)
