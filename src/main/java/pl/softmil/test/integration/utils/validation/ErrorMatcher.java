package pl.softmil.test.integration.utils.validation;

import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public abstract class ErrorMatcher<T> extends TypeSafeDiagnosingMatcher<T> {
    public static class ViolationContextContainer {
        public final String propertyName;
        public final Object invalidValue;
        public final String messageTemplate;

        public ViolationContextContainer(String propertyName,
                Object invalidValue, String messageTemplate) {
            super();
            this.propertyName = propertyName;
            this.invalidValue = invalidValue;
            this.messageTemplate = messageTemplate;
        }
    }

    protected final Matcher<? super String> propertyNameMatcher;
    protected final Matcher<? super Object> propertyValueMatcher;
    protected final Matcher<? super String> messageTemplateMatcher;

    public ErrorMatcher(
            ErrorMatcher.ViolationContextContainer violationContextContainer) {
        this.propertyNameMatcher = Matchers
                .is(equalTo(violationContextContainer.propertyName));
        this.propertyValueMatcher = Matchers
                .is(violationContextContainer.invalidValue);
        this.messageTemplateMatcher = Matchers
                .is(equalTo(violationContextContainer.messageTemplate));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("{ propertyName ")
                .appendDescriptionOf(propertyNameMatcher)
                .appendText(", invalidValue ")
                .appendDescriptionOf(propertyValueMatcher)
                .appendText(", messageTemplate ")
                .appendDescriptionOf(messageTemplateMatcher).appendText("}");
    }

    protected void reportMismatch(String name, Matcher<?> matcher, Object item,
            Description mismatchDescription) {

        mismatchDescription.appendText(name).appendText(" ");
        matcher.describeMismatch(item, mismatchDescription);
    }

}
