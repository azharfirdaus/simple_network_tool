/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.app.process;

import java.io.IOException;

/**
 *
 * @author User
 */
public interface ProcessInvoker {
    public void exec(String... commands) throws IOException;
    public String[] toMessages() throws NotInvokedProcessYetException;
}
