package pl.softmil.test.integration.utils.runner;

import org.springframework.test.context.*;

public class SpyCallTestExecutionListener implements TestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        InvocationsSpy.springListenerBeforeClassMethodCalled();
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        InvocationsSpy.springListenerAfterClassMethodCalled();
    }

}
