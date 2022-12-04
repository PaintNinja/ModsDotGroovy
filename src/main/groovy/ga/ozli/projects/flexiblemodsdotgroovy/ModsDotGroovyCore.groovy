package ga.ozli.projects.flexiblemodsdotgroovy

import groovy.transform.CompileStatic

/**
 * The general idea of the new FlexibleModsDotGroovy is to allow for more flexibility through the use of plugins.
 *
 * There are three layers in this new system:
 * 1) This core layer (ModsDotGroovyCore), which is the base layer that all other layers are built on top of.
 * 2) Plugins (e.g. ModsDotGroovyForge), which extend the core layer to add new functionality.
 * 3) The actual MDG script frontend (ModsDotGroovy), which is the layer that the user interacts with, handled by the Gradle plugin.
 *
 * The core layer is responsible for:
 * - Holding the Map data
 * - Providing basic validation that can be customized by plugins
 * - Providing reasonable defaults for the data
 * - Providing shared data types that would theoretically otherwise be implemented by all plugins themselves
 *
 * The plugins are responsible for:
 * - Adding new functionality to the core layer
 * - Changing the defaults from the core layer (e.g. changing the default modLoader to forge)
 * - Adding new validation logic and/or changing the existing core validation logic
 *
 * The frontend is responsible for:
 * - Providing the user with a DSL to interact with the lower levels
 * - Has its mappings dynamically generated by the Gradle plugin
 *     - This prevents the user being given code completions for things that are irrelevant for their use-case, such as
 *       Quilt-specific data when they're only using Forge
 *     - It also allows new plugins to show up in code completions with IDE support without requiring it to be merged
 *       upstream into the ModsDotGroovy repo itself
 */
@CompileStatic
class ModsDotGroovyCore extends PluginAwareMap {
    final float version = 2.00f

    static final ModsDotGroovyCore INSTANCE = new ModsDotGroovyCore()

    protected ModsDotGroovyCore() {
        super(null)
    }
}
