package fr.themode.minestom.event;

import fr.themode.minestom.entity.Player;
import fr.themode.minestom.item.ItemStack;

public class UseItemEvent extends Event {

    private Player.Hand hand;
    private ItemStack itemStack;

    public UseItemEvent(Player.Hand hand, ItemStack itemStack) {
        this.hand = hand;
        this.itemStack = itemStack;
    }

    public Player.Hand getHand() {
        return hand;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}