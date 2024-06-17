/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.github.hibi_10000.mods.fabric.mixin;

import com.github.hibi_10000.mods.fabric.ServerCommandSourceAlias;
import net.minecraft.command.CommandSource;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.*;

@Mixin(ServerCommandSource.class)
public abstract class ServerCommandSourceMixin implements CommandSource, ServerCommandSourceAlias {
    @Mutable @Unique @Final private String plateGate$alias;

    @Override
    public void plateGate$setAlias(String alias) {
        this.plateGate$alias = alias;
    }

    @Override
    public String plateGate$getAlias() {
        return plateGate$alias;
    }
}
