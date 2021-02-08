package dev.octalide.mint.blocks;

import dev.octalide.mint.Mint;
import dev.octalide.mint.blockentities.PipeEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

import java.util.Arrays;

public class Pipe extends PipeBase {
    public static final String NAME = "pipe";
    public static final Identifier ID = new Identifier(Mint.MOD_ID, NAME);

    @Override
    protected boolean canExtend(BlockState state, BlockState other, Direction direction) {
        boolean can = false;

        DirectionProperty[] props = {
            Properties.FACING,
            Properties.HORIZONTAL_FACING,
            Properties.HOPPER_FACING
        };

        if (other.getBlock() == Blocks.HOPPER) {
            can = Arrays.stream(props).anyMatch(directionProperty ->
                other.contains(directionProperty) && other.get(directionProperty) == direction.getOpposite());

        } else if (other.getBlock() == MBlocks.PIPE) {
            can = other.get(Properties.FACING) == direction.getOpposite();

        } else if (other.getBlock() == MBlocks.PIPE_EXTRACTOR) {
            can = true;

        } else if (other.getBlock() == MBlocks.PIPE_FILTER) {
            can = true;
        }

        return can;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new PipeEntity();
    }
}
