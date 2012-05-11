package pl.softmil.test.integration.utils.context.support;

import java.util.List;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runners.model.TestClass;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class DependencyInjectionJunitRulesTestExecutionListener extends
        AbstractTestExecutionListener {

    @Override
    public void prepareTestInstance(final TestContext testContext)
            throws Exception {
        Object testInstance = testContext.getTestInstance();
        List<TestRule> ruleFields = getRuleFields(testInstance);
        doInjection(testContext, ruleFields);
    }

    private void doInjection(final TestContext testContext,
            List<TestRule> ruleFields) {
        AutowireCapableBeanFactory beanFactory = testContext
                .getApplicationContext().getAutowireCapableBeanFactory();
        for (TestRule testRule : ruleFields) {
            beanFactory.autowireBeanProperties(testRule,
                    AutowireCapableBeanFactory.AUTOWIRE_NO, false);
            beanFactory.initializeBean(testRule, testContext.getTestClass()
                    .getName());
        }
    }

    private List<TestRule> getRuleFields(Object testInstance) {
        TestClass testClass = new TestClass(testInstance.getClass());
        return testClass.getAnnotatedFieldValues(testInstance, Rule.class,
                TestRule.class);
    }

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        List<TestRule> classRuleFields = getClassRuleFields(testContext);
        doInjection(testContext, classRuleFields);
    }

    private List<TestRule> getClassRuleFields(TestContext testContext) {
        TestClass testClass = new TestClass(testContext.getTestClass());
        return testClass.getAnnotatedFieldValues(null, ClassRule.class,
                TestRule.class);
    }
}
