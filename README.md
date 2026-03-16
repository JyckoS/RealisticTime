# RealisticTime
https://www.spigotmc.org/resources/realistic-time-1-8-8-1-14-4-ready-asynchronous-lightweight.62099/
RealisticTime is a lightweight plugin that synchronizes in-game time with real-world time in selected worlds.

This allows Minecraft worlds to reflect the actual time of a chosen timezone, creating a more immersive and realistic experience for players.


---

## Features

- Synchronize real-world time with in-game time
- Enable synchronization only for specific worlds
- Asynchronous time updates
- Lightweight and performance friendly
- Timezone support (choose your own region)
- Configurable update interval
- No database required

---

## Commands

| Command | Description | Permission |
|-------|-------------|------------|
| `/realtime` | Check current synchronization status | `timer.check` |
| `/realtime help` | Show help menu | `timer.admin` |
| `/realtime set` | Set time manually | `timer.admin` |
| `/realtime reload` | Reload configuration | `timer.admin` |
| `/realtime time` | Display current synced time | `timer.admin` |

---

## Installation

1. Download the plugin `.jar`
2. Place the file inside your server's `plugins` folder
3. Restart the server (or load using a plugin manager)
4. Open `config.yml`
5. Configure your desired **timezone**
6. Add the worlds you want synchronization enabled for
7. Run the command:

```
/realtime reload
```

Setup complete.

---

## Configuration

Example `config.yml`

```yaml
timezone: "Asia/Tokyo"

enabled-worlds:
  - world
  - world_nether

update-interval: 20
```

---

## Requirements

- Java 8 or higher
- Server with Spigot API
---

## Contact

Feel free to reach out if you have questions or suggestions.

- Facebook
- SpigotMC Profile
- LINE: `jyckosianjaya`
- Discord: `jyckos#1275`

---

## Support

If you enjoy the plugin and want to support development, donations are greatly appreciated.

PayPal support link available.

---

## License

This project is released under the MIT License.
