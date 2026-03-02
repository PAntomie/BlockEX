package io.github.PAntomie.cynblockex;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;


public class ModItem {
    public static Item register(String name, @NotNull Function<Item.Settings, Item> itemFactory, Item.@NotNull Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("cynblockex", name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }
    public static final Item CYN = register("cyn", Item::new, new Item.Settings());
    public static final Item UNKNOWN_ITEM = register("unknown_item", Item::new, new Item.Settings());
    public static final Item I1 = register("i1", Item::new, new Item.Settings());
    public static final Item I2 = register("i2", Item::new, new Item.Settings());
    public static void initialize() {}
}
