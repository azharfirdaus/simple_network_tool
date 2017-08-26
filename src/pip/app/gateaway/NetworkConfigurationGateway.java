/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.app.gateaway;

import java.io.IOException;
import java.util.List;
import pip.app.gateaway.InternetProtocol.InvalidIpAdderssV4FormatException;
import pip.app.process.NotInvokedProcessYetException;

/**
 *
 * @author User
 */
public interface NetworkConfigurationGateway {
    public String[] hostname() throws IOException, NotInvokedProcessYetException;
    public String[] interfaceIdentifiers() throws IOException, NotInvokedProcessYetException;
    public NetworkConfigurationPresenter createNetworkPresenter(String interfaceIdentifier) throws IOException, 
            NotInvokedProcessYetException, InvalidIpAdderssV4FormatException;
    public InternetProtocol[] listHost
        (InternetProtocol network, InternetProtocol internetProtocolPresenter0) 
                throws IOException, NotInvokedProcessYetException, InvalidIpAdderssV4FormatException;
}
