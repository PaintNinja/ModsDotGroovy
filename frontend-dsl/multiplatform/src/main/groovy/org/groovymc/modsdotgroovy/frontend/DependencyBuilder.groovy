package org.groovymc.modsdotgroovy.frontend

import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2
import org.groovymc.modsdotgroovy.core.ModsDotGroovyCore
import org.groovymc.modsdotgroovy.core.versioning.VersionRangeAware

@CompileStatic
@Log4j2(category = 'MDG - Multiplatform Frontend')
class DependencyBuilder extends DslBuilder implements PropertyInterceptor, MapClosureInterceptor, OnPlatform {
    /**@
     * The ID of the mod this dependency is depending on.
     */
    String modId

    /**@
     * Does this dependency have to exist? If not, ordering must also be specified.
     */
    boolean mandatory = true

    /**@
     * A version range of the versions of the mod you're compatible with.
     */
    @VersionRangeAware
    String versionRange

    /**@
     * An ordering relationship for the dependency - BEFORE or AFTER required if the relationship is not mandatory
     */
    String ordering = 'NONE'

    /**@
     * Side this dependency is applied on - BOTH, CLIENT or SERVER
     */
    String side = 'BOTH'

    DependencyBuilder(final ModsDotGroovyCore core) {
        super(core)
    }
}
