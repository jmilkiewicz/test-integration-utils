package pl.softmil.test.integration.utils.context.support;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class ExposeTestContextTestExecutionListener extends
		AbstractTestExecutionListener {
	private static ThreadLocal<TestContext> storage = new ThreadLocal<TestContext>();

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		storage.set(testContext);
	}
	
	public static TestContext getTestContext(){
		return storage.get();
	}
}
