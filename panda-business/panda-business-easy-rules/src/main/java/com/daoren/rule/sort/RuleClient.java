package com.daoren.rule.sort;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.YamlRuleDefinitionReader;

import java.io.FileReader;

public class RuleClient {
    public static void main(String[] args) throws Exception {
        RulesEngine rulesEngine = new DefaultRulesEngine();
        Rules rules = new MVELRuleFactory(new YamlRuleDefinitionReader()).createRules(new FileReader("D:\\Projects\\Custom\\panda\\panda-business\\panda-business-easy-rules\\src\\main\\resources\\asj-group.yaml"));
        Facts facts = new Facts();
        for (int i = 1; i <= 20; i++) {
            System.out.println("====== " + i + " ======");
            facts.put("ajywztdm", i);
            rulesEngine.fire(rules, facts);
        }
    }
}
