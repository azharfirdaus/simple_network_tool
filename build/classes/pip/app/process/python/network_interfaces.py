#! /usr/bin/python

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

import netifaces
network_interfaces = netifaces.interfaces()
for e in network_interfaces: print (e)