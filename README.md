# Mint

Mint is a mod for Minecraft that adds a few [Min]or [T]weaks.

The goal of mint is to improve QOL and automation capabilities while maintaining the vanilla minecraft "feel" we all hold so dear. This is achieved by keeping things wrapped into existing core gameplay mechanics.
For example, most of the crafting recipes for new blocks are composed of vanilla items (no endless "item upgrade" chains found in larger mods) and ALL of the recipes have minimal levels of crafting complexity.
This means you won't be crafting item A for item B for item C for item D for item... you get it.

Additions
---

This section covers the things mint adds to Minecraft.

# Items

## Telescopic Beam

The Telescopic Beam is an item used to craft a few of the blocks in mint, namely the Telescopic Piston and Mining Drill.

# Blocks

## Loadstone

This is a single block that, when placed in a chunk, will keep the chunk loaded.
This block DOES work on servers, but CAN be disabled through configuration.

> While this block honestly doesn't have a very "vanilla" feel, I felt it was appropriate to include in this mod as the uses for an item like this are astounding, and traditional redstone "chunk loaders" *can* be built in vanilla minecraft very inexpensively.

## Pipes

### Pipe

A pipe is similar to a vanilla Minecraft Hopper, but it:

- is slightly cheaper!
- has a single-tile inventory size
- can be placed in any orientation
- cannot pick up items from the surrounding area (can pull from inventories)

In effect, this turns the vanilla Hopper into an item *collection* utility, while Pipes can be used for item *
transportation*.

### Filter Pipe

A Filter Pipe allows for whitelisting a single item type. Simple enough!

### Ender Pipe

The purpose of this Pipe (pipe endpoint really) is to transfer items over vast distances or even *between dimensions*,
similar to an Ender Chest.

Ender Pipes:

- have an inventory space of 5 tiles
- share a single inventory (similar to an Ender Chest)
- by default share a player-restricted inventory (again, like an Ender Chest), UNLESS they are renamed.
    - Ender Pipes with the same name will share the same inventory (Ender Pipes named "My Cobblestone" will have a different
      inventory than ones named "My Diamonds"). This even works on servers, but chose your names wisely as renamed Ender Pipes are NOT bound to player names!

> P.S:
>
> The Ender Pipe is a block I personally debated adding. I decided I would allow it on the condition that it is EXPENSIVE and LIMITING.
> The final decision to add this item came from the realization that *Ender Chests* already exist in vanilla minecraft.
> I decided to implement the Ender Pipe with a similar design-principal to that of the Ender Chest.


---

## Placer

The Placer is similar to an item Dispenser from vanilla Minecraft.
The only difference is when a block is put in a Placer, and it's activated with a redstone signal, the Placer will *place* that block in the direction it's facing.

## Breaker

A Breaker is the opposite of a Placer. When given a redstone signal, it will *break* the block in the direction it's facing.
Note that the Breaker does NOT have an internal inventory and WILL leave the resulting item drop where it spawns.

## Telescopic Piston

The Telescopic Piston behaves like a normal minecraft piston, but extends to the level of redstone signal it receives,
up to 15 blocks. This is an INCREDIBLY useful tool for a very large number of reasons.

The Telescopic Piston also has a sticky variant.

## Mining Drill

The Mining Drill is exactly what it sounds like. The Mining Drill is a block that, when placed and powered with a
redstone signal, will extend in the direction it's facing until it encounters a block, which it will break and collect.

Items mined by the drill can be extracted from it's single tile inventory with a hopper or pipe.

The Mining Drill sounds like an OP item, and it definitely can be, however, creating many of these drills and linking
them up is NOT a cheap process and does require quite a bit of planning and redstone work.

Keeping these balanced required they have proper limitations. The Mining Drill:

- cannot extend horizontally past 64 blocks (there is no vertical limit!)
- has a single-tile inventory space (same as the pipe it uses in it's recipe)
- does NOT collect extra loot from ore (some mods allow this)
- does NOT collect mining XP