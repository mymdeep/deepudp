#!/usr/bin/env python
# coding:utf-8
import socket
import time
import sys
import getopt
HOST = '30.30.140.34'
PORT = 8222
def udp_send(funcStr):


    BUFSIZ = 128
    ADDR = (HOST, PORT)

    # 创建一个服务器端UDP套接字
    udpServer = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    # 绑定服务器套接字
    udpServer.bind(ADDR)
    addr = ''
    # 向客户端发送数据
    print funcStr
    udpServer.sendto(funcStr, ('30.30.140.25', PORT))
    print '.send to:', addr

def udp_rec():

    BUFSIZ = 128
    ADDR = (HOST, PORT)

    # 创建一个服务器端UDP套接字
    udpServer = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    # 绑定服务器套接字
    udpServer.bind(ADDR)
    addr = ''
    # 向客户端发送数据

    while True:
       data, addr = udpServer.recvfrom(2048)
       if not data:
            print "client has exist"
            break
       print "received:", data, "from", addr

try:
    options, args = getopt.getopt(sys.argv[1:], "f:r")
except getopt.GetoptError:
    sys.exit()
for name, value in options:
    print name+'   '+value
    if name in ("-f"):
        udp_send(value)
    if name in ("-r"):
            udp_rec()



