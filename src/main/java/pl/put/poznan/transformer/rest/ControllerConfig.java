package pl.put.poznan.transformer.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.put.poznan.transformer.logic.elements.ScenarioFormatter;
import pl.put.poznan.transformer.logic.elements.ScenarioSimplifier;
import pl.put.poznan.transformer.logic.visitor.CheckActorVisitor;
import pl.put.poznan.transformer.logic.visitor.CountStepsVisitor;
import pl.put.poznan.transformer.logic.visitor.KeywordVisitor;

import java.util.function.Supplier;

@Configuration
public class ControllerConfig {

    @Bean
    public Supplier<CheckActorVisitor> checkActorVisitorSupplier() {
        return CheckActorVisitor::new;
    }

    @Bean
    public Supplier<CountStepsVisitor> countStepsVisitorSupplier() {
        return CountStepsVisitor::new;
    }

    @Bean
    public Supplier<KeywordVisitor> keywordVisitorSupplier() {
        return KeywordVisitor::new;
    }

    @Bean
    public Supplier<ScenarioFormatter> scenarioFormatterSupplier() {
        return ScenarioFormatter::new;
    }

    @Bean
    public Supplier<ScenarioSimplifier> scenarioSimplifierSupplier() {
        return ScenarioSimplifier::new;
    }
}
