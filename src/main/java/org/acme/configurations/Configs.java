package org.acme.configurations;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;

@StaticInitSafe
@ConfigMapping(prefix = "code-with-quarkus", namingStrategy = ConfigMapping.NamingStrategy.KEBAB_CASE)
public interface Configs {
    String rapidCarApiUrl();
}
