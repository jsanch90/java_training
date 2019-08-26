package com.talos.javatraining.lesson9.commands.impl;

import com.talos.javatraining.lesson9.commands.AppCommand;
import com.talos.javatraining.lesson9.events.EventBus;
import com.talos.javatraining.lesson9.events.EventType;

import java.util.function.Supplier;


public class CommandTemplate implements AppCommand {

    private String[] args;
    private EventBus eventBus;
    private Supplier<EventType> op;

    public CommandTemplate(EventBus eventBus, Supplier<EventType> op, String... args) {
        this.args = args;
        this.eventBus = eventBus;
        this.op = op;
    }

    @Override
    public void execute() {
        eventBus.notify(op.get(), args);
    }

}
