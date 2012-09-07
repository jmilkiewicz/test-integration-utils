package pl.softmil.test.integration.utils.runner;

import java.util.List;

import org.junit.*;
import org.junit.internal.runners.statements.*;
import org.junit.rules.*;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.*;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.statements.*;

public class RunSpringAroundClassRulesJUnit4ClassRunner extends
        SpringJUnit4ClassRunner {

    public static class AroundTestClassCallbacks extends Statement {
        private final Statement statement;

        public AroundTestClassCallbacks(Statement next,
                TestContextManager testContextManager) {
            this.statement = new RunAfterTestClassCallbacks(
                    new RunBeforeTestClassCallbacks(next, testContextManager),
                    testContextManager);
        }

        @Override
        public void evaluate() throws Throwable {
            statement.evaluate();
        }

    }

    public RunSpringAroundClassRulesJUnit4ClassRunner(Class<?> clazz)
            throws InitializationError {
        super(clazz);
    }

    @Override
    protected Statement classBlock(final RunNotifier notifier) {
        Statement statement = childrenInvoker(notifier);
        statement = withBeforeClasses(statement);
        statement = withAfterClasses(statement);
        statement = withClassRules(statement);
        statement = withAroundTestClass(statement);
        return statement;
    }

    private Statement withAroundTestClass(Statement statement) {
        return new AroundTestClassCallbacks(statement, getTestContextManager());
    }

    @Override
    protected Statement withBeforeClasses(Statement statement) {
        List<FrameworkMethod> befores = getTestClass().getAnnotatedMethods(
                BeforeClass.class);
        return befores.isEmpty() ? statement : new RunBefores(statement,
                befores, null);
    }

    @Override
    protected Statement withAfterClasses(Statement statement) {
        List<FrameworkMethod> afters = getTestClass().getAnnotatedMethods(
                AfterClass.class);
        return afters.isEmpty() ? statement : new RunAfters(statement, afters,
                null);
    }

    private Statement withClassRules(Statement statement) {
        List<TestRule> classRules = classRules();
        return classRules.isEmpty() ? statement : new RunRules(statement,
                classRules, getDescription());
    }

}
