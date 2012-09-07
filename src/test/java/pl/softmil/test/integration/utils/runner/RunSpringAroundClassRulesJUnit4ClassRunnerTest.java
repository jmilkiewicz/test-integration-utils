package pl.softmil.test.integration.utils.runner;

import org.junit.*;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.springframework.test.context.*;
@RunWith(RunSpringAroundClassRulesJUnit4ClassRunner.class)
@ContextConfiguration({ "/*.xml"})
@TestExecutionListeners({
    SpyCallTestExecutionListener.class })
public class RunSpringAroundClassRulesJUnit4ClassRunnerTest {
    @Rule
    public ExternalResource testWatcher = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
              InvocationsSpy.testRuleCalled();
        }
    };
    
    @ClassRule
    public static ExternalResource statictestWatcher = new ExternalResource(){

        @Override
        protected void before() throws Throwable {
              InvocationsSpy.classTestRuleCalled();
        }
    };

    
    @BeforeClass
    public static void beforeClass(){
        InvocationsSpy.beforeClassCalled();
    }
    
    @AfterClass
    public static void afterClass(){
        InvocationsSpy.afterClassCalled();
    }
    
    @Before
    public void before(){
        InvocationsSpy.beforeCalled();
    }
    
    @After
    public void after(){
        InvocationsSpy.afterCalled();
    }

    @Test
    public void tests(){
        InvocationsSpy.assertForTestMethod();
    }

}
