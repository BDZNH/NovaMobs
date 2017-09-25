package com.pikycz.novamobs.command;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityItem;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import com.pikycz.novamobs.NovaMobs;
import com.pikycz.novamobs.entities.BaseEntity;

/**
 * @author PikyCZ
 */
public class Command extends PluginBase {

    /**
     * @param commandSender
     * @param cmd
     * @param label
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender commandSender, cn.nukkit.command.Command cmd, String label, String[] args) {
        if (cmd.getName().toLowerCase().equals("mob")) {

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
                                commandSender.sendMessage("Spawned " + mob + " to " + playerThatSpawns.getName());
                            } else {
                                commandSender.sendMessage("Unable to spawn " + mob);
                            }
                        } else {
                            commandSender.sendMessage("Unknown player " + (args.length == 3 ? args[2] : ((Player) commandSender).getName()));
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
                        commandSender.sendMessage("Removed " + count + " entities from all levels.");
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
                        commandSender.sendMessage("Removed " + count + " items on ground from all levels.");
                        break;
                    case "version":
                        commandSender.sendMessage(TextFormat.GREEN + "Version > 1.2 working with MCPE 1.1.5");//Todo Automatic Updater?
                        break;
                    default:
                        commandSender.sendMessage("Unkown command.");
                        break;
                }
            }
        }
        return true;

    }

}
