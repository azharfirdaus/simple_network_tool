/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networktool.main;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import pip.app.gateaway.InternetProtocol;
import pip.app.gateaway.InternetProtocol.InvalidIpAdderssV4FormatException;
import pip.app.gateaway.NetworkConfigurationPresenter;
import pip.app.gateaway.ConfigurationGatewayImpl;
import pip.app.process.NotInvokedPythonProcessYetException;
import pip.main.interactor.IODelivery;
import pip.app.gateaway.ConfigurationGateway;
import pip.main.interactor.IODelivery.CommandIsNotFoundException;
import pip.main.interactor.IODelivery.EmptyCommandFoundException;
import pip.main.interactor.IODelivery.InvalidParameterFoundException;

/**
 *
 * @author User
 */
public class PipNetworkToolJUnitTest {
    
    private IODelivery ui;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        ui = new IODelivery();
    }
    
    @Test
    public void testAvailableCommands() throws IOException, NotInvokedPythonProcessYetException, 
            CommandIsNotFoundException, InvalidIpAdderssV4FormatException, EmptyCommandFoundException, 
            InvalidParameterFoundException{
        ui.identifyCommand("ipaddr");
        ui.identifyCommand("interfaces");
        ui.identifyCommand("network_conf","{FEA67A50-93FD-46BE-AE31-BD927CD1C2F4}");
        ui.identifyCommand("network_conf", "--default-use", "{DE5DD19E-C88D-4B89-B17C-0D188A9C97A8}");
        ui.identifyCommand("list_host","192.168.1.0", "255.255.255.0");
    }
    
    @Ignore
    @Test(expected = EmptyCommandFoundException.class)
    public void givenEmptyCommand_throwEmptyCommandFoundException() throws IOException, 
            NotInvokedPythonProcessYetException, CommandIsNotFoundException, InvalidIpAdderssV4FormatException, 
            EmptyCommandFoundException, InvalidParameterFoundException{
        ui.identifyCommand("");
    }
    
    @Ignore
    @Test
    public void createNetworkInfoPresenterFromJSON() throws IOException, NotInvokedPythonProcessYetException, 
            InternetProtocol.InvalidIpAdderssV4FormatException, CommandIsNotFoundException{
        
        ConfigurationGateway gateway = new ConfigurationGatewayImpl();
        NetworkConfigurationPresenter info = 
                gateway.createNetworkPresenter("{DE5DD19E-C88D-4B89-B17C-0D188A9C97A8}");
        Assert.assertEquals("{DE5DD19E-C88D-4B89-B17C-0D188A9C97A8}", info.interfaceIdentifier());
        Assert.assertEquals("70:5a:0f:29:58:5f", info.physicalAddress());
        Assert.assertEquals("0.0.0.0", info.host.toString());
        Assert.assertEquals("0.0.0.0", info.netmask.toString());
        Assert.assertEquals("0.0.0.0", info.network.toString());
        Assert.assertEquals("0.0.0.0", info.broadcast.toString());
//        Assert.assertEquals("fe80::a440:21d5:4423:fcd4%16", info.hostV6());
//        Assert.assertEquals("ffff:ffff:ffff:ffff::/64", info.netmaskV6());
//        Assert.assertEquals("fe80::ffff:ffff:ffff:ffff%16", info.broadcastV6());
        
        NetworkConfigurationPresenter info2 = 
                gateway.createNetworkPresenter("{A07174A1-7014-4639-B93A-83291AC99679}");
        Assert.assertEquals("{A07174A1-7014-4639-B93A-83291AC99679}", info2.interfaceIdentifier());
        Assert.assertEquals("0a:00:27:00:00:25", info2.physicalAddress());
        Assert.assertEquals("192.168.56.1", info2.host.toString());
        Assert.assertEquals("255.255.255.0", info2.netmask.toString());
        Assert.assertEquals("192.168.56.0", info2.network.toString());
        Assert.assertEquals("192.168.56.255", info2.broadcast.toString());
//        Assert.assertEquals("fe80::98bd:7860:a74a:54a%37", info2.hostV6());
//        Assert.assertEquals("ffff:ffff:ffff:ffff::/64", info2.netmaskV6());
//        Assert.assertEquals("fe80::ffff:ffff:ffff:ffff%37", info2.broadcastV6());
    }
    
    @After
    public void tearDown() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
}
