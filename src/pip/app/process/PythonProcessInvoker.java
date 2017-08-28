/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.app.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class PythonProcessInvoker implements ProcessInvoker{
    
    private boolean invoked = false;
    private String[] messages;

    private void setAsInvoked() {
        invoked = true;
    }
    
    private void setAsNotInvoked() {
        invoked = false;
    }

    public boolean isInvoked() {
        return invoked;
    }
    
    private void setMessages(String messages[]){
        this.messages = messages;
    }

    @Override
    public void exec(String... commands) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(commands);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        
        String[] bufferedMessages = buildUpMessagesFrom(process.getInputStream());
        setMessages(bufferedMessages);
        
        setAsInvoked();
    }
    
    @Override
    public String[] toMessages() throws NotInvokedPythonProcessYetException {
        if(!isInvoked()){
            throw new NotInvokedPythonProcessYetException();
        }
        setAsNotInvoked();
        return messages;
    }
    
    private String[] buildUpMessagesFrom(InputStream inputStream) throws IOException{
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(inputStream));
        List<String> builtUpMessages = new ArrayList<>();
        String stream = "";
        while ((stream = stdInput.readLine()) != null) {
            builtUpMessages.add(stream);
        }
        String array[] = new String[builtUpMessages.size()];
        for(int i = 0; i < array.length; i ++){
            array[i] = builtUpMessages.get(i);
        }
        return array;
    }
}
