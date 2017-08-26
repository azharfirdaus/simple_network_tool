#!/usr/bin/env python2
#encoding: UTF-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

import socket
import sys

#create a TCP/IP socket
socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
socket.settimeout(10)
#bind the socket to the port
server_address = (sys.argv[1], 10000)
print ("starting up on %s port %s" % server_address)
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
            data = connection.recv(100)
            print ('receive %s' % data)
            if data:
                print ('sending data back to the client')
                connection.sendall(data)
            else:
                print ('no more data from', client_address)
                break
    finally:
        connection.close()