/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.main.adapter;

import java.io.IOException;
import pip.app.gateaway.InternetProtocol.InvalidIpAdderssV4FormatException;
import pip.app.process.NotInvokedProcessYetException;
import pip.main.adapter.CommandAdapter.CommandIsNotFoundException;
import pip.main.adapter.CommandAdapter.EmptyCommandFoundException;

/**
 *
 * @author User
 */
public interface CommandListener {

    public void callInteractor(String... commands) throws IOException, NotInvokedProcessYetException, 
            CommandIsNotFoundException, InvalidIpAdderssV4FormatException, EmptyCommandFoundException;
    //public void callInteractorWithArguments(String... commands) throws IOException, NotInvokedProcessYetException, CommandIsNotFoundException, InvalidIpAdderssV4FormatException;
    
}
