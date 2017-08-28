/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.main.interactor;

import pip.app.gateaway.InternetProtocol;

/**
 *
 * @author User
 */
public class GlobalSetting {
    private static InternetProtocol defaultInUse = null;

    public static InternetProtocol getDefaultInUse() {
        return defaultInUse;
    }

    public static void setDefaultInUse(InternetProtocol defaultInUse) {
        GlobalSetting.defaultInUse = defaultInUse;
    }
}
