package guru.springframework.sfgjms.util;

import com.typesafe.config.*;

public interface ConfigProvider {
    Config configReader = readConf();
    Config configCreator = ;

    static Config readConf(){
        return ConfigFactory.load("config.conf");
    }
    static Config createConf(){
        return ConfigFactory.empty().withValue("src/main/java/guru/springframework/sfgjms/entity/ChildDayConf.java", ConfigValueFactory.fromAnyRef());
    }
}
