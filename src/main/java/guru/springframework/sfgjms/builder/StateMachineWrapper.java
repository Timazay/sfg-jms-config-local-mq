package guru.springframework.sfgjms.builder;

import guru.springframework.sfgjms.entity.state.ChildDay;
import guru.springframework.sfgjms.entity.state.ChildEvent;

import java.io.Serializable;

public class StateMachineWrapper implements Serializable {
    private int id;
    private ChildDay source;
    private ChildDay target;
    private ChildEvent event;
}
