package org.groovymc.modsdotgroovy.plugin

import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2
import org.apache.logging.log4j.core.Logger
import org.groovymc.modsdotgroovy.core.Platform

@CompileStatic
@SuppressWarnings('GroovyUnusedDeclaration') // All these methods are dynamically called by ModsDotGroovyCore
@Log4j2(category = 'MDG - MultiplatformPlugin')
class MultiplatformPlugin extends ModsDotGroovyPlugin {
    private Platform currentPlatform = Platform.UNKNOWN

    @Override
    void init(final Map<String, ?> environment) {
        this.currentPlatform = environment['platform'] as Platform
        if (currentPlatform !in [Platform.FORGE, Platform.FABRIC])
            throw new PluginResult.MDGPluginException('Unknown platform: ' + currentPlatform)
    }

    @Override
    EnumSet<Platform> getPlatforms() {
        return EnumSet.of(Platform.FORGE, Platform.FABRIC)
    }

    @Override
    Logger getLog() {
        return log
    }

    @Override
    byte getPriority() {
        // The multiplatform plugin needs to be called before other plugins so that it can translate the calls
        return 10
    }

    def setModLoader(final String modLoader) {
        if (currentPlatform === Platform.FABRIC)
            return PluginResult.remove()
    }

    def setSourcesUrl(final String sourcesUrl) {
        switch (currentPlatform) {
            case Platform.FORGE:
                return PluginResult.remove()
            case Platform.FABRIC:
                return PluginResult.move(['contact', 'sources'], sourcesUrl)
        }
    }

    def setLicence(final String licence) {
        if (currentPlatform === Platform.FABRIC)
            // ForgePlugin supports the "licence" alias, FabricPlugin does not
            return PluginResult.rename('license', licence)
    }

    def setIssueTrackerUrl(final String issueTrackerUrl) {
        if (currentPlatform === Platform.FABRIC)
            return PluginResult.move(['contact', 'issues'], issueTrackerUrl)
    }

    def setEnvironment(final def environment) {
        if (currentPlatform === Platform.FORGE)
            return PluginResult.remove()
    }

    def setAccessWidener(final String accessWidener) {
        if (currentPlatform === Platform.FORGE)
            return PluginResult.remove()
    }

    def setIcon(final String icon) {
        if (currentPlatform === Platform.FORGE)
            return PluginResult.remove()
    }

    class Icon {
        def onNestEnter(final Deque<String> stack, final Map value) {
            if (currentPlatform === Platform.FORGE)
                return PluginResult.remove()
        }
    }

    class Mods {
        class ModInfo {
            def setAuthors(final List<String> authors) {
                if (currentPlatform === Platform.FABRIC)
                    return PluginResult.move(['authors'], authors) // todo: see if this works... the structure is different in Fabric
            }

            class Entrypoints {
                def onNestEnter(final Deque<String> stack, final Map value) {
                    switch (currentPlatform) {
                        case Platform.FORGE:
                            return PluginResult.remove()
                        case Platform.FABRIC:
                            return PluginResult.move(['entrypoints'], value)
                    }
                }
            }
        }
    }
}