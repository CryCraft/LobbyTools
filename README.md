# Lobbytools
Lobby plugin based on papertools

## Features
- [x] lobby items: server selector
- [x] commands: spawn, servers
- [x] npcs: behaviors, skins, list all, preview, hide
- [x] portals: send players to servers via portals defined in the config
- [x] void spawn: send players to the papertools spawn when they fall into the void

## TODO
- [ ] Cosmetics
- [x] portals reload

## Permissions
| feature/command | permission |
| --- | --- |
| lobby inventory overwrite | lobbytools.overwrite |
| /servers | lobbytools.command.servers |
| /npc | lobbytools.command.npc |
| /lobbytools | lobbytools.command.lobbytools |
| /spawn | lobbytools.command.spawn |

## Commands

### npc

```sh
/npc list
```
* list all currently previewed and unhidden npcs
* list all npcs defined in the config

```sh
/npc preview <name>
```

* preview an npc 
* does not have a skin
* goes away when you reload the server or config

```sh
/npc hide <id>
```

* hide a previewed or config npc
* unhides on a server reload or config reload

### lobbytools

```sh
/lobbytools reload
```

* reloads the config
* removes al previewed npcs and unhides al hidden npcs

### spawn

```sh
/spawn
```

* send player to papertools spawn

### servers

```sh
/servers
```

* open servers gui