<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a id="readme-top"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/your-github-username/warnsystem">
  </a>

<h3 align="center">WarnSystem [+AutoBan] [+BanID]</h3>

<!-- ABOUT THE PROJECT -->
## About The Project

WarnSystem is a Minecraft plugin designed to manage player warnings effectively, featuring automatic banning after multiple warnings and detailed ban information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- FEATURES -->
## Features ✨

- **Warn Players**: Use the `/warn` command to issue warnings to players. ⚠️
- **View Player Info**: Display warnings, ban status, and online status of a player with the `/warns` command. 🔍
- **Automatic Ban**: Players are automatically banned from the server after three warnings. 🚫
- **Delete Warnings**: Remove warnings from players with the `/delwarn` command. 🗑️
- **Unban Players**: Unban players and remove all their warnings. 🔓
- **BanID and BanDate**: View the BanID and BanDate when displaying player information. 📅

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- INSTALLATION -->
## Installation 🚀

1. **Download** the latest version of the plugin from [SpigotMC](https://www.spigotmc.org/resources/warnsystem-autoban-banid.117845/). 📥
2. **Copy** the `.jar` file into the `plugins` folder of your Minecraft server. 📂
3. **Restart** your Minecraft server to activate the plugin. 🔄

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- COMMANDS AND PERMISSIONS -->
## Commands and Permissions ⚔️

### Commands

- **`/warn <player> <reason>`**  
  Warns the specified player and provides a reason for the warning.  
  Permission: `warn.command` 🛡️

- **`/warns <player>`**  
  Displays the warnings, BanID, BanDate, and ban status of the specified player.  
  Permission: `warns.command` 🕵️‍♂️

- **`/delwarn <player>`**  
  Deletes all warnings for the specified player.  
  Permission: `delwarn.command` 🗑️

- **`/unban <player>`**  
  Unbans the specified player and removes all warnings.  
  Permission: `unban.command` 🔓

### Permissions

- **`warn.command`** - Allows the use of the `/warn` command. 🛡️
- **`warns.command`** - Allows the use of the `/warns` command. 🕵️‍♂️
- **`delwarn.command`** - Allows the use of the `/delwarn` command. 🗑️
- **`unban.command`** - Allows the use of the `/unban` command. 🔓

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- WARNING BEHAVIOR -->
## Warning Behavior ⚠️

- After the **second warning**, the player will receive a message reminding them to follow the rules. 📜
- After the **third warning**, the player will be automatically banned from the server. 🚫

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- IMPORTANT NOTES -->
## Important Notes ℹ️

- The plugin is **not** BungeeCord compatible. It works only on servers without BungeeCord or with a single Minecraft instance. 🚫🔗

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License 📜

This plugin is licensed under the [MIT License](LICENSE). See the [LICENSE](LICENSE) file for details.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- SUPPORT -->
## Support 🤝

If you have any questions or issues, you can [create an issue on GitHub](https://github.com/your-github-username/warnsystem/issues) or visit the [discussion page](https://github.com/your-github-username/warnsystem/discussions).

<p align="right">(<a href="#readme-top">back to top</a>)</p>
