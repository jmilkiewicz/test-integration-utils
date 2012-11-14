package pl.softmil.test.integration.utils.rules;

import java.util.LinkedList;
import java.util.List;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RuleChainBean implements TestRule {
	private List<TestRule> rules = new LinkedList<TestRule>();

	public List<TestRule> getRules() {
		return rules;
	}

	public void setRules(List<TestRule> rules) {
		this.rules = rules;
	}

	public Statement apply(Statement base, Description description) {
		for (TestRule each : rules)
			base = each.apply(base, description);
		return base;
	}

}
