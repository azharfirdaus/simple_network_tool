#! /usr/bin/python

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

import ping
import socket
import sys
from netaddr import *

def simple_ping(address):
    try:
        ping.verbose_ping(address, count=4)
    except socket.error, e:
        print "Ping Error:", e
        
simple_ping(sys.argv[1])
    
