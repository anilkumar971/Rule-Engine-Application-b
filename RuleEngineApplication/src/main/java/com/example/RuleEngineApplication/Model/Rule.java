package com.example.RuleEngineApplication.Model;





import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleString; // Stores the raw rule string

    @Lob
    private String astJson; // Stores the serialized AST JSON


}