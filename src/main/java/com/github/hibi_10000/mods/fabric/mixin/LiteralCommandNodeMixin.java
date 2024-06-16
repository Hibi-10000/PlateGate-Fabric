/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.github.hibi_10000.mods.fabric.mixin;

import com.github.hibi_10000.mods.fabric.LiteralCommandNodeAlias;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(LiteralCommandNode.class)
public abstract class LiteralCommandNodeMixin<S> extends CommandNode<S> implements LiteralCommandNodeAlias {
    @Shadow @Final private String literalLowerCase;
    @Mutable @Unique @Final private CommandNode<S> redirect;
    @Mutable @Unique @Final private String alias;

    private LiteralCommandNodeMixin(Command<S> command, Predicate<S> requirement, CommandNode<S> redirect, RedirectModifier<S> modifier, boolean forks) {
        super(command, requirement, redirect, modifier, forks);
    }

    @Inject(at = @At("TAIL"), method = "<init>(Ljava/lang/String;Lcom/mojang/brigadier/Command;Ljava/util/function/Predicate;Lcom/mojang/brigadier/tree/CommandNode;Lcom/mojang/brigadier/RedirectModifier;Z)V")
    private void init(final String literal, final Command<S> command, final Predicate<S> requirement, final CommandNode<S> redirect, final RedirectModifier<S> modifier, final boolean forks, CallbackInfo info) {
        if (redirect instanceof LiteralCommandNode/* && redirect.getName().equals("plategate:plategate")*/) ((LiteralCommandNodeAlias) redirect).plateGate$setAlias(literalLowerCase);
        this.redirect = redirect;
    }

    @Override
    public CommandNode<S> getRedirect() {
        return redirect;
    }

    @Override
    public void plateGate$setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String plateGate$getAlias() {
        return alias;
    }
}
