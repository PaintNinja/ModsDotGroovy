package io.github.groovymc.modsdotgroovy.frontend

import groovy.transform.CompileStatic
import io.github.groovymc.modsdotgroovy.core.ModsDotGroovyCore

@CompileStatic
class ModInfoBuilder implements PropertyInterceptor, MapClosureInterceptor {
    private final ModsDotGroovyCore core

    String modId = null

    ModInfoBuilder() {
        println "[Frontend] new io.github.groovymc.modsdotgroovy.frontend.ModInfoBuilder()"
        this.core = null
    }

    ModInfoBuilder(final ModsDotGroovyCore core) {
        println "[Frontend] new io.github.groovymc.modsdotgroovy.frontend.ModInfoBuilder(core: $core)"
        this.core = core
    }
}
