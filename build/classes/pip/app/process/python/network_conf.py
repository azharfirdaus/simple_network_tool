#! /usr/bin/python

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

import netifaces
import sys
def network_conf(network_interface, interface_serial, conf):
    net_interfaces = netifaces.ifaddresses(network_interface)
    if interface_serial == "AF_LINK":
        print(net_interfaces[netifaces.AF_LINK][0][conf])
    elif interface_serial == "AF_INET":
        print(net_interfaces[netifaces.AF_INET][0][conf])
    elif interface_serial == "AF_INET6":
        print(net_interfaces[netifaces.AF_INET6][0][conf])
        
network_conf(sys.argv[1],sys.argv[2], sys.argv[3])    