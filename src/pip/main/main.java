/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.main;

import pip.main.boundary.textual.IODelivery;
import java.io.IOException;
import pip.app.gateaway.InternetProtocol.InvalidIpAdderssV4FormatException;
import pip.app.process.NotInvokedProcessYetException;
import pip.main.adapter.CommandAdapter.CommandIsNotFoundException;
import pip.main.adapter.CommandAdapter.EmptyCommandFoundException;


/**
 *
 * @author User
 */
public class main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws pip.app.process.ProcessInvokerImpl.NotInvokedProcessYetException
     * @throws pip.main.adapter.CommandAdapter.CommandIsNotFoundException
     * @throws pip.main.adapter.CommandAdapter.EmptyCommandFoundException
     * @throws pip.app.gateaway.InternetProtocol.InvalidIpAdderssV4FormatException
     */
    public static void main(String[] args) throws IOException, NotInvokedProcessYetException, CommandIsNotFoundException, 
            InvalidIpAdderssV4FormatException, EmptyCommandFoundException {
        IODelivery io = new IODelivery();
        io.relayCommand("ipaddr");
        io.relayCommand("interfaces");
        io.relayCommand("network_conf","{DE5DD19E-C88D-4B89-B17C-0D188A9C97A8}");
        io.relayCommand("list_hosts","192.168.1.0","255.255.255.0");
    }
    
}
