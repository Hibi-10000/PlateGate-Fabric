/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.github.hibi_10000.mods.fabric.commands

import com.github.hibi_10000.mods.fabric.commands.alias.LiteralAliasArgumentBuilder
import com.github.hibi_10000.mods.fabric.commands.alias.LiteralAliasCommandNode
import com.github.hibi_10000.mods.fabric.commands.plategate.PGCreate
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.ServerCommandSource

object PlateGateCommand {
    private val perm = { source: ServerCommandSource -> source.hasPermissionLevel(2) }
    private val PlateGateCommandLiteral = literal("plategate:plategate").requires(perm)
        .then(PGCreate.CommandLiteral)

    internal fun register() {
        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            val commandNode = dispatcher.register(PlateGateCommandLiteral)
            dispatcher.register(literal("plategate:pg").requires(perm).alias(commandNode as LiteralAliasCommandNode))
            dispatcher.register(literal("plategate")   .requires(perm).alias(commandNode))
            dispatcher.register(literal("pg")          .requires(perm).alias(commandNode))
        }
    }

    private fun literal(literal: String?): LiteralAliasArgumentBuilder<ServerCommandSource> {
        return LiteralAliasArgumentBuilder(literal)
    }
}
