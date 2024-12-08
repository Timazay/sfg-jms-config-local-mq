package guru.springframework.sfgjms.entity;

import guru.springframework.sfgjms.entity.state.ChildDay;
import guru.springframework.sfgjms.entity.state.ChildEvent;
import lombok.Data;

@Data
public class ChildDayConf {
    private int childId;
    private ChildDay source;
    private ChildDay target;
    private ChildEvent event;
}
