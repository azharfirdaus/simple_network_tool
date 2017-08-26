/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.app.gateaway;

import pip.app.process.ProcessInvoker;
import java.io.IOException;
import pip.app.gateaway.InternetProtocol.InvalidIpAdderssV4FormatException;
import pip.app.process.NotInvokedProcessYetException;
import pip.app.process.ProcessInvokerImpl;

/**
 *
 * @author User
 */
public class NetworkConfigurationGatewayImpl implements NetworkConfigurationGateway {
    
    @Override
    public String[] hostname() throws IOException, NotInvokedProcessYetException {
        ProcessInvoker invoker = new ProcessInvokerImpl();
        invoker.exec("python", 
                "C:\\Users\\User\\Documents\\NetBeansProjects\\NetworkTool\\src\\pip\\app\\process\\python\\read_ipaddr.py");
        return invoker.toMessages();
    }
    
    @Override
    public String[] interfaceIdentifiers() throws IOException, NotInvokedProcessYetException{
        ProcessInvoker invoker = new ProcessInvokerImpl();
        invoker.exec("python", 
                "C:\\Users\\User\\Documents\\NetBeansProjects\\NetworkTool\\src\\pip\\app\\process\\python\\network_interfaces.py");
        return invoker.toMessages();
    }

    @Override
    public NetworkConfigurationPresenter createNetworkPresenter(String interfaceIdentifier) throws IOException, 
            NotInvokedProcessYetException, InvalidIpAdderssV4FormatException {
        NetworkConfigurationPresenter presenter = new NetworkConfigurationPresenter();
        presenter.interfaceIdentifier = interfaceIdentifier;
        presenter.physicalAddress = getNetworkConfiguration(interfaceIdentifier, "AF_LINK","addr");
        
        presenter.host.setVerboseAddress(getNetworkConfiguration(interfaceIdentifier, "AF_INET","addr"));
        presenter.netmask.setVerboseAddress(getNetworkConfiguration(interfaceIdentifier, "AF_INET","netmask"));
        presenter.broadcast.setVerboseAddress(getNetworkConfiguration(interfaceIdentifier, "AF_INET","broadcast"));
        
        presenter.network = InternetProtocolFactory.makeNetworkIpAddress(presenter.host, presenter.netmask);
        
        presenter.hostv6 = getNetworkConfiguration(interfaceIdentifier, "AF_INET6","addr");
        presenter.netmask6 = getNetworkConfiguration(interfaceIdentifier, "AF_INET6","netmask");
        presenter.broadcastV6 = getNetworkConfiguration(interfaceIdentifier, "AF_INET6","broadcast");
        
        return presenter;
    }

    private String getNetworkConfiguration(String... args) throws IOException, NotInvokedProcessYetException {
        ProcessInvoker invoker = new ProcessInvokerImpl();
        invoker.exec("python", 
                "C:\\Users\\User\\Documents\\NetBeansProjects\\NetworkTool\\src\\pip\\app\\process\\python\\network_conf.py", 
                args[0], args[1], args[2]);
        String messages[] = invoker.toMessages();
        return messages.length < 2 ? messages[0] : "0.0.0.0";
    }

    @Override
    public InternetProtocol[] listHost
        (InternetProtocol network, InternetProtocol netmask) throws IOException, 
                NotInvokedProcessYetException, InvalidIpAdderssV4FormatException {
        ProcessInvoker invoker = new ProcessInvokerImpl();
        invoker.exec("python",
                "C:\\Users\\User\\Documents\\NetBeansProjects\\NetworkTool\\src\\pip\\app\\process\\python\\list_hosts.py",
                network.toString(), netmask.toString());
        String messages[] = invoker.toMessages();
        InternetProtocol hosts[] = new InternetProtocol[messages.length];
        for(int i = 0; i < messages.length; i++)
            hosts[i] = new InternetProtocol(messages[i]);
        return hosts;
        
    }
    
    
    

}
