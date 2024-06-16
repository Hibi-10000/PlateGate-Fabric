/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.github.hibi_10000.mods.fabric.commands.alias

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import java.util.function.Predicate

class LiteralAliasArgumentBuilder<S> internal constructor(literal: String?) : LiteralArgumentBuilder<S>(literal) {
    fun alias(target: LiteralAliasCommandNode<S>): LiteralAliasArgumentBuilder<S> {
        return super.redirect(LiteralAliasCommandNode(target)) as LiteralAliasArgumentBuilder<S>
    }

    override fun requires(requirement: Predicate<S>?): LiteralAliasArgumentBuilder<S> {
        return super.requires(requirement) as LiteralAliasArgumentBuilder<S>
    }

    override fun build(): LiteralAliasCommandNode<S> {
        val result = LiteralAliasCommandNode<S>(literal, command, requirement, redirect, redirectModifier, isFork)
        for (argument in arguments) {
            result.addChild(argument)
        }
        return result
    }
}
