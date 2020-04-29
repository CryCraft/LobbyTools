# Lobbytools
Lobby plugin based on papertools

## Features
- [x] lobby items: server selector
- [x] commands: spawn, servers, lobbytools
- [x] void spawn: send players to the papertools spawn when they fall into the void

## TODO
- [ ] Cosmetics

## Permissions
| feature/command | permission |
| --- | --- |
| lobby inventory overwrite | lobbytools.overwrite |
| /servers | lobbytools.command.servers |
| /lobbytools | lobbytools.command.lobbytools |
| /spawn | lobbytools.command.spawn |

## Commands

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