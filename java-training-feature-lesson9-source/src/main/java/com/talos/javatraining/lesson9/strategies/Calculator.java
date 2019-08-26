package com.talos.javatraining.lesson9.strategies;

import com.talos.javatraining.lesson9.events.EventBus;
import com.talos.javatraining.lesson9.events.EventType;
import com.talos.javatraining.lesson9.strategies.impl.CalculatorTemplate;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;


public class Calculator {
    private EventBus eventBus;
    private Map<String, CalculatorStrategy> strategies;
    private String mode = "basic";// default mode

    @Inject
    public Calculator(EventBus eventBus) {

        this.eventBus = eventBus;
        eventBus.register(EventType.ADD, args -> calculate(getStrategy()::add, args));
        eventBus.register(EventType.SUBTRACT, args -> calculate(getStrategy()::subtract, args));
        eventBus.register(EventType.MULTIPLY, args -> calculate(getStrategy()::multiply, args));
        eventBus.register(EventType.DIVIDE, args -> calculate(getStrategy()::divide, args));
        eventBus.register(EventType.CHANGE_MODE, this::changeMode);

        this.strategies = new HashMap<>();
        this.strategies.put("basic", new CalculatorTemplate((value) -> value.setScale(5, RoundingMode.HALF_UP).toString()));
        this.strategies.put("scientific", new CalculatorTemplate(this::formatter));
    }

    private CalculatorStrategy getStrategy() {
        return strategies.get(mode);
    }

    private String formatter(BigDecimal val) {
        DecimalFormat formatter = new DecimalFormat("0.00E00");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        formatter.setMinimumFractionDigits(10);
        return formatter.format(val);
    }

    private void calculate(BiFunction<String, String, String> operation, String[] args) {
        String result = operation.apply(args[0], args[1]);
        eventBus.notify(EventType.RESULT, "> " + result);
    }

    private void changeMode(String[] args) {
        String tempMode = args[0];
        if (!strategies.containsKey(tempMode)) {
            eventBus.notify(EventType.RESULT, "not supported mode: " + tempMode);
            return;
        }
        mode = tempMode;
        eventBus.notify(EventType.RESULT, "mode: " + mode);
    }


}
