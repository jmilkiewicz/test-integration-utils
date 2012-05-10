package pl.softmil.test.integration.utils.validation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

import java.util.HashSet;
import java.util.Set;

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

    public void assertGivenNumberViolationsRaised(int numberOfViolationsRaised) {
        Assert.assertThat("incorrect number of violations raised",
                constraintViolations, hasSize(numberOfViolationsRaised));
    }

    public void assertAnyViolationsRaised() {
        Assert.assertThat("incorrect number of violations raised",
                constraintViolations, hasSize(greaterThan(0)));

    }

    public void assertNoViolationsRaised() {
        assertGivenNumberViolationsRaised(0);

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
