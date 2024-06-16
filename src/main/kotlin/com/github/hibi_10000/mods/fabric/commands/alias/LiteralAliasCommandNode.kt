/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.github.hibi_10000.mods.fabric.commands.alias

import com.mojang.brigadier.Command
import com.mojang.brigadier.RedirectModifier
import com.mojang.brigadier.tree.CommandNode
import com.mojang.brigadier.tree.LiteralCommandNode
import java.util.function.Predicate

class LiteralAliasCommandNode<S>(
    literal: String,
    command: Command<S>?,
    requirement: Predicate<S>?,
    redirect: CommandNode<S>?,
    modifier: RedirectModifier<S>?,
    forks: Boolean,
) : LiteralCommandNode<S>(literal, command, requirement, redirect, modifier, forks) {
    val alias: String = literal.lowercase()
    constructor(source: LiteralCommandNode<S>) : this(source.literal, source.command, source.requirement, source.redirect, source.redirectModifier, source.isFork)
}
