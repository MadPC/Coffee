package com.madpc.coffee.world;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderCoffee implements IChunkProvider {

    public ChunkProviderCoffee() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean chunkExists(int i, int j) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Chunk provideChunk(int i, int j) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Chunk loadChunk(int i, int j) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void populate(IChunkProvider ichunkprovider, int i, int j) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean unloadQueuedChunks() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canSave() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String makeString() {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i,
            int j, int k) {
        // TODO Auto-generated method stub
        return null;
    }

    public ChunkPosition func_147440_b(World world, String s, int i, int j, int k) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getLoadedChunkCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void recreateStructures(int i, int j) {
        // TODO Auto-generated method stub

    }

    public void func_104112_b() {
        // TODO Auto-generated method stub

    }

	@Override
	public ChunkPosition func_147416_a(World var1, String var2, int var3,
			int var4, int var5)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveExtraData()
	{
		// TODO Auto-generated method stub
		
	}

}
