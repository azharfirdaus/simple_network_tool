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
import pip.app.gateaway.NetworkConfigurationGatewayImpl;
import pip.app.process.NotInvokedProcessYetException;
import pip.app.gateaway.NetworkConfigurationGateway;

/**
 *
 * @author User
 */
public class Interactor{

    public String[] getHostname() throws IOException, NotInvokedProcessYetException {
        NetworkConfigurationGateway gateaway = new NetworkConfigurationGatewayImpl();
        return gateaway.hostname();
    }

    public String[] getNetworkInterfaceIdentifier() throws IOException, NotInvokedProcessYetException {
        NetworkConfigurationGateway gateaway = new NetworkConfigurationGatewayImpl();
        return gateaway.interfaceIdentifiers();
    }
    
    public NetworkConfigurationPresenter getNetworkInfoPresenter(String command) throws IOException, 
            NotInvokedProcessYetException, InvalidIpAdderssV4FormatException {
        NetworkConfigurationGateway gateaway = new NetworkConfigurationGatewayImpl();
        return gateaway.createNetworkPresenter(command);
    }

    public InternetProtocol[] getAllHosts(String network, String netmask) throws IOException, 
            InvalidIpAdderssV4FormatException, NotInvokedProcessYetException {
        NetworkConfigurationGateway gateaway = new NetworkConfigurationGatewayImpl();
        return gateaway.listHost(new InternetProtocol(network), new InternetProtocol(netmask));
    }
    
    
}
