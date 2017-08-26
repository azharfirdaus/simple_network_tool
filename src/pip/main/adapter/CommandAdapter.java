/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.main.adapter;

import java.io.IOException;
import pip.app.gateaway.InternetProtocol;
import pip.app.gateaway.InternetProtocol.InvalidIpAdderssV4FormatException;
import pip.app.gateaway.NetworkConfigurationPresenter;
import pip.main.interactor.Interactor;
import pip.app.process.NotInvokedProcessYetException;
import pip.main.boundary.textual.ResponseAcceptor;
import pip.main.boundary.graphical.ChangeAcceptor;

/**
 *
 * @author User
 */
public class CommandAdapter implements CommandListener{
    
    private final Interactor interactor = new Interactor();
    private ResponseAcceptor responser;
    private ChangeAcceptor changer;
    
    public CommandAdapter(ResponseAcceptor responser){
        this.responser = responser;
    }

    public CommandAdapter(ChangeAcceptor changer) {
        this.changer = changer;
    }
    
    private void throwExceptionIfCommandArrayIsEmpty(String... commands) throws EmptyCommandFoundException{
        if(commands.length < 1)
            throw new EmptyCommandFoundException();
        
    }
    
    private void throwExceptionIfCommandIsEmpty(String... commands) throws EmptyCommandFoundException{
        if(commands[0].trim().length() < 1)
            throw new EmptyCommandFoundException();
    }
    
    @Override
    public void callInteractor(String... commands) throws IOException, NotInvokedProcessYetException, 
            CommandIsNotFoundException, InvalidIpAdderssV4FormatException, EmptyCommandFoundException {
        throwExceptionIfCommandArrayIsEmpty(commands);
        throwExceptionIfCommandIsEmpty(commands);
        
        if(commands.length == 1)
            callInteractorWithoutArgument(commands[0]);
        else if(commands.length > 1)
            callInteractorWithArguments(commands);
    }
    
    private void fireResponserOnGetHostName(String hostname) throws NotInvokedProcessYetException, IOException {
        if(responser != null)
            responser.print(hostname);
    }

    private void fireChangerOnGetNetworkInterfaceIdentifier(String identifiers[]) throws IOException, NotInvokedProcessYetException {
        if(changer != null)
            changer.onDisplayNetworkInterfaceIdentifier(identifiers);
    }

    private void fireResponserOnGetNetworkInterfaceIdentifier(String identifiers[]) throws IOException, NotInvokedProcessYetException {
        if(responser != null)
            responser.print(identifiers);
    }
    
    private void callInteractorWithoutArgument(String command) throws IOException, NotInvokedProcessYetException, 
            CommandIsNotFoundException, InvalidIpAdderssV4FormatException {
        switch (command) {
            case "ipaddr":
                fireResponserOnGetHostName(interactor.getHostname()[0]);
                break;
            case "interfaces":
                String identifiers[] = interactor.getNetworkInterfaceIdentifier();
                fireResponserOnGetNetworkInterfaceIdentifier(identifiers);
                fireChangerOnGetNetworkInterfaceIdentifier(identifiers);
                break;
            default:
                throw new CommandIsNotFoundException();
        }
    }

    private void fireReponserOnGetNetworkInfoPresenter(NetworkConfigurationPresenter presenter) throws IOException, 
            InvalidIpAdderssV4FormatException, NotInvokedProcessYetException {
        if(responser != null)
            responser.print(presenter);
    }
    
    private void fireChangerOnGetNetworkPresenter(NetworkConfigurationPresenter presenter) {
        if(changer != null){
            changer.onDetailNetworkInterfaceIdentifier(presenter);
        }
    }

    private void fireReponserOnGetAllHosts(InternetProtocol hosts[]) throws InvalidIpAdderssV4FormatException, 
            NotInvokedProcessYetException, IOException {
        if(responser != null)
            responser.print(hosts);
    }
    
    private void fireChangerOnGetAllHosts(InternetProtocol[] hosts) {
        if(changer != null){
            changer.onDisplayAllHosts(hosts);
        }
    }

    private void callInteractorWithArguments(String... commands) throws IOException, 
            NotInvokedProcessYetException, CommandIsNotFoundException, InvalidIpAdderssV4FormatException {
        switch(commands[0]){
            case "network_card_conf":
                NetworkConfigurationPresenter presenter = interactor.getNetworkInfoPresenter(commands[1]);
                fireReponserOnGetNetworkInfoPresenter(presenter);
                fireChangerOnGetNetworkPresenter(presenter);
                break;
            case "list_hosts":
                InternetProtocol hosts[] = interactor.getAllHosts(commands[1], commands[2]);
                fireReponserOnGetAllHosts(hosts);
                fireChangerOnGetAllHosts(hosts);
                break;
            default:
                throw new CommandIsNotFoundException();
        }
    }
    
    public static class CommandIsNotFoundException extends Exception{}
    public static class EmptyCommandFoundException extends Exception{}
    
}
