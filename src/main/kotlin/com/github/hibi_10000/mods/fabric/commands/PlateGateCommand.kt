/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.github.hibi_10000.mods.fabric.commands

import com.github.hibi_10000.mods.fabric.ServerCommandSourceAlias
import com.github.hibi_10000.mods.fabric.commands.plategate.PGCreate
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource

object PlateGateCommand {
    private val alias = { source: ServerCommandSource, alias: String ->
        (source as ServerCommandSourceAlias).`plateGate$setAlias`(alias)
        source
    }
    private val perm = { source: ServerCommandSource -> source.hasPermissionLevel(2) }
    private val PlateGateCommandLiteral = literal("plategate:plategate").requires(perm)
        .then(PGCreate.CommandLiteral)

    internal fun register() {
        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            val p   = literal("plategate"   ).requires(perm)
            val ppg = literal("plategate:pg").requires(perm)
            val  pg = literal(          "pg").requires(perm)
            val commandNode = dispatcher.register(PlateGateCommandLiteral)
            dispatcher.register(ppg.redirect(commandNode) { alias(it.source, ppg.literal) })
            dispatcher.register(p  .redirect(commandNode) { alias(it.source, p  .literal) })
            dispatcher.register( pg.redirect(commandNode) { alias(it.source,  pg.literal) })
        }
    }
}
