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
public class InternetProtocolFactory {
    
    public static InternetProtocol makeNetworkIpAddress(InternetProtocol host, InternetProtocol netmask){
        InternetProtocol ipNetwork = new InternetProtocol();
        for(int i = 0; i < 4; i++)
            ipNetwork.segments[i] = host.segments[i] & netmask.segments[i];
        return ipNetwork;
    }
}
