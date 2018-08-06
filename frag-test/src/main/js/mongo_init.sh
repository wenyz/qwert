#!bin/bash

#以无权限控制的方式启动服务（--fork -f并不是必须的，是我自己的需求在用，有需要自行查询其他参数，）
mongod --fork -f /path/to/mongod.conf

#使用mongo [options] [db address] [file names (ending in .js)]命令执行js脚本操作数据库，并写入日志
mongo --host 127.0.0.1:27017 admin mongo_init.js

#安全地杀掉mongod进程
pkill mongod

#开启权限控制
mongo --auth --fork -f /path/to/mongod.conf