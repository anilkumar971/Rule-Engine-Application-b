package com.example.RuleEngineApplication.repo;




import com.example.RuleEngineApplication.Model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
}
