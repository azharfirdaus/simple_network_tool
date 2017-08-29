/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.main.interactor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
    private final List<String> parameters = Arrays.asList("--default-use");

    public IODelivery() {
        
    }
    
    public IODelivery(CommandListener listener){
        this.listener = listener;
    }
    
    private void throwExceptionIfCommandArrayIsEmpty(String... commands) throws EmptyCommandFoundException{
        if(commands.length < 1)
            throw new EmptyCommandFoundException();
    }
    
    private void throwExceptionIfCommandIsEmpty(String... commands) throws EmptyCommandFoundException{
        if(commands[0].trim().length() < 1)
            throw new EmptyCommandFoundException();
    }

    public void identifyCommand(String... commands) throws IOException, NotInvokedPythonProcessYetException, 
            CommandIsNotFoundException, InvalidIpAdderssV4FormatException, EmptyCommandFoundException, InvalidParameterFoundException{
        throwExceptionIfCommandArrayIsEmpty(commands);
        throwExceptionIfCommandIsEmpty(commands);
        
        if(commands.length == 1) 
            callInteractor(commands[0]);
        else if(commands.length > 1 && parameters.contains(commands[1]))
            callInteractor(commands[0], commands[1], copyArrayFrom(commands, 2, commands.length));
        else if(commands.length > 1)
            callInteractor(commands[0], copyArrayFrom(commands, 1, commands.length));
        else 
            throw new CommandIsNotFoundException();
    }
    
    private String[] copyArrayFrom(String arrays[], int begin, int end){
        String newArray[] = new String[end-begin];
        for(int i = 0; i < newArray.length; i++){
            newArray[i] = arrays[begin++];
        }
        return newArray;
    }
    
    private void callInteractor(String singleCommand) throws IOException, NotInvokedPythonProcessYetException, 
            CommandIsNotFoundException, InvalidIpAdderssV4FormatException {
        switch (singleCommand) {
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
     
    private void callInteractor(String command, String args[]) throws IOException, 
            NotInvokedPythonProcessYetException, CommandIsNotFoundException, InvalidIpAdderssV4FormatException {
        switch(command){
            case "network_conf":
                NetworkConfigurationPresenter presenter = interactor.getNetworkInfoPresenter(args[0]);
                
                fireOnDisplayNetworkInterfaceIdentifiers(presenter);
                print(presenter);
                break;
            case "list_host":
                InternetProtocol hosts[] = interactor.getAllHosts(args[0], args[1]);
                
                fireOnDisplayAllHosts(hosts);
                printArray(hosts);
                break;
            default:
                throw new CommandIsNotFoundException();
        }
    }
    
    private void fireOnDisplayNetworkInterfaceIdentifiers(NetworkConfigurationPresenter presenter){
        if(listener != null)
            listener.onDetailNetworkInterfaceIdentifier(presenter);
    }
    
    private void fireOnDisplayAllHosts(InternetProtocol hosts[]){
        if(listener != null)
            listener.onDisplayAllHosts(hosts);
    }
    
    private void callInteractor(String command, String parameter, String args[]) throws IOException, 
            NotInvokedPythonProcessYetException, InvalidIpAdderssV4FormatException, InvalidParameterFoundException, CommandIsNotFoundException{
        switch(command){
            case "network_conf":
                if(parameter.equals("--default-use")){
                    NetworkConfigurationPresenter presenter = interactor.getNetworkInfoPresenter(args[0]);
                    GlobalSetting.setDefaultInUse(presenter);
                    
                    fireOnSetDefaultInterface(presenter);
                    print(String.format("use %s as default interface", args[0]));
                    print(presenter);
                } else {
                    throw new InvalidParameterFoundException();
                }
                break;
            default:
                throw new CommandIsNotFoundException();
        }
    }

    private void fireOnSetDefaultInterface(NetworkConfigurationPresenter presenter) {
        if(listener != null){
            listener.onSetDefaultInterface(presenter);
        }
    }
    
    
    private void printArray(Object objects[]){
        for(Object obj: objects)
            System.out.println(obj.toString());
    }
    
    private void print(Object obj){
        System.out.println(obj.toString());
    } 
    
    public static class CommandIsNotFoundException extends Exception{}
    public static class InvalidParameterFoundException extends Exception{}
    public static class EmptyCommandFoundException extends Exception{}
    public static class InvalidArgumentsFoundException extends Exception{}
}
