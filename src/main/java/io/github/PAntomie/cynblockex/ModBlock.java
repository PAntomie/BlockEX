package io.github.PAntomie.cynblockex;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;


public class ModBlock {
    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));
        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }
    private static RegistryKey<Block> keyOfBlock(String name) { return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("cynblockex", name)); }
    private static RegistryKey<Item> keyOfItem(String name) { return RegistryKey.of(RegistryKeys.ITEM, Identifier.of("cynblockex", name)); }
    public static final Block RED_OBSIDIAN = register("red_obsidian", Block::new, AbstractBlock.Settings.copy(Blocks.OBSIDIAN), true);
    public static final Block GLOWING_OBSIDIAN = register("glowing_obsidian", Block::new, AbstractBlock.Settings.copy(Blocks.OBSIDIAN), true);
    public static final Block OLD_GLOWING_OBSIDIAN = register("old_glowing_obsidian", Block::new, AbstractBlock.Settings.copy(Blocks.OBSIDIAN), true);
    public static final Block REDUX_GLOWING_OBSIDIAN = register("redux_glowing_obsidian", Block::new, Block.Settings.copy(Blocks.OBSIDIAN), true);
    public static final Block REACTOR_0 = register("reactor_0", Block::new, AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK), true);
    public static final Block REACTOR_1 = register("reactor_1", Block::new, AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK), true);
    public static final Block REACTOR_2 = register("reactor_2", Block::new, AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK), true);
    public static final Block P1 = register("p1", Block::new, AbstractBlock.Settings.copy(Blocks.STONE), true);
    public static final Block P2 = register("p2", Block::new, AbstractBlock.Settings.copy(Blocks.STONE), true);
    public static final Block P3 = register("p3", Block::new, AbstractBlock.Settings.copy(Blocks.STONE), true);
    public static final Block P4 = register("p4", Block::new, AbstractBlock.Settings.copy(Blocks.STONE), true);
    public static void initialize() {}
}
