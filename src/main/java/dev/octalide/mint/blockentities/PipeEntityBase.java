package dev.octalide.mint.blockentities;

import dev.octalide.mint.PipeInventoryImpl;
import dev.octalide.mint.blocks.Pipe;
import dev.octalide.mint.blocks.PipeBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public abstract class PipeEntityBase extends BlockEntity implements PipeInventoryImpl, Tickable {
    public int OUTPUT_COOLDOWN_MAX = 2;
    protected int outputCooldown = 0;

    DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public PipeEntityBase(BlockEntityType type) {
        super(type);
    }

    protected boolean attemptOutput() {
        if (world == null || world.isClient()) return false;
        if (getCachedState().get(PipeBase.Props.powered)) return false;
        if (this.isEmpty()) return false;

        Direction output = getCachedState().get(Pipe.Props.output);

        Inventory outputInventory = HopperBlockEntity.getInventoryAt(world, pos.offset(output));
        if (outputInventory == null) return false;

        return transfer(this, outputInventory, output.getOpposite());
    }

    protected static boolean transfer(Inventory from, Inventory to, Direction direction) {
        ItemStack stack = from.getStack(0).copy();

        ItemStack result = HopperBlockEntity.transfer(from, to, from.removeStack(0, 1), direction);
        if (result.isEmpty()) {
            to.markDirty();
            return true;
        }

        from.setStack(0, stack);
        return false;
    }

    @Override
    public void tick() {
        if (world == null || world.isClient()) return;
        if (this.isEmpty()) {
            outputCooldown = OUTPUT_COOLDOWN_MAX;
            return;
        }

        outputCooldown--;
        
        if (outputCooldown > 0) return;
        outputCooldown = 0;

        if (attemptOutput()) {
            outputCooldown = OUTPUT_COOLDOWN_MAX;
            markDirty();
        }
    }

    @Override
    public int getMaxCountPerStack() {
        return 1;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return this.isEmpty();
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return this.getCachedState().get(PipeBase.Props.output) != dir;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag, items);
        outputCooldown = tag.getInt("output_cooldown");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag, items);
        tag.putInt("output_cooldown", outputCooldown);
        return super.toTag(tag);
    }
}
