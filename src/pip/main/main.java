/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.main;

import pip.main.interactor.IODelivery;
import java.io.IOException;
import pip.app.gateaway.InternetProtocol.InvalidIpAdderssV4FormatException;
import pip.app.process.NotInvokedPythonProcessYetException;
import pip.main.interactor.IODelivery.CommandIsNotFoundException;
import pip.main.interactor.IODelivery.EmptyCommandFoundException;


/**
 *
 * @author User
 */
public class main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws pip.app.process.NotInvokedPythonProcessYetException
     * @throws pip.main.interactor.IODelivery.CommandIsNotFoundException
     * @throws pip.main.interactor.IODelivery.EmptyCommandFoundException
     * @throws pip.app.gateaway.InternetProtocol.InvalidIpAdderssV4FormatException
     */
    public static void main(String[] args) throws IOException, NotInvokedPythonProcessYetException, CommandIsNotFoundException, 
            InvalidIpAdderssV4FormatException, EmptyCommandFoundException, IODelivery.InvalidParameterFoundException {
        IODelivery io = new IODelivery();
        io.identifyCommand("ipaddr");
        io.identifyCommand("interfaces");
        io.identifyCommand("network_interface","{DE5DD19E-C88D-4B89-B17C-0D188A9C97A8}");
        io.identifyCommand("list_hosts","192.168.1.0","255.255.255.0");
    }
    
}
