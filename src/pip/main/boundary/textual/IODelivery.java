/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.main.boundary.textual;

import pip.main.adapter.CommandListener;
import pip.main.adapter.CommandAdapter;
import java.io.IOException;
import pip.app.gateaway.InternetProtocol.InvalidIpAdderssV4FormatException;
import pip.app.process.NotInvokedProcessYetException;
import pip.main.adapter.CommandAdapter.CommandIsNotFoundException;
import pip.main.adapter.CommandAdapter.EmptyCommandFoundException;

/**
 *
 * @author User
 */
public class IODelivery implements ResponseAcceptor{
    private final CommandListener listener;
    public IODelivery() {
        listener = new CommandAdapter(this);
    }

    public void relayCommand(String... commands) throws IOException, NotInvokedProcessYetException, 
            CommandIsNotFoundException, InvalidIpAdderssV4FormatException, EmptyCommandFoundException{
        listener.callInteractor(commands);
    }

    @Override
    public void print(Object feedback) {
        System.out.println(feedback.toString());
    }

    @Override
    public void print(Object[] feedbacks) {
        for(Object obj: feedbacks)
            System.out.println(obj.toString());
    }
    
}
