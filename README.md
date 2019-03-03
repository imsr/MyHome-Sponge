## MyHome 3.0.0

**A Home warp point plugin for Sponge modding platform**

>  Version 3.0.0 alpha, works with SpongeAPI 7.x

---

ChangeLog | How to Configure this Plugin | Commands description | Bug Reports

---

### Brief Overview

MyHome is a simple to use but highly flexible home teleporation point plugin. It allows server owners to control many aspects of when their users can teleport to their home. This can be by requiring them to wait for a period either as the teleport warms up or they have to wait a period between uses of the /home function. The major functions of MyHome can also be set to charge the users per use through one of the major economy plugins.

For a quick permissions set-up, give your users **myhome.home.\*** and your admins **myhome.\***

---

### Installation

Simply put `MyHome-x.x.x.jar` in your plugins folder. When you start up your server, a configuration file will be generated and placed in a folder called "MyHome". You can edit this configuration file to adjust the settings for MyHome to your liking.

Configuration Options for MyHome

---

### Commands & Permissions:

#### Basic Commands:

| Command      | Permission                 | Description                             |
| ------------ | -------------------------- | --------------------------------------- |
| /home        | *myhome.home.basic.home*   | Takes you to your home                  |
| /home set    | *myhome.home.basic.set*    | Sets your home to your current position |
| /home delete | *myhome.home.basic.delete* | Deletes your home                       |
| /home point  | —                          | Point your compass towards your home    |
| /home help   | —                          | Display help                            |



#### Social Commands:

| Command               | Permission                 | Description                        |
| --------------------- | -------------------------- | ---------------------------------- |
| /home <name>          | *myhome.home.soc.others*   | Visit <name>'s home                |
| /home list            | *myhome.home.soc.list*     | Displays whose homes you can visit |
| /home ilist           | *myhome.home.soc.list*     | Displays who can visit your home   |
| /home invite <name>   | *myhome.home.soc.invite*   | Invites <name> to your house       |
| /home uninvite <name> | *myhome.home.soc.uninvite* | Uninvites <name> to your house     |
| /home public          | *myhome.home.soc.public*   | Makes your house public            |
| /home private         | *myhome.home.soc.private*  | Makes your house private           |



#### Admin Commands:

| Command                      | Permission               | Description                                            |
| ---------------------------- | ------------------------ | ------------------------------------------------------ |
| /home listall                | *myhome.admin.home.list* | Allow admins to list all homes                         |
| /home clear <playername> | *myhome.admin.home.delete* | Allow an admin to delete playername's home             |
| /home convert                | *myhome.admin.convert*   | Converts the homes from very old homes.txt into the DB |
| /home reload                 | *myhome.admin.reload*    | Do not use this for swapping to MySQL                  |
| /home rename <oldname> <newname> | *myhome.admin.home.rename* | Rename owner of a home warp                            |

---

### Permissions

In addition to specific command permissions there are also the following:

#### Alias Permissions:

- (*myhome.user*) - Grants user permissions
- (*myhome.admin*) - Grants admin permissions

#### Economy Permissions:

- (*myhome.econ.free.\**) - Allow /sethome and /home usage for free
- (*myhome.econ.free.sethome*) - Allow /sethome usage for free
- (*myhome.econ.free.home*) - Allow /home usage for free

#### Bypassing Timers/Limits Permisions:

- (*myhome.bypass.\**) - Bypass all limits (cooldowns, warmups and bed usage etc)
- (*myhome.bypass.cooldown*) - Permission to bypass /home cooldowns
- (*myhome.bypass.warmup*) - Permission to bypass /home warmup
- (*myhome.bypass.sethomecool*) - Permission to bypass /sethome cooldown
- (*myhome.bypass.bedsethome*) - Permission to use /sethome when bed usage is forced
- (*myhome.bypass.dmgaborting*) - Do not abort a /home warmup when receiving or dealing damage

#### Admin Permissions:

- (*myhome.admin.\**) - Has access to all admin commands in MyHome
- (*myhome.admin.home.any*) - Admin can /home to anyone's home
- (*myhome.admin.home.rename*) - Admin can change ownership of a home
