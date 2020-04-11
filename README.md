# Lobbytools

## TODO

- [ ] Join lobby items
- [ ] Portals, portals config
- [ ] npc behavior enum

## Commands

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

```sh
/npc reload
```

* reloads the config
* removes al previewed npcs and unhides al hidden npcs