/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.app.gateaway;

/**
 *
 * @author User
 */
public class NetworkConfigurationPresenter {
    protected String interfaceIdentifier = "";
    protected String physicalAddress = "";
    public InternetProtocol host = new InternetProtocol();
    public InternetProtocol netmask = new InternetProtocol();
    public InternetProtocol broadcast = new InternetProtocol();
    public InternetProtocol network = new InternetProtocol();
    protected String hostv6 = "";
    protected String netmask6 = "";
    protected String broadcastV6 = "";

    @Override
    public String toString() {
        String toString = "";
        toString += String.format("interface: %s\n", interfaceIdentifier);
        toString += String.format("physical: %s\n", physicalAddress);
        toString += String.format("network: %s\n", network.toString());
        toString += String.format("host: %s\n", host.toString());
        toString += String.format("netmask: %s\n", netmask.toString());
        toString += String.format("broadcast: %s\n", broadcast.toString());
        toString += String.format("host v6: %s\n", hostv6);
        toString += String.format("netmask v6: %s\n", netmask6);
        toString += String.format("broadcast v6: %s", broadcastV6);
        return toString;
    }
    
    

    public String interfaceIdentifier() {
        return interfaceIdentifier;
    }

    public String physicalAddress() {
        return physicalAddress;
    }

    public String hostV6() {
        return hostv6;
    }

    public String netmaskV6() {
        return netmask6;
    }

    public String broadcastV6() {
        return broadcastV6;
    }
}
