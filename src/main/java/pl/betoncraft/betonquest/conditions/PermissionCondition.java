/**
 * BetonQuest - advanced quests for Bukkit
 * Copyright (C) 2016  Jakub "Co0sh" Sapalski
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.betoncraft.betonquest.conditions;

import pl.betoncraft.betonquest.BetonQuest;
import pl.betoncraft.betonquest.Instruction;
import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.Condition;
import pl.betoncraft.betonquest.metadata.DefaultTypeMetadata;
import pl.betoncraft.betonquest.metadata.TypeMetadata;
import pl.betoncraft.betonquest.metadata.datatype.Data;
import pl.betoncraft.betonquest.metadata.format.Argument;
import pl.betoncraft.betonquest.metadata.format.Format;
import pl.betoncraft.betonquest.metadata.text.StringText;
import pl.betoncraft.betonquest.utils.PlayerConverter;

/**
 * Requires the player to have specified permission node
 *
 * @author Jakub Sapalski
 */
public class PermissionCondition extends Condition {

	private final String permission;

	public PermissionCondition(Instruction instruction) throws InstructionParseException {
		super(instruction);
		permission = instruction.next();
	}

	@Override
	public boolean check(String playerID) {
		if (PlayerConverter.getPlayer(playerID).hasPermission(permission)) {
			return true;
		}
		return false;
	}

	public static TypeMetadata getMetadata() {
		return new DefaultTypeMetadata()
		        .setName(new StringText("Permission"))
		        .setDescription(new StringText("Checks if the player has a permission"))
		        .setPlugin(BetonQuest.getInstance())
		        .setFormat(Format.argument()
		                .require(new Argument()
		                        .setName(new StringText("Permission"))
		                        .setDescription(new StringText("Permission string to check"))
		                        .setData(Data.string())));
	}

}
