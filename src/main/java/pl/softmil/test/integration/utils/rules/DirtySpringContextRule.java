package pl.softmil.test.integration.utils.rules;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import pl.softmil.test.integration.utils.context.support.ExposeTestContextTestExecutionListener;

public class DirtySpringContextRule extends TestWatcher {
	private static class MyDirtiesContextTestExecutionListener extends DirtiesContextTestExecutionListener{
		@Override
		public void dirtyContext(TestContext testContext) {
			super.dirtyContext(testContext);
		}
	}
	
	@Override
	protected void finished(Description description) {
		TestContext testContext = ExposeTestContextTestExecutionListener.getTestContext();
		dirtyContext(testContext);
	}

	private void dirtyContext(TestContext testContext) {
		MyDirtiesContextTestExecutionListener myDirtiesContextTestExecutionListener = new MyDirtiesContextTestExecutionListener();
		myDirtiesContextTestExecutionListener.dirtyContext(testContext);
	}

}
