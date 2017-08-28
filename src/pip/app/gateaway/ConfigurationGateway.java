/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.app.gateaway;

import java.io.IOException;
import pip.app.gateaway.InternetProtocol.InvalidIpAdderssV4FormatException;
import pip.app.process.NotInvokedPythonProcessYetException;

/**
 *
 * @author User
 */
public interface ConfigurationGateway {
    public String[] hostname() throws IOException, NotInvokedPythonProcessYetException;
    public String[] interfaceIdentifiers() throws IOException, NotInvokedPythonProcessYetException;
    public NetworkConfigurationPresenter createNetworkPresenter(String interfaceIdentifier) throws IOException, 
            NotInvokedPythonProcessYetException, InvalidIpAdderssV4FormatException;
    public InternetProtocol[] listHost
        (InternetProtocol network, InternetProtocol internetProtocolPresenter0) 
                throws IOException, NotInvokedPythonProcessYetException, InvalidIpAdderssV4FormatException;
}
