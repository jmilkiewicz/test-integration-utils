package pl.softmil.test.integration.utils.validation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.*;

import javax.validation.ConstraintViolation;

import org.hamcrest.Matchers;
import org.junit.Assert;

public class ConstraintViolationHelper {
    private Set<? extends ConstraintViolation<?>> constraintViolations;

    public static ConstraintViolationHelper build(
            Set<? extends ConstraintViolation<?>> constraintViolations) {
        return new ConstraintViolationHelper(constraintViolations);
    }

    private ConstraintViolationHelper(
            Set<? extends ConstraintViolation<?>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    public void assertGivenNumberViolationsRaised(org.hamcrest.Matcher<? super java.lang.Integer> sizeMatcher) {
        Assert.assertThat("incorrect number of violations raised",
                constraintViolations, hasSize(sizeMatcher));
    }

    public void assertAnyViolationsRaised() {
        Assert.assertThat("incorrect number of violations raised",
                constraintViolations, hasSize(greaterThan(0)));

    }

    public void assertNoViolationsRaised() {
        assertGivenNumberViolationsRaised(equalTo(0));

    }

    public void assertSpecificViolationRaised(
            ErrorMatcher.ViolationContextContainer violationContextContainer) {
        assertThat(
                new HashSet<ConstraintViolation<?>>(constraintViolations),
                Matchers.<ConstraintViolation<?>> hasItem(new Jsr303ConstraintViolationMatcher(
                        violationContextContainer)));
    }

    public void assertViolationForGivenPropertyRaised(String propertyName) {
        Set<String> constraintViolationsPropertyPathNames = getConstraintViolationsPropertyPathNames();
        Assert.assertThat("no validation errors raised for " + propertyName,
                constraintViolationsPropertyPathNames, hasItem(propertyName));
    }

    public void assertNoViolationForGivenPropertyRaised(String propertyName) {
        Set<String> constraintViolationsPropertyPathNames = getConstraintViolationsPropertyPathNames();
        Assert.assertThat("validation errors raised for " + propertyName,
                constraintViolationsPropertyPathNames,
                not(hasItem(propertyName)));
    }

    private Set<String> getConstraintViolationsPropertyPathNames() {
        Set<String> result = new HashSet<String>();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            result.add(constraintViolation.getPropertyPath().toString());
        }
        return result;
    }

}
