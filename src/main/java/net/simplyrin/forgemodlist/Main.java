package net.simplyrin.forgemodlist;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by SimplyRin on 2018/05/31.
 *
 * Copyright (c) 2018 SimplyRin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
@Mod(modid = "ForgeModList", version = "1.0", acceptableRemoteVersions = "*", acceptedMinecraftVersions = "[1.8,1.8.8,1.8.9]")
public class Main {

	private CommandBase commandBase = new CommandBase() {

		@Override
		public String getCommandName() {
			return "mods";
		}

		@Override
		public String getCommandUsage(ICommandSender sender) {
			return "/mods";
		}

		@Override
		public boolean canCommandSenderUseCommand(ICommandSender sender) {
			return true;
		}

		@Override
		public int getRequiredPermissionLevel() {
			return 0;
		}

		@Override
		public void processCommand(ICommandSender sender, String[] args) {
			List<ModContainer> modList = Loader.instance().getModList();

			String mods = "";
			for(ModContainer modContainer : modList) {
				mods += modContainer.getModId() + "§f, §a";
			}

			sender.addChatMessage(new ChatComponentText("§7[§cForgeModList§7] §r§fMods: (" + modList.size() + ") §a" + mods.substring(0, (mods.length() - 4))));
		}

	};

	@SideOnly(Side.CLIENT)
	@EventHandler
	public void init(FMLInitializationEvent event) {
		if(MinecraftForge.MC_VERSION.equals("1.8") || MinecraftForge.MC_VERSION.equals("1.8.8") || MinecraftForge.MC_VERSION.equals("1.8.9")) {
			ClientCommandHandler.instance.registerCommand(this.commandBase);
		}
	}

	@SideOnly(Side.SERVER)
	@EventHandler
	public void init(FMLServerStartingEvent event) {
		if(MinecraftForge.MC_VERSION.equals("1.8") || MinecraftForge.MC_VERSION.equals("1.8.8") ||  MinecraftForge.MC_VERSION.equals("1.8.9")) {
			event.registerServerCommand(this.commandBase);
		}
	}

}
