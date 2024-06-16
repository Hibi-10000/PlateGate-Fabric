/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.github.hibi_10000.mods.fabric

import com.github.hibi_10000.mods.fabric.commands.PlateGateCommand
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object PlateGate : ModInitializer {
    private val logger = LoggerFactory.getLogger("plategate")

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Hello Fabric world!")
        PlateGateCommand.register()
    }
}
