package net.pastek.chemicalscience.registers;

import java.util.function.Supplier;

import net.minecraft.network.chat.Component;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.pastek.chemicalscience.ChemicalScience;
import net.pastek.chemicalscience.common.block.CSBlockMachine;
import net.pastek.chemicalscience.common.block.decoration.CSHalideLamp;
import net.pastek.chemicalscience.common.block.decoration.CSLabSink;
import net.pastek.chemicalscience.common.block.decoration.CSLabSupport;
import net.pastek.chemicalscience.common.block.decoration.CSLadderBlock;
import net.pastek.chemicalscience.common.block.decoration.CSScaffoldingHollow;
import net.pastek.chemicalscience.common.block.decoration.CSScaffoldingOpen;
import net.pastek.chemicalscience.common.block.decoration.CSSymbol;
import net.pastek.chemicalscience.common.block.subtype.SubtypeChemicalMachine;
import net.pastek.chemicalscience.common.tile.TileOrganicSolarPanel;
import voltaic.common.block.BlockMachine;

@SuppressWarnings("null")
public class CSBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChemicalScience.MOD_ID);

                /* ====================================================================== */
                /* ==                              ORES                                == */
                /* ====================================================================== */

    //Overworld
    public static final DeferredBlock<Block> ORE_MAGNESIUM = registerBlock("ore_magnesium", () -> new DropExperienceBlock(UniformInt.of(0, 3),BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_DEEPSLATE_MAGNESIUM = registerBlock("ore_deepslate_magnesium", () -> new DropExperienceBlock(UniformInt.of(0, 3), BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> ORE_SILICON = registerBlock("ore_silicon", () -> new DropExperienceBlock(UniformInt.of(3, 8),BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_DEEPSLATE_SILICON = registerBlock("ore_deepslate_silicon", () -> new DropExperienceBlock(UniformInt.of(3, 8), BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> ORE_ARSENOPYRITE = registerBlock("ore_arsenopyrite", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_DEEPSLATE_ARSENOPYRITE = registerBlock("ore_deepslate_arsenopyrite", () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> ORE_WOLFRAMITE = registerBlock("ore_wolframite", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_DEEPSLATE_WOLFRAMITE = registerBlock("ore_deepslate_wolframite", () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> ORE_COOPERITE = registerBlock("ore_cooperite", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_DEEPSLATE_COOPERITE = registerBlock("ore_deepslate_cooperite", () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> ORE_PYROLUSITE = registerBlock("ore_pyrolusite", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_DEEPSLATE_PYROLUSITE = registerBlock("ore_deepslate_pyrolusite", () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> ORE_OIL = registerBlock("ore_sourcerock", () -> new Block(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.CALCITE)));
    public static final DeferredBlock<Block> ORE_DEEPSLATE_OIL = registerBlock("ore_deepslate_sourcerock", () -> new Block(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.CALCITE)));

    //Stellaris, Ad Astra, Galacticraft
    public static final DeferredBlock<Block> ORE_ARSENOPYRITE_MOON = registerBlock("ore_arsenopyrite_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_ARSENOPYRITE_MARS = registerBlock("ore_arsenopyrite_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_ARSENOPYRITE_VENUS = registerBlock("ore_arsenopyrite_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_ARSENOPYRITE_MERCURY = registerBlock("ore_arsenopyrite_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_COOPERITE_MOON = registerBlock("ore_cooperite_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_COOPERITE_MARS = registerBlock("ore_cooperite_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_COOPERITE_VENUS = registerBlock("ore_cooperite_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_COOPERITE_MERCURY = registerBlock("ore_cooperite_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_MAGNESIUM_MOON = registerBlock("ore_magnesium_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_MAGNESIUM_MARS = registerBlock("ore_magnesium_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_MAGNESIUM_VENUS = registerBlock("ore_magnesium_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_MAGNESIUM_MERCURY = registerBlock("ore_magnesium_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_PYROLUSITE_MOON = registerBlock("ore_pyrolusite_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_PYROLUSITE_MARS = registerBlock("ore_pyrolusite_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_PYROLUSITE_VENUS = registerBlock("ore_pyrolusite_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_PYROLUSITE_MERCURY = registerBlock("ore_pyrolusite_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SILICON_MOON = registerBlock("ore_silicon_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SILICON_MARS = registerBlock("ore_silicon_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SILICON_VENUS = registerBlock("ore_silicon_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SILICON_MERCURY = registerBlock("ore_silicon_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_WOLFRAMITE_MOON = registerBlock("ore_wolframite_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_WOLFRAMITE_MARS = registerBlock("ore_wolframite_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_WOLFRAMITE_VENUS = registerBlock("ore_wolframite_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_WOLFRAMITE_MERCURY = registerBlock("ore_wolframite_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_ALUMINUM_MOON = registerBlock("ore_aluminum_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_ALUMINUM_MARS = registerBlock("ore_aluminum_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_ALUMINUM_VENUS = registerBlock("ore_aluminum_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_ALUMINUM_MERCURY = registerBlock("ore_aluminum_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_CHROMIUM_MOON = registerBlock("ore_chromium_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_CHROMIUM_MARS = registerBlock("ore_chromium_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_CHROMIUM_VENUS = registerBlock("ore_chromium_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_CHROMIUM_MERCURY = registerBlock("ore_chromium_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_FLUORITE_MOON = registerBlock("ore_fluorite_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_FLUORITE_MARS = registerBlock("ore_fluorite_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_FLUORITE_VENUS = registerBlock("ore_fluorite_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_FLUORITE_MERCURY = registerBlock("ore_fluorite_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_LEAD_MOON = registerBlock("ore_lead_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_LEAD_MARS = registerBlock("ore_lead_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_LEAD_VENUS = registerBlock("ore_lead_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_LEAD_MERCURY = registerBlock("ore_lead_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_LITHIUM_MOON = registerBlock("ore_lithium_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_LITHIUM_MARS = registerBlock("ore_lithium_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_LITHIUM_VENUS = registerBlock("ore_lithium_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_LITHIUM_MERCURY = registerBlock("ore_lithium_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_MOLYBDENUM_MOON = registerBlock("ore_molybdenum_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_MOLYBDENUM_MARS = registerBlock("ore_molybdenum_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_MOLYBDENUM_VENUS = registerBlock("ore_molybdenum_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_MOLYBDENUM_MERCURY = registerBlock("ore_molybdenum_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_MONAZITE_MOON = registerBlock("ore_monazite_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_MONAZITE_MARS = registerBlock("ore_monazite_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_MONAZITE_VENUS = registerBlock("ore_monazite_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_MONAZITE_MERCURY = registerBlock("ore_monazite_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_NITER_MOON = registerBlock("ore_niter_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_NITER_MARS = registerBlock("ore_niter_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_NITER_VENUS = registerBlock("ore_niter_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_NITER_MERCURY = registerBlock("ore_niter_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SALT_MOON = registerBlock("ore_salt_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SALT_MARS = registerBlock("ore_salt_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SALT_VENUS = registerBlock("ore_salt_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SALT_MERCURY = registerBlock("ore_salt_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SILVER_MOON = registerBlock("ore_silver_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SILVER_MARS = registerBlock("ore_silver_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SILVER_VENUS = registerBlock("ore_silver_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SILVER_MERCURY = registerBlock("ore_silver_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SULFUR_MOON = registerBlock("ore_sulfur_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SULFUR_MARS = registerBlock("ore_sulfur_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SULFUR_VENUS = registerBlock("ore_sulfur_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SULFUR_MERCURY = registerBlock("ore_sulfur_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SYLVITE_MOON = registerBlock("ore_sylvite_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SYLVITE_MARS = registerBlock("ore_sylvite_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SYLVITE_VENUS = registerBlock("ore_sylvite_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_SYLVITE_MERCURY = registerBlock("ore_sylvite_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_TIN_MOON = registerBlock("ore_tin_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_TIN_MARS = registerBlock("ore_tin_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_TIN_VENUS = registerBlock("ore_tin_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_TIN_MERCURY = registerBlock("ore_tin_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_TITANIUM_MOON = registerBlock("ore_titanium_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_TITANIUM_MARS = registerBlock("ore_titanium_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_TITANIUM_VENUS = registerBlock("ore_titanium_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_TITANIUM_MERCURY = registerBlock("ore_titanium_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_THORIUM_MOON = registerBlock("ore_thorium_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_THORIUM_MARS = registerBlock("ore_thorium_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_THORIUM_VENUS = registerBlock("ore_thorium_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_THORIUM_MERCURY = registerBlock("ore_thorium_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_URANIUM_MOON = registerBlock("ore_uranium_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_URANIUM_MARS = registerBlock("ore_uranium_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_URANIUM_VENUS = registerBlock("ore_uranium_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_URANIUM_MERCURY = registerBlock("ore_uranium_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_VANADIUM_MOON = registerBlock("ore_vanadium_moon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_VANADIUM_MARS = registerBlock("ore_vanadium_mars", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_VANADIUM_VENUS = registerBlock("ore_vanadium_venus", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ORE_VANADIUM_MERCURY = registerBlock("ore_vanadium_mercury", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

                /* ====================================================================== */
                /* ==                         RESOURCE BLOCKS                          == */
                /* ====================================================================== */

    public static final DeferredBlock<Block> MAGNESIUM_BLOCK = registerBlock("block_magnesium", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> SILICON_BLOCK = registerBlock("block_silicon", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> MANGANESE_BLOCK = registerBlock("block_manganese", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TUNGSTEN_BLOCK = registerBlock("block_tungsten", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> PLATINUM_BLOCK = registerBlock("block_platinum", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

                /* ====================================================================== */
                /* ==                        DECORATIVE BLOCKS                         == */
                /* ====================================================================== */

    //Basic
    public static final DeferredBlock<Block> MAGNESIUM_GA9Z1_BLOCK = registerBlock("block_magnesiumaluminum", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.COPPER)));
    public static final DeferredBlock<Block> MAGNESIUM_GA9Z1_LAMP = registerBlock("lamp_magnesiumaluminum", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.COPPER).lightLevel(BlockState -> 15)));
    public static final DeferredBlock<StairBlock> MAGNESIUM_GA9Z1_STAIR = registerBlock("stair_magnesiumaluminum", () -> new StairBlock(CSBlocks.MAGNESIUM_GA9Z1_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.COPPER)));
    public static final DeferredBlock<SlabBlock> MAGNESIUM_GA9Z1_SLAB = registerBlock("slab_magnesiumaluminum", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.COPPER)));
    public static final DeferredBlock<DoorBlock> MAGNESIUM_GA9Z1_DOOR = registerBlock("door_magnesiumaluminum", () -> new DoorBlock(BlockSetType.COPPER, BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> MAGNESIUM_GA9Z1_TRAPDOOR = registerBlock("trapdoor_magnesiumaluminum", () -> new TrapDoorBlock(BlockSetType.COPPER, BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().noOcclusion()));
    public static final DeferredBlock<FenceBlock> MAGNESIUM_GA9Z1_FENCE = registerBlock("fence_magnesiumaluminum", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    //Scaffolding
    public static final DeferredBlock<Block> SCAFFOLDING_MANGANESE = registerBlock("scaffolding_manganese", () -> new Block(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f)));
    public static final DeferredBlock<SlabBlock> SCAFFOLDING_MANGANESE_SLAB = registerBlock("slab_scaffolding_manganese", () -> new SlabBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f)));
    public static final DeferredBlock<Block> SCAFFOLDING_MANGANESE_HOLLOW = registerBlock("scaffolding_manganese_hollow", () -> new CSScaffoldingHollow(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f)));
    public static final DeferredBlock<Block> SCAFFOLDING_MANGANESE_GRATE = registerBlock("scaffolding_grate_manganese", () -> new Block(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f)));
    public static final DeferredBlock<SlabBlock> SCAFFOLDING_MANGANESE_GRATE_SLAB = registerBlock("slab_scaffolding_grate_manganese", () -> new SlabBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f)));
    public static final DeferredBlock<Block> SCAFFOLDING_MANGANESE_GRATE_HOLLOW = registerBlock("scaffolding_grate_manganese_hollow", () -> new CSScaffoldingOpen(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f)));
    public static final DeferredBlock<Block> SCAFFOLDING_MANGANESE_GLASS = registerBlock("scaffolding_glass_manganese", () -> new Block(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f)));
    public static final DeferredBlock<SlabBlock> SCAFFOLDING_MANGANESE_GLASS_SLAB = registerBlock("slab_scaffolding_glass_manganese", () -> new SlabBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f)));
    public static final DeferredBlock<Block> SCAFFOLDING_MANGANESE_GLASS_HOLLOW = registerBlock("scaffolding_glass_manganese_hollow", () -> new CSScaffoldingHollow(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f)));
    public static final DeferredBlock<Block> GRATE_MANGANESE = registerBlock("grate_manganese", () -> new Block(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f)));
    public static final DeferredBlock<CSLadderBlock> LADDER_MANGANESE = registerBlock("ladder_manganese", () -> new CSLadderBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f)));

    public static final DeferredBlock<SlabBlock> SCAFFOLDING_STEEL_SLAB = registerBlock("slab_scaffolding_steel", () -> new SlabBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f)));
    public static final DeferredBlock<Block> SCAFFOLDING_STEEL_HOLLOW = registerBlock("scaffolding_steel_hollow", () -> new CSScaffoldingHollow(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f)));
    public static final DeferredBlock<Block> SCAFFOLDING_STEEL_GRATE = registerBlock("scaffolding_grate_steel", () -> new Block(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f)));
    public static final DeferredBlock<SlabBlock> SCAFFOLDING_STEEL_GRATE_SLAB = registerBlock("slab_scaffolding_grate_steel", () -> new SlabBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f)));
    public static final DeferredBlock<Block> SCAFFOLDING_STEEL_GRATE_HOLLOW = registerBlock("scaffolding_grate_steel_hollow", () -> new CSScaffoldingOpen(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f)));
    public static final DeferredBlock<Block> SCAFFOLDING_STEEL_GLASS = registerBlock("scaffolding_glass_steel", () -> new Block(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f)));
    public static final DeferredBlock<SlabBlock> SCAFFOLDING_STEEL_GLASS_SLAB = registerBlock("slab_scaffolding_glass_steel", () -> new SlabBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f)));
    public static final DeferredBlock<Block> SCAFFOLDING_STEEL_GLASS_HOLLOW = registerBlock("scaffolding_glass_steel_hollow", () -> new CSScaffoldingHollow(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f)));
    public static final DeferredBlock<Block> GRATE_STEEL = registerBlock("grate_steel", () -> new Block(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f)));
    public static final DeferredBlock<CSLadderBlock> LADDER_STEEL = registerBlock("ladder_steel", () -> new CSLadderBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f)));

    // Radiation shielding
    public static final DeferredBlock<Block> CONCRETE_SHIELDING = registerBlock("radiationshielding_concrete", () -> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE_BRICKS).explosionResistance(9)));
    public static final DeferredBlock<Block> TANTALUM_SHIELDING = registerBlock("radiationshielding_tantalum", () -> new Block(BlockBehaviour.Properties.of().strength(8f).requiresCorrectToolForDrops().sound(SoundType.COPPER).explosionResistance(30)));
    public static final DeferredBlock<Block> GLASS_SHIELDING = registerBlock("radiationshielding_glass", () -> new Block(BlockBehaviour.Properties.of().strength(1f).noLootTable().sound(SoundType.GLASS).noOcclusion()));
    public static final DeferredBlock<Block> ADVANCED_GLASS_SHIELDING = registerBlock("radiationshielding_advancedglass", () -> new Block(BlockBehaviour.Properties.of().strength(5f).noLootTable().sound(SoundType.GLASS).explosionResistance(15).noOcclusion()));

    //Storage
    public static final DeferredBlock<Block> RACK_MANGANESE = registerBlock("rack_manganese", () -> new BlockMachine(SubtypeChemicalMachine.rackm));
    public static final DeferredBlock<Block> RACK_STEEL = registerBlock("rack_steel", () -> new BlockMachine(SubtypeChemicalMachine.racks));

    //Laboratory
    public static final DeferredBlock<Block> LABORATORY_BENCH = registerBlock("laboratory_bench", () ->  new CSBlockMachine(SubtypeChemicalMachine.labbench, 0, null));
    public static final DeferredBlock<Block> LABORATORY_SUPPORT = registerBlock("laboratory_support", () -> new CSLabSupport(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(2f)));
    public static final DeferredBlock<Block> LABORATORY_SINK = registerBlock("laboratory_sink", () -> new CSLabSink(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(2f).noOcclusion()));
    public static final DeferredBlock<Block> LABORATORY_STORAGE = registerBlock("laboratory_storage", () ->  new CSBlockMachine(SubtypeChemicalMachine.labstorage, 0, null));

    //Asphalt
    public static final DeferredBlock<Block> ASPHALT_BLOCK = registerBlock("block_asphalt", () -> new Block(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<StairBlock> ASPHALT_STAIR = registerBlock("stair_asphalt", () -> new StairBlock(CSBlocks.ASPHALT_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<SlabBlock> ASPHALT_SLAB = registerBlock("slab_asphalt", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> MARKED_LINE_WHITE_ASPHALT_BLOCK = registerBlock("block_marked_white_line_asphalt", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> MARKED_DOT_WHITE_ASPHALT_BLOCK = registerBlock("block_marked_white_dot_asphalt", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> MARKED_LINE_YELLOW_ASPHALT_BLOCK = registerBlock("block_marked_yellow_line_asphalt", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> MARKED_DOT_YELLOW_ASPHALT_BLOCK = registerBlock("block_marked_yellow_dot_asphalt", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    //Wall Lamps
    public static final DeferredBlock<Block> METAL_HALIDE_LAMP_STEEL_WHITE = registerBlock("metalhalidelamp_sw", () -> new CSHalideLamp(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f).lightLevel(BlockState -> 15)));
    public static final DeferredBlock<Block> METAL_HALIDE_LAMP_STEEL_RED = registerBlock("metalhalidelamp_sr", () -> new CSHalideLamp(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f).lightLevel(BlockState -> 15)));
    public static final DeferredBlock<Block> METAL_HALIDE_LAMP_STEEL_GREEN = registerBlock("metalhalidelamp_sg", () -> new CSHalideLamp(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f).lightLevel(BlockState -> 15)));
    public static final DeferredBlock<Block> METAL_HALIDE_LAMP_STEEL_BLUE = registerBlock("metalhalidelamp_sb", () -> new CSHalideLamp(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(3f).lightLevel(BlockState -> 15)));
    public static final DeferredBlock<Block> METAL_HALIDE_LAMP_MANGANESE_WHITE = registerBlock("metalhalidelamp_mw", () -> new CSHalideLamp(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f).lightLevel(BlockState -> 15)));
    public static final DeferredBlock<Block> METAL_HALIDE_LAMP_MANGANESE_RED = registerBlock("metalhalidelamp_mr", () -> new CSHalideLamp(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f).lightLevel(BlockState -> 15)));
    public static final DeferredBlock<Block> METAL_HALIDE_LAMP_MANGANESE_GREEN = registerBlock("metalhalidelamp_mg", () -> new CSHalideLamp(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f).lightLevel(BlockState -> 15)));
    public static final DeferredBlock<Block> METAL_HALIDE_LAMP_MANGANESE_BLUE = registerBlock("metalhalidelamp_mb", () -> new CSHalideLamp(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(2f).lightLevel(BlockState -> 15)));

    //Signs
    public static final DeferredBlock<Block> SYMBOL_BIOHAZARD = registerBlock("symbol_biohazard", () -> new CSSymbol(BlockBehaviour.Properties.of().instabreak().sound(SoundType.SCAFFOLDING)));
    public static final DeferredBlock<Block> SYMBOL_RADIATION = registerBlock("symbol_radiation", () -> new CSSymbol(BlockBehaviour.Properties.of().instabreak().sound(SoundType.SCAFFOLDING)));
    public static final DeferredBlock<Block> SYMBOL_ELECTRICAL = registerBlock("symbol_electrical", () -> new CSSymbol(BlockBehaviour.Properties.of().instabreak().sound(SoundType.SCAFFOLDING)));
    public static final DeferredBlock<Block> SYMBOL_DANGER = registerBlock("symbol_danger", () -> new CSSymbol(BlockBehaviour.Properties.of().instabreak().sound(SoundType.SCAFFOLDING)));
    public static final DeferredBlock<Block> SYMBOL_FLAMMABLE = registerBlock("symbol_flammable", () -> new CSSymbol(BlockBehaviour.Properties.of().instabreak().sound(SoundType.SCAFFOLDING)));
    public static final DeferredBlock<Block> SYMBOL_TOXIC = registerBlock("symbol_toxic", () -> new CSSymbol(BlockBehaviour.Properties.of().instabreak().sound(SoundType.SCAFFOLDING)));
    public static final DeferredBlock<Block> SYMBOL_CORROSIVE = registerBlock("symbol_corrosive", () -> new CSSymbol(BlockBehaviour.Properties.of().instabreak().sound(SoundType.SCAFFOLDING)));
    public static final DeferredBlock<Block> SYMBOL_PRESSURE = registerBlock("symbol_pressure", () -> new CSSymbol(BlockBehaviour.Properties.of().instabreak().sound(SoundType.SCAFFOLDING)));
    public static final DeferredBlock<Block> SYMBOL_EXPLOSIVE = registerBlock("symbol_explosive", () -> new CSSymbol(BlockBehaviour.Properties.of().instabreak().sound(SoundType.SCAFFOLDING)));

    // Periodic Table
    public static final DeferredBlock<Block> ELEMENT_H =  registerBlock("block_element_h",  () -> new Block(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.BONE_BLOCK)));
    public static final DeferredBlock<Block> ELEMENT_HE = registerBlock("block_element_he", () -> new Block(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> ELEMENT_LI = registerBlock("block_element_li", () -> new Block(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ELEMENT_BE = registerBlock("block_element_be", () -> new Block(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.CALCITE)));
    public static final DeferredBlock<Block> ELEMENT_B =  registerBlock("block_element_b",  () -> new Block(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.SAND)));
    public static final DeferredBlock<Block> ELEMENT_C =  registerBlock("block_element_c",  () -> new Block(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.BONE_BLOCK)));
    public static final DeferredBlock<Block> ELEMENT_N =  registerBlock("block_element_n",  () -> new Block(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.BONE_BLOCK)));
    public static final DeferredBlock<Block> ELEMENT_O =  registerBlock("block_element_o",  () -> new Block(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.BONE_BLOCK)));
    public static final DeferredBlock<Block> ELEMENT_F =  registerBlock("block_element_f",  () -> new Block(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.BONE_BLOCK)));
    public static final DeferredBlock<Block> ELEMENT_NE = registerBlock("block_element_ne", () -> new Block(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> ELEMENT_NA = registerBlock("block_element_na", () -> new Block(BlockBehaviour.Properties.of().strength(3f).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ELEMENT_MG = registerBlock("block_element_mg", () -> new Block(BlockBehaviour.Properties.of().strength(3f).sound(SoundType.CALCITE)));
    public static final DeferredBlock<Block> ELEMENT_AL = registerBlock("block_element_al", () -> new Block(BlockBehaviour.Properties.of().strength(3f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_SI = registerBlock("block_element_si", () -> new Block(BlockBehaviour.Properties.of().strength(3f).sound(SoundType.SAND)));
    public static final DeferredBlock<Block> ELEMENT_P =  registerBlock("block_element_p",  () -> new Block(BlockBehaviour.Properties.of().strength(3f).sound(SoundType.BONE_BLOCK)));
    public static final DeferredBlock<Block> ELEMENT_S =  registerBlock("block_element_s",  () -> new Block(BlockBehaviour.Properties.of().strength(3f).sound(SoundType.BONE_BLOCK)));
    public static final DeferredBlock<Block> ELEMENT_CL = registerBlock("block_element_cl", () -> new Block(BlockBehaviour.Properties.of().strength(3f).sound(SoundType.BONE_BLOCK)));
    public static final DeferredBlock<Block> ELEMENT_AR = registerBlock("block_element_ar", () -> new Block(BlockBehaviour.Properties.of().strength(3f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> ELEMENT_K =  registerBlock("block_element_k",  () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ELEMENT_CA = registerBlock("block_element_ca", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.CALCITE)));
    public static final DeferredBlock<Block> ELEMENT_SC = registerBlock("block_element_sc", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_TI = registerBlock("block_element_ti", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_V =  registerBlock("block_element_v",  () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_CR = registerBlock("block_element_cr", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_MN = registerBlock("block_element_mn", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_FE = registerBlock("block_element_fe", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_CO = registerBlock("block_element_co", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_NI = registerBlock("block_element_ni", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_CU = registerBlock("block_element_cu", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_ZN = registerBlock("block_element_zn", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_GA = registerBlock("block_element_ga", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_GE = registerBlock("block_element_ge", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.SAND)));
    public static final DeferredBlock<Block> ELEMENT_AS = registerBlock("block_element_as", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.SAND)));
    public static final DeferredBlock<Block> ELEMENT_SE = registerBlock("block_element_se", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.BONE_BLOCK)));
    public static final DeferredBlock<Block> ELEMENT_BR = registerBlock("block_element_br", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.BONE_BLOCK)));
    public static final DeferredBlock<Block> ELEMENT_KR = registerBlock("block_element_kr", () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> ELEMENT_RB = registerBlock("block_element_rb", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ELEMENT_SR = registerBlock("block_element_sr", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.CALCITE)));
    public static final DeferredBlock<Block> ELEMENT_Y =  registerBlock("block_element_y",  () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_ZR = registerBlock("block_element_zr", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_NB = registerBlock("block_element_nb", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_MO = registerBlock("block_element_mo", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_TC = registerBlock("block_element_tc", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_RU = registerBlock("block_element_ru", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_RH = registerBlock("block_element_rh", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_PD = registerBlock("block_element_pd", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_AG = registerBlock("block_element_ag", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_CD = registerBlock("block_element_cd", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_IN = registerBlock("block_element_in", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_SN = registerBlock("block_element_sn", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_SB = registerBlock("block_element_sb", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.SAND)));
    public static final DeferredBlock<Block> ELEMENT_TE = registerBlock("block_element_te", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.SAND)));
    public static final DeferredBlock<Block> ELEMENT_I =  registerBlock("block_element_i",  () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.BONE_BLOCK)));
    public static final DeferredBlock<Block> ELEMENT_XE = registerBlock("block_element_xe", () -> new Block(BlockBehaviour.Properties.of().strength(5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> ELEMENT_CS = registerBlock("block_element_cs", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ELEMENT_BA = registerBlock("block_element_ba", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.CALCITE)));
    public static final DeferredBlock<Block> ELEMENT_LA = registerBlock("block_element_la", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_CE = registerBlock("block_element_ce", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_PR = registerBlock("block_element_pr", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_ND = registerBlock("block_element_nd", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_PM = registerBlock("block_element_pm", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_SM = registerBlock("block_element_sm", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_EU = registerBlock("block_element_eu", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_GD = registerBlock("block_element_gd", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_TB = registerBlock("block_element_tb", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_DY = registerBlock("block_element_dy", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_HO = registerBlock("block_element_ho", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_ER = registerBlock("block_element_er", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_TM = registerBlock("block_element_tm", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_YB = registerBlock("block_element_yb", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_LU = registerBlock("block_element_lu", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<Block> ELEMENT_HF = registerBlock("block_element_hf", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_TA = registerBlock("block_element_ta", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_W =  registerBlock("block_element_w",  () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_RE = registerBlock("block_element_re", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_OS = registerBlock("block_element_os", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_IR = registerBlock("block_element_ir", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_PT = registerBlock("block_element_pt", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_AU = registerBlock("block_element_au", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_HG = registerBlock("block_element_hg", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_TL = registerBlock("block_element_tl", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_PB = registerBlock("block_element_pb", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_BI = registerBlock("block_element_bi", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_PO = registerBlock("block_element_po", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_AT = registerBlock("block_element_at", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_RN = registerBlock("block_element_rn", () -> new Block(BlockBehaviour.Properties.of().strength(6f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> ELEMENT_FR = registerBlock("block_element_fr", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ELEMENT_RA = registerBlock("block_element_ra", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.CALCITE)));
    public static final DeferredBlock<Block> ELEMENT_AC = registerBlock("block_element_ac", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_TH = registerBlock("block_element_th", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_PA = registerBlock("block_element_pa", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_U =  registerBlock("block_element_u",  () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_NP = registerBlock("block_element_np", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_PU = registerBlock("block_element_pu", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_AM = registerBlock("block_element_am", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_CM = registerBlock("block_element_cm", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_BK = registerBlock("block_element_bk", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_CF = registerBlock("block_element_cf", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_ES = registerBlock("block_element_es", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_FM = registerBlock("block_element_fm", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_MD = registerBlock("block_element_md", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_NO = registerBlock("block_element_no", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_LR = registerBlock("block_element_lr", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.SOUL_SAND)));
    public static final DeferredBlock<Block> ELEMENT_RF = registerBlock("block_element_rf", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_DB = registerBlock("block_element_db", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_SG = registerBlock("block_element_sg", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_BH = registerBlock("block_element_bh", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_HS = registerBlock("block_element_hs", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_MT = registerBlock("block_element_mt", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_DS = registerBlock("block_element_ds", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_RG = registerBlock("block_element_rg", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_CN = registerBlock("block_element_cn", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ELEMENT_NH = registerBlock("block_element_nh", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_FL = registerBlock("block_element_fl", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_MC = registerBlock("block_element_mc", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_LV = registerBlock("block_element_lv", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_TS = registerBlock("block_element_ts", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> ELEMENT_OG = registerBlock("block_element_og", () -> new Block(BlockBehaviour.Properties.of().strength(7f).sound(SoundType.AMETHYST)));

                /* ====================================================================== */
                /* ==                             MACHINES                             == */
                /* ====================================================================== */

    // Energy
    public static final DeferredBlock<Block> ORGANIC_SOLAR_PANEL = registerBlock("organicsolarpanel", () ->
            new CSBlockMachine(SubtypeChemicalMachine.organicsolarpanel, 240, null) {

                @Override
                protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
                    super.createBlockStateDefinition(builder);
                    builder.add(TileOrganicSolarPanel.TRANSPARENCY);
                }
            }
    );
    public static final DeferredBlock<Block> FUEL_CELL = registerBlock("fuelcell", () -> new CSBlockMachine(SubtypeChemicalMachine.fuelcell, 240, null));

    // Simple Machine
    public static final DeferredBlock<Block> CIRCUIT_MAKER = registerBlock("circuitmaker", () -> new CSBlockMachine(SubtypeChemicalMachine.circuitmaker, 480, null));
    public static final DeferredBlock<Block> HDS_UNIT = registerBlock("hds_unit", () -> new CSBlockMachine(SubtypeChemicalMachine.hdsunit, 480, null));
    public static final DeferredBlock<Block> STEAM_CRACKER = registerBlock("steam_cracker", () -> new CSBlockMachine(SubtypeChemicalMachine.steamcracker, 480, null));
    public static final DeferredBlock<Block> CATALYTIC_REFORMER = registerBlock("catalytic_reformer", () -> new CSBlockMachine(SubtypeChemicalMachine.catalyticreformer, 480, null));
    public static final DeferredBlock<Block> SPIN_COATER = registerBlock("spincoater", () -> new CSBlockMachine(SubtypeChemicalMachine.spincoater, 120, null));


    // Multiblock Machine
    public static final DeferredBlock<Block> FRACTIONATING_COLUMN = registerBlock("fractionating_column", () -> new CSBlockMachine(SubtypeChemicalMachine.fractionatingcolumn, 480, Component.literal("multiblock")));
    public static final DeferredBlock<Block> CHEMICAL_BENCH = registerBlock("chemical_bench_controller", () -> new CSBlockMachine(SubtypeChemicalMachine.chemicalbench, 240, Component.literal("multiblock")));
    public static final DeferredBlock<Block> REDOX_FURNACE = registerBlock("redox_furnace_controller", () -> new CSBlockMachine(SubtypeChemicalMachine.redoxfurnace, 960, Component.literal("multiblock")));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        CSItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}