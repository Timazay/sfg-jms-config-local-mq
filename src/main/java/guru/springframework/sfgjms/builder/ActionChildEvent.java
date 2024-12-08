package guru.springframework.sfgjms.builder;

import guru.springframework.sfgjms.entity.Child;
import guru.springframework.sfgjms.entity.state.ChildDay;
import guru.springframework.sfgjms.entity.state.ChildEvent;
import lombok.extern.java.Log;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

@Log
public class ActionChildEvent {


    public Action<ChildDay, ChildEvent> action(Child child, ChildEvent event){
        switch (event){
            case GOING_TO_SCHOOL:
                return goToSchool(child);
            case DOING_HOMEWORK:
                return doHomework();
            case SPORT_ACTIVITY:
                return sportActivity();
            case PLAYING_VIDEO_GAMES:
                return playVideoGame();
            case LEISURE_ACTIVITY:
                return leisureActivity();
            default:
                return complete();
        }
    }

    private Action<ChildDay, ChildEvent> goToSchool(Child child){
        return new Action<ChildDay, ChildEvent>() {
            @Override
            public void execute(StateContext<ChildDay, ChildEvent> context) {
                log.info("Method go to school executed");
            }
        };

    }

    private Action<ChildDay, ChildEvent> playVideoGame(){

        return new Action<ChildDay, ChildEvent>() {
            @Override
            public void execute(StateContext<ChildDay, ChildEvent> context) {
                log.info("Method play video games executed");
            }
        };
    }

    private Action<ChildDay, ChildEvent> doHomework(){

        return new Action<ChildDay, ChildEvent>() {
            @Override
            public void execute(StateContext<ChildDay, ChildEvent> context) {
                log.info("Method do homework executed");
            }
        };
    }


    private Action<ChildDay, ChildEvent> leisureActivity(){

        return new Action<ChildDay, ChildEvent>() {
            @Override
            public void execute(StateContext<ChildDay, ChildEvent> context) {
                log.info("Method leisureActivity executed");
            }
        };
    }

    private Action<ChildDay, ChildEvent> sportActivity(){

        return new Action<ChildDay, ChildEvent>() {
            @Override
            public void execute(StateContext<ChildDay, ChildEvent> context) {
                log.info("Method sport activity executed");
            }
        };
    }

    private Action<ChildDay, ChildEvent> complete(){

        return new Action<ChildDay, ChildEvent>() {
            @Override
            public void execute(StateContext<ChildDay, ChildEvent> context) {
                log.info("Method complete executed");
            }
        };
    }
}
