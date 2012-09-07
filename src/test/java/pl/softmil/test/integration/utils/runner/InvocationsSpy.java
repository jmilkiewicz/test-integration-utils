package pl.softmil.test.integration.utils.runner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InvocationsSpy {
    private static boolean testRuleCalled;
    private static boolean classTestRuleCalled;

    private static boolean beforeClassCalled;
    private static boolean afterClassCalled;

    private static boolean beforeCalled;
    private static boolean afterCalled;

    private static boolean springListenerAfterClassCalled;
    private static boolean springListenerBeforeClassCalled;

    public static void testRuleCalled() {
        testRuleCalled = true;
        assertThat(springListenerBeforeClassCalled, is(true));
        assertThat(beforeClassCalled, is(true));
        assertThat(classTestRuleCalled, is(true));

        assertThat(springListenerAfterClassCalled, is(false));
        assertThat(afterClassCalled, is(false));
        assertThat(beforeCalled, is(false));
        assertThat(afterCalled, is(false));
    }

    public static void classTestRuleCalled() {
        classTestRuleCalled = true;
        assertThat(springListenerBeforeClassCalled, is(true));

        assertThat(testRuleCalled, is(false));
        assertThat(beforeClassCalled, is(false));
        assertThat(springListenerAfterClassCalled, is(false));
        assertThat(afterClassCalled, is(false));
        assertThat(beforeCalled, is(false));
        assertThat(afterCalled, is(false));
    }
    
    public static void assertForTestMethod() {
        assertThat(springListenerBeforeClassCalled, is(true));
        assertThat(classTestRuleCalled, is(true));
        assertThat(beforeClassCalled, is(true));
        assertThat(testRuleCalled, is(true));
        assertThat(beforeCalled, is(true));

        assertThat(afterCalled, is(false));
        assertThat(afterClassCalled, is(false));
        assertThat(springListenerAfterClassCalled, is(false));
    }

    public static void beforeClassCalled() {
        beforeClassCalled = true;
        assertThat(springListenerBeforeClassCalled, is(true));
        assertThat(classTestRuleCalled, is(true));

        assertThat(testRuleCalled, is(false));
        assertThat(springListenerAfterClassCalled, is(false));
        assertThat(afterClassCalled, is(false));
        assertThat(beforeCalled, is(false));
        assertThat(afterCalled, is(false));
    }

    public static void afterClassCalled() {
        afterClassCalled = true;
        assertThat(springListenerBeforeClassCalled, is(true));
        assertThat(classTestRuleCalled, is(true));
        assertThat(beforeClassCalled, is(true));
        assertThat(testRuleCalled, is(true));
        assertThat(beforeCalled, is(true));
        assertThat(afterCalled, is(true));

        assertThat(springListenerAfterClassCalled, is(false));
    }

    public static void beforeCalled() {
        beforeCalled = true;
        assertThat(springListenerBeforeClassCalled, is(true));
        assertThat(classTestRuleCalled, is(true));
        assertThat(beforeClassCalled, is(true));
        assertThat(testRuleCalled, is(true));

        assertThat(afterCalled, is(false));
        assertThat(afterClassCalled, is(false));
        assertThat(springListenerAfterClassCalled, is(false));
    }

    public static void afterCalled() {
        afterCalled = true;
        assertThat(springListenerBeforeClassCalled, is(true));
        assertThat(classTestRuleCalled, is(true));
        assertThat(beforeClassCalled, is(true));
        assertThat(testRuleCalled, is(true));
        assertThat(beforeCalled, is(true));
        assertThat(afterClassCalled, is(false));
        assertThat(springListenerAfterClassCalled, is(false));
    }

    public static void springListenerAfterClassMethodCalled() {
        springListenerAfterClassCalled = true;
        assertThat(springListenerBeforeClassCalled, is(true));
        assertThat(classTestRuleCalled, is(true));
        assertThat(beforeClassCalled, is(true));
        assertThat(testRuleCalled, is(true));
        assertThat(beforeCalled, is(true));
        assertThat(afterCalled, is(true));
        assertThat(afterClassCalled, is(true));

    }

    public static void springListenerBeforeClassMethodCalled() {
        springListenerBeforeClassCalled = true;

        assertThat(classTestRuleCalled, is(false));
        assertThat(beforeClassCalled, is(false));
        assertThat(testRuleCalled, is(false));
        assertThat(beforeCalled, is(false));
        assertThat(afterCalled, is(false));
        assertThat(afterClassCalled, is(false));
        assertThat(springListenerAfterClassCalled, is(false));
    }

}
