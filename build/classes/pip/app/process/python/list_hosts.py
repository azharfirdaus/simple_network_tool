#! /usr/bin/python

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

import sys
from netaddr import *

ip_network = sys.argv[1]
netmask_bits = IPAddress(str(sys.argv[2])).netmask_bits() # convert 255.255.255.0 to 24
for ip_address in IPNetwork("{}/{}".format(ip_network, netmask_bits)).iter_hosts():
    print(ip_address)
    
