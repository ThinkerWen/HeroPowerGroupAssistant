<div align="center">
    <img src="https://www.hive-net.cn/Program/HeroPowerMiniProgram/logo_circle.png" width="200" alt="HeroPower"/>
    <h1>HeroPowerGroupAssistant</h1>



[![PackageVersion](https://img.shields.io/badge/code-Github-red)](https://github.com/Raptor-wxw/HeroPowerGroupAssistant)
[![PackageVersion](https://img.shields.io/badge/java-11-orange)](https://www.oracle.com/java/technologies/downloads/#java11)
[![PackageVersion](https://img.shields.io/badge/mirai-2.10.1-blue)](https://github.com/mamoe/mirai)
[![PackageVersion](https://img.shields.io/badge/suggestion-issue-blue)](https://github.com/Raptor-wxw/HeroPowerGroupAssistant/issues)
[![PackageVersion](https://img.shields.io/badge/download-release-blue)](https://github.com/Raptor-wxw/HeroPowerGroupAssistant/releases)
</div>

----

## 介绍

**HeroPowerGroupAssistant是一款王者荣耀查战力的QQ群机器人助手，非常好用。可以查询王者荣耀最低战力省、市、区的位置及其战力分。数据每日凌晨6点更新，数据更新及时，结果准确。**
**使用方便，只需要将插件引入mirai主程序plugins文件夹中即可自动加载，支持机器人主人配置、群聊权限配置，防止恶意调用。**

**对您有帮助可以点个Star，谢谢**！

****

## 安装

本项目基于[Mirai](https://github.com/mamoe/mirai)开发，项目代码90%采用Java语言开发。开发者可以阅读[源代码](https://github.com/Raptor-wxw/HeroPowerGroupAssistant/tree/master/src/main/java/org/wzry/heropower)，Fork此项目并应用提交您的更改。
用户安装请查看[Mirai用户文档](https://github.com/mamoe/mirai/blob/dev/docs/UserManual.md)，安装纯[控制台版本](https://github.com/mamoe/mirai/blob/dev/docs/ConsoleTerminal.md)，或着[图形化界面版本](https://github.com/sonder-joker/mirai-compose/releases)，或者[安卓手机端版本](https://github.com/mzdluo123/MiraiAndroid)。若登录过程弹出验证码，请使用[滑动验证助手](https://github.com/mzdluo123/TxCaptchaHelper)。

### Mirai安装地址

[Linux控制台](https://github.com/iTXTech/mcl-installer/releases/download/v1.0.7/mcl-installer-1.0.7-linux-amd64-musl)

[MacOS控制台](https://github.com/iTXTech/mcl-installer/releases/download/v1.0.7/mcl-installer-1.0.7-macos-amd64)

[MacOS图形化界面](https://github.com/sonder-joker/mirai-compose/releases/download/v1.1.5/mirai-compose-1.1.5.dmg)

[Windows控制台](https://github.com/iTXTech/mcl-installer/releases/download/v1.0.7/mcl-installer-1.0.7-windows-x86.exe)

[Windows图形话界面](https://github.com/sonder-joker/mirai-compose/releases/download/v1.1.5/mirai-compose-1.1.5.msi)

[安卓App](https://github.com/mzdluo123/MiraiAndroid/releases)

### 插件安装方法

**安装好Mirai后，在Mirai安装的主路径中有plugins文件夹，将HeroPowerGroupAssistant-xxx.jar复制到plugins文件夹内即可。**
**装好插件第一次启动后会自动在Mirai安装的主路径中的config文件夹下生成org.wzry.heropower.HeroPower文件夹，文件夹内的HeroPower.yml为插件配置文件，按注释修改，添加机器人主人和群聊，即可在相应群聊中使用插件了。**

****

## 插件使用方式：

**输入：**<br>
**“查战力 英雄 区服”**

**例如：**<br>
**查战力 李白 安卓QQ**<br>
**查战力 李白 苹果微信**

****

## 插件指令：

| 触发词                | 含义                              |
| --------------------- | --------------------------------- |
| 查战力帮助            | 插件帮助提示                      |
| 开启战力查询          | 开启当前群查战力功能              |
| 关闭战力查询          | 关闭当前群查战力功能              |
| 添加插件管理员 @某人  | 添加被@对象为插件管理员（有空格） |
| 移除插件管理员 @某人  | 移除被@对象的插件管理员（有空格） |
| 加载HeroPower配置文件 | 重新加载配置文件                  |


****

## 示例：

![示例图片](https://s2.loli.net/2022/07/29/9FuyAZwzTnQCWXE.jpg)
