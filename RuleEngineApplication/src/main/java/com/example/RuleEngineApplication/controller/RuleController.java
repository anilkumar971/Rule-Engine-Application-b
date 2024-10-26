package com.example.RuleEngineApplication.controller;





 // Ensure correct import for your model
import com.example.RuleEngineApplication.Model.ASTNode;
import com.example.RuleEngineApplication.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    // 1. API to create a rule and return the corresponding AST
    @PostMapping("/create")
    public ASTNode createRule(@RequestBody Map<String, String> requestBody) {
        String ruleString = requestBody.get("ruleString"); // Extract ruleString from the request body
        return ruleService.createRule(ruleString);
    }

    // 2. API to combine multiple rules into a single AST
    @PostMapping("/combine")
    public ASTNode combineRules(@RequestBody String[] rules) {
        return ruleService.combineRules(rules);
    }

    // 3. API to evaluate a rule's AST against provided data
    @PostMapping("/evaluate")
    public boolean evaluateRule(@RequestBody Map<String, Object> data, @RequestParam String ruleString) {
        ASTNode astNode = ruleService.createRule(ruleString); // Create AST from the ruleString
        return ruleService.evaluateRule(astNode, data); // Evaluate AST against provided data
    }
}
