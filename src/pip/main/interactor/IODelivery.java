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
import pip.app.process.NotInvokedPythonProcessYetException;
import pip.main.boundary.graphical.CommandListener;

/**
 *
 * @author User
 */
public class IODelivery {
    
    private final Interactor interactor = new Interactor();
    private CommandListener listener;

    public IODelivery() {
    }
    
    public IODelivery(CommandListener listener){
        this.listener = listener;
    }

    public void relayCommand(String... commands) throws IOException, NotInvokedPythonProcessYetException, 
            CommandIsNotFoundException, InvalidIpAdderssV4FormatException, EmptyCommandFoundException{
        throwExceptionIfCommandArrayIsEmpty(commands);
        throwExceptionIfCommandIsEmpty(commands);
        if(commands.length == 1)
            callInteractor(commands[0]);
        else if(commands.length > 1)
            callInteractorWithArguments(commands);
    }
    
    private void callInteractor(String command) throws IOException, NotInvokedPythonProcessYetException, 
            CommandIsNotFoundException, InvalidIpAdderssV4FormatException {
        switch (command) {
            case "ipaddr":
                String output[] = interactor.getHostname();
                printArray(output);
                break;
            case "interfaces":
                String identifiers[] = interactor.getNetworkInterfaceIdentifier();
                printArray(identifiers);
                fireOnDisplayNetworkInterfaceIdentifier(identifiers);
                break;
            default:
                throw new CommandIsNotFoundException();
        }
    }

    private void fireOnDisplayNetworkInterfaceIdentifier(String[] identifiers) {
        if(listener != null)
            listener.onDisplayNetworkInterfaceIdentifier(identifiers);
    }
    
    
    private void callInteractorWithArguments(String... commands) throws IOException, 
            NotInvokedPythonProcessYetException, CommandIsNotFoundException, InvalidIpAdderssV4FormatException {
        switch(commands[0]){
            case "network_interface":
                NetworkConfigurationPresenter presenter = null;
                if(commands[1].equals("--set-default")){
                    interactor.getNetworkInfoPresenter(commands[2]);
                } else {
                    
                }
                fireOnDisplayNetworkInterfaceIdentifier(presenter);
                print(presenter);
                break;
            case "list_hosts":
                InternetProtocol hosts[] = interactor.getAllHosts(commands[1], commands[2]);
                fireOnDisplayAllHosts(hosts);
                printArray(hosts);
                break;
            default:
                throw new CommandIsNotFoundException();
        }
    }
    
    private void fireOnDisplayNetworkInterfaceIdentifier(NetworkConfigurationPresenter presenter){
        if(listener != null)
            listener.onDetailNetworkInterfaceIdentifier(presenter);
    }
    
    private void fireOnDisplayAllHosts(InternetProtocol hosts[]){
        if(listener != null)
            listener.onDisplayAllHosts(hosts);
    }
    
    private void printArray(Object objects[]){
        for(Object obj: objects)
            System.out.println(obj.toString());
    }
    
    private void print(Object obj){
        System.out.println(obj.toString());
    } 
    
    private void throwExceptionIfCommandArrayIsEmpty(String... commands) throws EmptyCommandFoundException{
        if(commands.length < 1)
            throw new EmptyCommandFoundException();
        
    }
    
    private void throwExceptionIfCommandIsEmpty(String... commands) throws EmptyCommandFoundException{
        if(commands[0].trim().length() < 1)
            throw new EmptyCommandFoundException();
    }
    
    public static class CommandIsNotFoundException extends Exception{}
    public static class EmptyCommandFoundException extends Exception{}
}
