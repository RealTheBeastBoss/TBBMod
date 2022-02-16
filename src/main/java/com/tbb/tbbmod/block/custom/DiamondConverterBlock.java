package com.tbb.tbbmod.block.custom;

import com.tbb.tbbmod.block.entity.DiamondConverterBlockEntity;
import com.tbb.tbbmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class DiamondConverterBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public DiamondConverterBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    public static final VoxelShape SHAPE_N = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(2, 1, 2, 14, 6, 14),
            Block.box(5, 6, 5, 6, 10, 6),
            Block.box(10, 6, 9, 11, 9, 10),
            Block.box(9, 6, 6, 10, 11, 7),
            Block.box(5, 6, 10, 6, 12, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SHAPE_E = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(2, 1, 2, 14, 6, 14),
            Block.box(10, 6, 5, 11, 10, 6),
            Block.box(6, 6, 10, 7, 9, 11),
            Block.box(9, 6, 9, 10, 11, 10),
            Block.box(5, 6, 5, 6, 12, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SHAPE_S = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(2, 1, 2, 14, 6, 14),
            Block.box(10, 6, 10, 11, 10, 11),
            Block.box(5, 6, 6, 6, 9, 7),
            Block.box(6, 6, 9, 7, 11, 10),
            Block.box(10, 6, 5, 11, 12, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SHAPE_W = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(2, 1, 2, 14, 6, 14),
            Block.box(5, 6, 10, 6, 10, 11),
            Block.box(9, 6, 5, 10, 9, 6),
            Block.box(6, 6, 6, 7, 11, 7),
            Block.box(10, 6, 10, 11, 12, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch (pState.getValue(FACING)) {
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof DiamondConverterBlockEntity) {
                ((DiamondConverterBlockEntity) blockEntity).drops();
            }
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof DiamondConverterBlockEntity) {
                NetworkHooks.openGui(((ServerPlayer)pPlayer), (DiamondConverterBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new DiamondConverterBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.DIAMOND_CONVERTER.get(), DiamondConverterBlockEntity::tick);
    }
}
