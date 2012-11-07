package pl.softmil.test.integration.utils.context.support;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;

import pl.softmil.test.integration.utils.runner.RunSpringAroundClassRulesJUnit4ClassRunner;

@RunWith(RunSpringAroundClassRulesJUnit4ClassRunner.class)
@ContextConfiguration({ "/*.xml" })
@TestExecutionListeners({ ExposeTestContextTestExecutionListener.class})
public class ExposeTestContextTestExecutionListenerTest {
	private static class AssertTestContextExposedRule extends TestWatcher {

		@Override
		protected void starting(Description description) {
			assertTestContextExposed();
		}

		@Override
		protected void finished(Description description) {
			assertTestContextExposed();
		}

		private void assertTestContextExposed() {
			TestContext testContext = ExposeTestContextTestExecutionListener
					.getTestContext();
			assertNotNull(" test context can not be null", testContext);
		}
	}

	@Rule
	public AssertTestContextExposedRule propertyRule = new AssertTestContextExposedRule();

	@Rule
	public static AssertTestContextExposedRule classRule = new AssertTestContextExposedRule();

	@Test
	public void test() {
		assertTrue(true);
	}

}
