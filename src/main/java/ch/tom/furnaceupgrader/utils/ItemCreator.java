package ch.tom.furnaceupgrader.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemCreator {

    private final ItemStack item;
    private final ItemMeta itemMeta;

    public ItemCreator(Material material, int amount) {
        item = new ItemStack(material, amount);
        itemMeta = item.getItemMeta();
    }

    public ItemCreator(Material material) {
        this(material, 0);
    }

    public ItemCreator setDisplayName(String displayName) {
        itemMeta.setDisplayName(displayName);
        return this;
    }

    public ItemCreator setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemCreator setUnbreakable(boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemStack create() {
        item.setItemMeta(itemMeta);
        return item;
    }
}
