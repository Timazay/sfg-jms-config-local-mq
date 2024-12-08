package guru.springframework.sfgjms.entity;


import guru.springframework.sfgjms.entity.state.ChildDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Father implements Member {

    public String name;

    public int age;

    public void setChildEvent(Child child, ChildDay day) {
        child.setDay(day);
    }
}