/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.github.hibi_10000.mods.fabric.commands.plategate

import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.command.argument.RotationArgumentType
import net.minecraft.command.argument.Vec3ArgumentType
import net.minecraft.server.command.CommandManager.argument
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object PGCreate {
    internal val CommandLiteral = literal("create").then(
        argument("name", StringArgumentType.string()).then(
            argument("destination", EntityArgumentType.entity()).executes { context ->
                val source = context.source
                val sourceP = source.playerOrThrow
                val name = StringArgumentType.getString(context, "name")
                val destination = EntityArgumentType.getEntity(context, "destination")
                execute(context)
            }
        ).then(
            argument("location", Vec3ArgumentType.vec3()).then(
                //TODO: northとかで指定?
                argument("rotation", RotationArgumentType.rotation()).executes { context ->
                    val source = context.source
                    val sourceP = source.playerOrThrow
                    val name = StringArgumentType.getString(context, "name")
                    val location = Vec3ArgumentType.getVec3(context, "location")
                    val rotation = RotationArgumentType.getRotation(context, "rotation")
                    execute(context)
                }
            )
        ).executes { context ->
            val source = context.source
            val sourceP = source.playerOrThrow
            val name = StringArgumentType.getString(context, "name")
            execute(context)
        }
    )

    private fun execute(context: CommandContext<ServerCommandSource>): Int {
        println("PGCreate executed ${context.input}")
        context.source.sendFeedback({ Text.literal("Called /plategate create ${context.input}") }, true)
        return Command.SINGLE_SUCCESS
    }
}
