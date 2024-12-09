package guru.springframework.sfgjms.util;

import com.typesafe.config.*;
import guru.springframework.sfgjms.entity.state.ChildDay;
import guru.springframework.sfgjms.entity.state.ChildEvent;

public interface ConfigProvider {

    static Config readConf(Config config){
        return ConfigFactory.load(config);
    }
    static Config readConfString(String config){
        return ConfigFactory.parseString(config);
    }
    static Config createConf(int id, String source, String target, String event){
        return ConfigFactory.empty()
                .withValue("child.id", ConfigValueFactory.fromAnyRef(id))
                .withValue("child.source", ConfigValueFactory.fromAnyRef(source))
                .withValue("child.target", ConfigValueFactory.fromAnyRef(target))
                .withValue("child.event", ConfigValueFactory.fromAnyRef(event));
    }
}
