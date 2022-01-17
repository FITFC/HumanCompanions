package com.github.justin.companions.entity;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;

public class LowHealthGoal extends Goal {
    protected final TamableAnimal mob;
    int startTick = 0;
    TextComponent text = new TextComponent("My health is low!");

    public LowHealthGoal (TamableAnimal entity) {
        this.mob = entity;
    }

    public boolean canUse() {
        if (this.mob.getHealth() < 10 && this.mob.isTame()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void start() {
        startTick = this.mob.tickCount;
        this.mob.getOwner().sendMessage(new TranslatableComponent("chat.type.text", this.mob.getDisplayName(), text),
                this.mob.getUUID());
    }

    public void tick() {
        if ((this.mob.tickCount - startTick) % (15 * 20) == 0 && this.mob.tickCount > startTick) {
            this.mob.getOwner().sendMessage(new TranslatableComponent("chat.type.text", this.mob.getDisplayName(),
                            text),
                    this.mob.getUUID());
            }

    }
}