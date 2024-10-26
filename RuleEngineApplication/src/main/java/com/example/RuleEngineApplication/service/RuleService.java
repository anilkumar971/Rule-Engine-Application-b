package com.example.RuleEngineApplication.service;



import com.example.RuleEngineApplication.Model.ASTNode;
import com.example.RuleEngineApplication.Model.Rule;
import com.example.RuleEngineApplication.repo.RuleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private ObjectMapper objectMapper;  // Used for ASTNode <-> JSON conversion

    // 1. Create a rule and convert it to an ASTNode
    public ASTNode createRule(String ruleString) {
        ASTNode rootNode = parseRuleString(ruleString);

        Rule rule = new Rule();
        rule.setRuleString(ruleString);

        try {
            String astJson = objectMapper.writeValueAsString(rootNode);
            rule.setAstJson(astJson);
            ruleRepository.save(rule);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save rule", e);
        }

        return rootNode;
    }

    // 2. Combine multiple rules into a single ASTNode
    public ASTNode combineRules(String[] rules) {
        ASTNode combinedNode = new ASTNode("operator", null, null, "OR");

        ASTNode current = combinedNode;
        for (String rule : rules) {
            ASTNode childNode = parseRuleString(rule);
            if (current.getLeft() == null) {
                current.setLeft(childNode);
            } else if (current.getRight() == null) {
                current.setRight(childNode);
            } else {
                current.setRight(childNode);
                current = current.getRight();
            }
        }
        return combinedNode;
    }

    // 3. Evaluate a rule against the provided data
    public boolean evaluateRule(ASTNode node, Map<String, Object> data) {
        if (node == null) return false;

        if (node.getType().equals("operator")) {
            // Evaluate left and right child nodes
            boolean leftEval = evaluateRule(node.getLeft(), data);
            boolean rightEval = evaluateRule(node.getRight(), data);

            // Evaluate the operator stored in the value of this node
            String operator = node.getValue();
            if (operator.equals("AND")) {
                return leftEval && rightEval;
            } else if (operator.equals("OR")) {
                return leftEval || rightEval;
            }
        } else if (node.getType().equals("operand")) {
            // Evaluate the operand
            return evaluateOperand(node.getValue(), data);
        }

        return false; // Default fallback if the evaluation cannot be done
    }

    // Helper function to parse rule strings into ASTNode
    private ASTNode parseRuleString(String ruleString) {
        // This is a placeholder for actual parsing logic, which depends on your rule format
        return new ASTNode("operand", null, null, ruleString.trim()); // Example placeholder
    }

    // Method to evaluate a single operand against data
    private boolean evaluateOperand(String operand, Map<String, Object> data) {
        // Split operand string into parts (e.g., "age > 18" becomes ["age", ">", "18"])
        String[] parts = operand.split(" ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid operand format: " + operand);
        }

        String attribute = parts[0].toLowerCase(); // e.g., "age"
        String operator = parts[1];                // e.g., ">"
        Object value = data.get(attribute);         // Get value from data map

        if (value instanceof Number) {
            int intValue = ((Number) value).intValue();
            int compareValue = Integer.parseInt(parts[2]); // Assume the third part is a number

            // Evaluate the operand based on the operator
            switch (operator) {
                case ">":
                    return intValue > compareValue;
                case "<":
                    return intValue < compareValue;
                case "=":
                case "==":
                    return intValue == compareValue;
                default:
                    throw new IllegalArgumentException("Unsupported operator: " + operator);
            }
        }

        // Return false if evaluation cannot be performed or the type is unsupported
        return false;
    }

}
