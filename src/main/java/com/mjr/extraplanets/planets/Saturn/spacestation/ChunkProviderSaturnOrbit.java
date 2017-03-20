package com.mjr.extraplanets.planets.Saturn.spacestation;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenSpaceStation;
import net.minecraft.block.BlockFalling;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import java.util.Random;

public class ChunkProviderSaturnOrbit extends ChunkProviderGenerate
{
    private final Random rand;

    private final World worldObj;

    public ChunkProviderSaturnOrbit(World par1World, long par2, boolean par4)
    {
        super(par1World, par2, par4, "");
        this.rand = new Random(par2);
        this.worldObj = par1World;
    }

    @Override
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    @Override
    public int getLoadedChunkCount()
    {
        return 0;
    }

    @Override
    public boolean saveChunks(boolean var1, IProgressUpdate var2)
    {
        return true;
    }

    @Override
    public boolean canSave()
    {
        return true;
    }

    @Override
    public Chunk provideChunk(int par1, int par2)
    {
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);

        final Chunk var4 = new Chunk(this.worldObj, chunkprimer, par1, par2);

        final byte[] biomesArray = var4.getBiomeArray();
        for (int i = 0; i < biomesArray.length; ++i)
        {
            biomesArray[i] = (byte) BiomeGenBaseOrbit.space.biomeID;
        }


        var4.generateSkylightMap();
        return var4;
    }

    @Override
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    @Override
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        BlockFalling.fallInstantly = true;
        final int k = par2 * 16;
        final int l = par3 * 16;
        this.rand.setSeed(this.worldObj.getSeed());
        final long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        final long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(par2 * i1 + par3 * j1 ^ this.worldObj.getSeed());
        if (k == 0 && l == 0)
        {
            BlockPos pos = new BlockPos(k, 64, l);
            this.worldObj.setBlockState(pos, GCBlocks.spaceStationBase.getDefaultState(), 2);

            final TileEntity var8 = this.worldObj.getTileEntity(pos);

            if (var8 instanceof IMultiBlock)
            {
                ((IMultiBlock) var8).onCreate(this.worldObj, pos);
            }

            new WorldGenSpaceStation().generate(this.worldObj, this.rand, new BlockPos(k - 10, 62, l - 3));
        }
        BlockFalling.fallInstantly = false;
    }

    @Override
    public String makeString()
    {
        return "SaturnOrbitLevelSource";
    }
}
