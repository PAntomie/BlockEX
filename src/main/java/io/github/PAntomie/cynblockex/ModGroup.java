package io.github.PAntomie.cynblockex;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModGroup {
    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of("cynblockex", "item_group"));
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItem.CYN))
            .displayName(Text.translatable("itemGroup.cynblockex"))
            .build();
    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(Blocks.GLASS);
            itemGroup.add(Blocks.TINTED_GLASS);
            itemGroup.add(Blocks.RED_STAINED_GLASS);
            itemGroup.add(Blocks.PINK_STAINED_GLASS);
            itemGroup.add(Blocks.BLUE_STAINED_GLASS);
            itemGroup.add(Blocks.CYAN_STAINED_GLASS);
            itemGroup.add(Blocks.GRAY_STAINED_GLASS);
            itemGroup.add(Blocks.LIME_STAINED_GLASS);
            itemGroup.add(Blocks.GREEN_STAINED_GLASS);
            itemGroup.add(Blocks.BLACK_STAINED_GLASS);
            itemGroup.add(Blocks.BROWN_STAINED_GLASS);
            itemGroup.add(Blocks.WHITE_STAINED_GLASS);
            itemGroup.add(Blocks.ORANGE_STAINED_GLASS);
            itemGroup.add(Blocks.PURPLE_STAINED_GLASS);
            itemGroup.add(Blocks.YELLOW_STAINED_GLASS);
            itemGroup.add(Blocks.MAGENTA_STAINED_GLASS);
            itemGroup.add(Blocks.LIGHT_BLUE_STAINED_GLASS);
            itemGroup.add(ModBlock.REDUX_GLOWING_OBSIDIAN);
            itemGroup.add(ModBlock.OLD_GLOWING_OBSIDIAN);
            itemGroup.add(ModBlock.GLOWING_OBSIDIAN);
            itemGroup.add(ModBlock.RED_OBSIDIAN);
            itemGroup.add(ModBlock.REACTOR_0);
            itemGroup.add(ModBlock.REACTOR_1);
            itemGroup.add(ModBlock.REACTOR_2);
            });
    }
}
