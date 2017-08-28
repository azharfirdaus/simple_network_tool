/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.main.interactor;

import java.io.IOException;
import pip.app.gateaway.InternetProtocol;
import pip.app.gateaway.InternetProtocol.InvalidIpAdderssV4FormatException;
import pip.app.gateaway.NetworkConfigurationPresenter;
import pip.app.gateaway.ConfigurationGatewayImpl;
import pip.app.process.NotInvokedPythonProcessYetException;
import pip.app.gateaway.ConfigurationGateway;

/**
 *
 * @author User
 */
public class Interactor{

    String[] getHostname() throws IOException, NotInvokedPythonProcessYetException {
        ConfigurationGateway gateaway = new ConfigurationGatewayImpl();
        return gateaway.hostname();
    }

    String[] getNetworkInterfaceIdentifier() throws IOException, NotInvokedPythonProcessYetException {
        ConfigurationGateway gateaway = new ConfigurationGatewayImpl();
        return gateaway.interfaceIdentifiers();
    }
    
    NetworkConfigurationPresenter getNetworkInfoPresenter(String command) throws IOException, 
            NotInvokedPythonProcessYetException, InvalidIpAdderssV4FormatException {
        ConfigurationGateway gateaway = new ConfigurationGatewayImpl();
        return gateaway.createNetworkPresenter(command);
    }

    InternetProtocol[] getAllHosts(String network, String netmask) throws IOException, 
            InvalidIpAdderssV4FormatException, NotInvokedPythonProcessYetException {
        ConfigurationGateway gateaway = new ConfigurationGatewayImpl();
        return gateaway.listHost(new InternetProtocol(network), new InternetProtocol(netmask));
    }
    
    
}
