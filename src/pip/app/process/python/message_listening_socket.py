#!/usr/bin/env python2
#encoding: windows-1252

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

import socket
import sys

# create a tcp/ip socket
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# connect the socket to the port where is listening
server_address = (sys.argv[1], 10000)
print ("connection to %s port %s" % server_address)
sock.connect(server_address)

try:
    #send data
    message = sys.argv[2]
    print  ("sending %s" % message)
    sock.sendall(message.encode('utf-8'))
    
#    amount_received = 0
#    amount_expected = len(message)
#    
#    while amount_received < amount_expected:
#        data = sock.recv(10)
#        #amount_received += len(data)
#        print ("received %s" % data)

finally:
    print ("closing socket");
    sock.close()