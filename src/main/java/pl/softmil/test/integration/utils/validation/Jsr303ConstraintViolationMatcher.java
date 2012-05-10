package pl.softmil.test.integration.utils.validation;

import javax.validation.ConstraintViolation;

import org.hamcrest.Description;

public class Jsr303ConstraintViolationMatcher extends
        ErrorMatcher<ConstraintViolation<?>> {

    public Jsr303ConstraintViolationMatcher(
            pl.softmil.test.integration.utils.validation.ErrorMatcher.ViolationContextContainer violationContextContainer) {
        super(violationContextContainer);
    }

    @Override
    protected boolean matchesSafely(ConstraintViolation<?> item,
            Description mismatchDescription) {
        boolean matches = true;
        mismatchDescription.appendText("{");
        if (!propertyNameMatcher.matches(item.getPropertyPath().toString())) {
            matches = false;
            reportMismatch("propertyName", propertyNameMatcher, item
                    .getPropertyPath().toString(), mismatchDescription);
        }
        if (!propertyValueMatcher.matches(item.getInvalidValue())) {
            matches = false;
            reportMismatch("invalidValue", propertyValueMatcher,
                    item.getInvalidValue(), mismatchDescription);
        }
        if (!messageTemplateMatcher.matches(item.getMessageTemplate())) {
            matches = false;
            reportMismatch("messageTemplate", messageTemplateMatcher,
                    item.getMessageTemplate(), mismatchDescription);
        }
        mismatchDescription.appendText("}");
        return matches;
    }

}
