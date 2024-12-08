package guru.springframework.sfgjms.entity;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Family {
    private int id;

    private Mother mother;

    private Father father;

    private List<Child> children;

    {
        children = new ArrayList<>();
    }

    public void addMember(Member member){
        if (member instanceof Mother){
            this.mother = (Mother) member;
        } else if (member instanceof  Father){
           this.father = (Father) member;
        } else {
            this.children.add((Child) member);
        }
    }

}
