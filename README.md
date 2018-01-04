# deepudp
## 说明
这是一个udp使用的建议库，支持手法udp信号，并提供了python脚本进行测试，目前只有收发两个功能，如有用户有需求，可以留言联系我
## 使用
## 接口
### 初始化

```
DeepUdp deepUdp =  DeepUdp.getInstance().init("目标主机的ip地址",8222, 100);
```
其中8222为端口，100为收发字节流大小

### 发送

```
   deepUdp.send("test");
```

### 收取

```
 deepUdp.beginReceive(new UDPCallback() {
                @Override
                public void callback(String info) {
                   
                }
            });
```
callback为收到消息的回调，info为收到的内容
如果不关闭收取，会持续监听，如果希望关闭，可以调用：
```
 deepUdp.end();
```
### 关闭
如果需要关闭连接，可以使用

```
deepUdp.close()
```
调用关闭后，如果想继续使用，需要重新初始化

## 测试
在工程下有一个脚本udpp.py
运行该脚本可以进行测试
运行：

```
./udpp.py -f test
```
模拟发送信号
运行：

```
./udpp.py -r
```
模拟监听收取信号

# 特别说明
如果有新的需求可以报给我补充，谢谢。
也欢迎关注我的公众号
![](http://upload-images.jianshu.io/upload_images/1483670-a6007f9989aa35a3.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
