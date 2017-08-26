/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.main.boundary.graphical;

import pip.app.gateaway.InternetProtocol;
import pip.app.gateaway.NetworkConfigurationPresenter;

/**
 *
 * @author User
 */
public interface ChangeAcceptor {
    public void onDisplayNetworkInterfaceIdentifier(Object identifier[]);
    public void onDetailNetworkInterfaceIdentifier(NetworkConfigurationPresenter presenter);
    public void onDisplayAllHosts(Object hosts[]);
}
