package com.github.justin.humancompanions.entity;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

public class LowHealthGoal extends Goal {
    protected final AbstractHumanCompanionEntity mob;
    int startTick = 0;
    TextComponent text = new TextComponent("I need food!");
    ItemStack food = ItemStack.EMPTY;

    public LowHealthGoal (AbstractHumanCompanionEntity entity) {
        this.mob = entity;
    }

    public boolean canUse() {
        if (this.mob.getHealth() < 10 && this.mob.isTame()) {
            food = mob.checkFood();
            return food.isEmpty();
        }
        return false;

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
