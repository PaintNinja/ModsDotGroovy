package org.groovymc.modsdotgroovy.frontend

import groovy.transform.CompileStatic
import org.groovymc.modsdotgroovy.core.ModsDotGroovyCore

@CompileStatic
abstract class ModsDotGroovyFrontend implements VersionProducer {
    final ModsDotGroovyCore core

    /**@
     * If running in a Gradle environment, this will be populated with the {@code build.properties}.
     */
    public final Map<String, ?> buildProperties = [:]

    /**@
     * If running in a Gradle environment, this will be populated with
     * <a href="https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog">version catalogue</a> data
     */
    public final VersionCatalogue libs

    ModsDotGroovyCore getCore() {
        return core
    }

    @SuppressWarnings('GroovyUnusedDeclaration') // Used by the Groovy compiler for coercing an implicit `it` closure
    ModsDotGroovyFrontend() {
        this([:])
    }

    ModsDotGroovyFrontend(final Map<String, ?> environment) {
        this.core = new ModsDotGroovyCore(environment)

        if (environment.containsKey('buildProperties'))
            this.@buildProperties.putAll(environment.buildProperties as Map<String, ?>)

        this.@libs = new VersionCatalogue((environment.libs as Map<String, Map<String, ?>>) ?: [:])
    }
}
