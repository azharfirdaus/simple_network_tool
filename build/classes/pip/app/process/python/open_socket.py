#!/usr/bin/env python2
#encoding: UTF-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

import socket
import sys

#create a TCP/IP socket
socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
socket.settimeout(30)
#bind the socket to the port
server_address = (sys.argv[1], 10000)
print("starting up on %s port %s" % server_address)
print("set timeout 30 second")
socket.bind(server_address)

#Listen for incoming connections
socket.listen(1)
while True:
    #wait for a connections
    print ('waiting for a connections')
    connection, client_address = socket.accept()
    
    try:
        print ('connection from', client_address)
        #receive the data in small chunks and retransmit it
        while True:
            data = connection.recv(10)
            if not data:
                break
            elif data == 'killsrv'.encode('utf-8'):
                print("killsrv kills server")
                connection.close()
                socket.close()
                
            print ("receive: %s" % data)
                
            
    finally:
        connection.close()