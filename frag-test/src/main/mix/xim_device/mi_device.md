# 局域网控制小米设备
## 主要有两种思路
### 第一种是通过复制发送的数据包，模拟发送报文控制
大概尝试了一下，没有成功。
中间使用了中间人的[ARP嗅探](https://blog.csdn.net/u013752202/article/details/78568995)，
虽然成功截取到了数据包，但是因为加密的，没办法解析，只好换一种思路。
### 第二种是通过查找小米协议，然后发现竟然有[开源实现](https://paper.seebug.org/616/)
[python-miio](https://github.com/rytilahti/python-miio)

sudo apt-get install build-essential lib sqlite3-dev sqlite3 bzip2 libbz2-dev libssl-dev openssl libgdbm-dev liblzma-dev libreadline-dev libncursesw5-dev