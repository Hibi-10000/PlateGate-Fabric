/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.github.hibi_10000.mods.fabric.commands.plategate

import com.github.hibi_10000.mods.fabric.commands.alias.LiteralAliasCommandNode
import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.CommandManager.argument
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object PGCreate {
    internal val CommandLiteral = literal("create").then(
        argument("name", StringArgumentType.string()).executes(this::execute)
    )

    private fun execute(context: CommandContext<ServerCommandSource>): Int {
        val source = context.source
        val sourceP = source.playerOrThrow
        val name = StringArgumentType.getString(context, "name")
        //val alias = (context.rootNode as LiteralCommandNodeAlias).`plateGate$getAlias`()
        val alias = (context.rootNode as LiteralAliasCommandNode).alias
        context.source.sendFeedback({ Text.literal("Called /$alias create $name") }, true)
        return Command.SINGLE_SUCCESS
    }
}
