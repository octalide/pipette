package dev.octalide.mint.blockentities;

import alexiil.mc.lib.attributes.SearchOptions;
import alexiil.mc.lib.attributes.item.ItemAttributes;
import alexiil.mc.lib.attributes.item.ItemExtractable;
import alexiil.mc.lib.attributes.item.ItemInsertable;
import alexiil.mc.lib.attributes.item.ItemInvUtil;
import alexiil.mc.lib.attributes.item.compat.FixedInventoryVanillaWrapper;
import alexiil.mc.lib.attributes.item.impl.RejectingItemInsertable;
import dev.octalide.mint.blocks.MBlocks;
import dev.octalide.mint.blocks.Pipe;
import dev.octalide.mint.blocks.PipeBase;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;

public class PipeEntity extends PipeEntityBase {
    public PipeEntity() {
        super(MBlocks.PIPE_ENTITY);
    }

    @Override
    protected boolean attemptOutput() {
        if (world == null) return false;

        ItemStack stack = getStack(0);
        if (stack == null) return false;

        if (getCachedState().get(PipeBase.Props.powered)) return false;

        Direction outputDirection = getCachedState().get(Pipe.Props.facing);
        Inventory outputInventory = HopperBlockEntity.getInventoryAt(world, pos.offset(outputDirection));

        if (outputInventory != null) {
            ItemStack stackCopy = stack.copy();
            ItemStack result = HopperBlockEntity.transfer(this, outputInventory, this.removeStack(0, 1), outputDirection.getOpposite());

            if (result.isEmpty()) {
                outputInventory.markDirty();
                return true;
            }

            this.setStack(0, stackCopy);
        } else {
            ItemInsertable insertable = ItemAttributes.INSERTABLE.get(world, pos.offset(outputDirection), SearchOptions.inDirection(outputDirection));
            if (insertable == RejectingItemInsertable.NULL) {
                return false;
            }

            ItemExtractable extractable = new FixedInventoryVanillaWrapper(this).getExtractable();

            return ItemInvUtil.move(extractable, insertable, 1) > 0;
        }

        return false;
    }
}
