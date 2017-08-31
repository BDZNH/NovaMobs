package com.pikycz.novamobs;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityItem;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import com.pikycz.novamobs.configsection.MainConfig;
import com.pikycz.novamobs.entities.projectile.*;
import com.pikycz.novamobs.entities.monster.walking.*;
import com.pikycz.novamobs.entities.animal.walking.*;
import com.pikycz.novamobs.entities.BaseEntity;
import com.pikycz.novamobs.entities.monster.walking.Wolf;
import com.pikycz.novamobs.task.AutoSpawnTask;
import com.pikycz.novamobs.utils.Utils;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import java.util.Timer;

public class NovaMobs extends PluginBase implements Listener {

    private static NovaMobs Instance;

    public NovaMobs plugin;

    Config config;

    public final HashMap<Integer, Level> levelsToSpawn = new HashMap<>();

    public List<String> disabledWorlds;

    static NovaMobs getInstance() {
        return Instance;
    }

    private int counter = 0;

    @Override
    public void onLoad() {
        registerEntities();
        Utils.logServerInfo(TextFormat.colorize("&bL&fo&ea&cd&di&en&fg &bNova&6Mobs &f- &aRe&cC&ar&be&5a&ct&2e"));
        Utils.logServerInfo("Version - 1.2-Dev");
    }

    @Override
    public void onEnable() {
        Instance = this;
        this.getServer().getPluginManager().registerEvents(this, this);
        //Config
        this.getDataFolder().mkdirs();
        this.saveResource("config.yml");
        config = new Config(this.getDataFolder() + "/config.yml");
        config.getList("worlds-spawn-disabled", new ArrayList());
        config.getBoolean("spawn-animals", true);
        config.getBoolean("spawn-mobs", true);
        config.getInt("auto-spawn-tick", 20);
        //Config

        if (MainConfig.SpawnDelay > 0) {

            Timer timer = new Timer();

            timer.schedule(new AutoSpawnTask(this), 10000, 1000);
        }

        for (Level level : Server.getInstance().getLevels().values()) {
            if (disabledWorlds.contains(level.getFolderName().toLowerCase())) {
                continue;
            }

            levelsToSpawn.put(level.getId(), level);
        }

    }

    private void registerEntities() {
        // register Passive entities
        //Entity.registerEntity(Bat.class.getSimpleName(), Bat.class); //Fly too high
        Entity.registerEntity(Chicken.class.getSimpleName(), Chicken.class);
        Entity.registerEntity(Cow.class.getSimpleName(), Cow.class);
        //Entity.registerEntity(Donkey.class.getSimpleName(), Donkey.class);
        //Entity.registerEntity(Horse.class.getSimpleName(), Horse.class);//TODO: Riding O-O
        Entity.registerEntity(Mooshroom.class.getSimpleName(), Mooshroom.class);
        //Entity.registerEntity(Mule.class.getSimpleName(), Mule.class);
        Entity.registerEntity(Ocelot.class.getSimpleName(), Ocelot.class);
        Entity.registerEntity(Pig.class.getSimpleName(), Pig.class);
        Entity.registerEntity(PolarBear.class.getSimpleName(), PolarBear.class);
        Entity.registerEntity(Rabbit.class.getSimpleName(), Rabbit.class);
        Entity.registerEntity(Sheep.class.getSimpleName(), Sheep.class);
        //Entity.registerEntity(SkeletonHorse.class.getSimpleName(), SkeletonHorse.class);
        Entity.registerEntity(Villager.class.getSimpleName(), Villager.class);
        Entity.registerEntity(Wolf.class.getSimpleName(), Wolf.class);
        //Entity.registerEntity(ZombieHorse.class.getSimpleName(), ZombieHorse.class);

        //register Monster entities
        //Entity.registerEntity(Blaze.class.getSimpleName(), Blaze.class);
        //Entity.registerEntity(EnderDragon.class.getSimpleName(), EnderDragon.class); //TODO: Spawn in End
        //Entity.registerEntity(Wither.class.getSimpleName(), Wither.class);
        //Entity.registerEntity(ElderGuardian.class.getSimpleName(), ElderGuardian.class); //TODO: Spawn in Ocean palace swim , attack
        //Entity.registerEntity(Ghast.class.getSimpleName(), Ghast.class); //TODO: Spawn in Nether
        //Entity.registerEntity(Guardian.class.getSimpleName(), Guardian.class); //TODO: Spawn in Ocean palace swim , attack
        Entity.registerEntity(CaveSpider.class.getSimpleName(), CaveSpider.class);
        Entity.registerEntity(Creeper.class.getSimpleName(), Creeper.class);
        Entity.registerEntity(Enderman.class.getSimpleName(), Enderman.class); //TODO: Move(teleport) , attack
        //Entity.registerEntity(MagmaCube.class.getSimpleName(), MagmaCube.class);//Spawn In Nether
        Entity.registerEntity(PigZombie.class.getSimpleName(), PigZombie.class);//Spawn in Nether
        Entity.registerEntity(Silverfish.class.getSimpleName(), Silverfish.class); //TODO: Spawn random from stone
        Entity.registerEntity(Skeleton.class.getSimpleName(), Skeleton.class);
        //Entity.registerEntity(Slime.class.getSimpleName(), Slime.class); //TODO: Make random spawn Slime (Big,Small)
        Entity.registerEntity(Spider.class.getSimpleName(), Spider.class);
        Entity.registerEntity(Stray.class.getSimpleName(), Stray.class);
        Entity.registerEntity(Witch.class.getSimpleName(), Witch.class);
        Entity.registerEntity(Husk.class.getSimpleName(), Husk.class);
        Entity.registerEntity(Zombie.class.getSimpleName(), Zombie.class);
        Entity.registerEntity(ZombieVillager.class.getSimpleName(), ZombieVillager.class);

        // register the fireball entity
        Entity.registerEntity("BlueWitherSkull", BlueWitherSkull.class);
        Entity.registerEntity("BlazeFireBall", BlazeFireBall.class);
        Entity.registerEntity("DragonFireBall", DragonFireBall.class);
        Entity.registerEntity("GhastDireBall", GhastFireBall.class);

        Utils.logServerInfo("Register: Entites - Done.");
    }

    /**
     * @param commandSender
     * @param cmd
     * @param label
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String label, String[] args) {
        if (cmd.getName().toLowerCase().equals("mob")) {

            String output = "";

            if (args.length == 0) {
                commandSender.sendMessage(TextFormat.GOLD + "--NovaMobs 1.1--");
                commandSender.sendMessage(TextFormat.GREEN + "/mob summon <mob>" + TextFormat.YELLOW + "- Spawn Mob");
                commandSender.sendMessage(TextFormat.GREEN + "/mob removemobs" + TextFormat.YELLOW + "- Remove all Mobs");
                commandSender.sendMessage(TextFormat.GREEN + "/mob removeitems" + TextFormat.YELLOW + "- Remove all items on ground");
                commandSender.sendMessage(TextFormat.RED + "/mob version" + TextFormat.YELLOW + "- Show MobPlugin Version");
                commandSender.sendMessage(TextFormat.RED + "/mob info" + TextFormat.YELLOW + "- Show info result");
            } else {
                switch (args[0]) {
                    case "summon":
                        String mob = args[1];
                        Player playerThatSpawns = null;

                        if (args.length == 3) {
                            playerThatSpawns = this.getServer().getPlayer(args[2]);
                        } else {
                            playerThatSpawns = (Player) commandSender;
                        }

                        if (playerThatSpawns != null) {
                            Position pos = playerThatSpawns.getPosition();

                            Entity ent;
                            if ((ent = NovaMobs.create(mob, pos)) != null) {
                                ent.spawnToAll();
                                output += "Spawned " + mob + " to " + playerThatSpawns.getName();
                            } else {
                                output += "Unable to spawn " + mob;
                            }
                        } else {
                            output += "Unknown player " + (args.length == 3 ? args[2] : ((Player) commandSender).getName());
                        }
                        break;
                    case "removemobs":
                        int count = 0;
                        for (Level level : getServer().getLevels().values()) {
                            for (Entity entity : level.getEntities()) {
                                if (entity instanceof BaseEntity) {
                                    entity.close();
                                    count++;
                                }
                            }
                        }
                        output += "Removed " + count + " entities from all levels.";
                        break;
                    case "removeitems":
                        count = 0;
                        for (Level level : getServer().getLevels().values()) {
                            for (Entity entity : level.getEntities()) {
                                if (entity instanceof EntityItem && entity.isOnGround()) {
                                    entity.close();
                                    count++;
                                }
                            }
                        }
                        output += "Removed " + count + " items on ground from all levels.";
                        break;
                    case "version":
                        commandSender.sendMessage(TextFormat.GREEN + "Version > 1.1 working with MCPE 1.1");//Todo Automatic Updater?
                        break;
                    default:
                        output += "Unkown command.";
                        break;
                }
            }

            commandSender.sendMessage(output);
        }
        return true;

    }

    /**
     * @param type
     * @param source
     * @param args
     * @return
     */
    public static Entity create(Object type, Position source, Object... args) {
        FullChunk chunk = source.getLevel().getChunk((int) source.x >> 4, (int) source.z >> 4, true);

        if (!chunk.isGenerated()) {
            chunk.setGenerated();
        }

        if (!chunk.isPopulated()) {
            chunk.setPopulated();
        }

        if (chunk.getEntities().size() > 10) {
            FileLogger.debug(String.format("Not spawning mob because the chunk already has too many mobs!"));
            return null;
        }

        CompoundTag nbt = new CompoundTag().putList(new ListTag<DoubleTag>("Pos").add(new DoubleTag("", source.x)).add(new DoubleTag("", source.y)).add(new DoubleTag("", source.z)))
                .putList(new ListTag<DoubleTag>("Motion").add(new DoubleTag("", 0)).add(new DoubleTag("", 0)).add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation").add(new FloatTag("", source instanceof Location ? (float) ((Location) source).yaw : 0))
                        .add(new FloatTag("", source instanceof Location ? (float) ((Location) source).pitch : 0)));

        return Entity.createEntity(type.toString(), chunk, nbt, args);
    }

}
