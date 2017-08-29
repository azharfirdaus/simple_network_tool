/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pip.main.interactor;

import pip.app.gateaway.NetworkConfigurationPresenter;

/**
 *
 * @author User
 */
public class GlobalSetting {
    private static NetworkConfigurationPresenter defaultInUse = null;

    public static NetworkConfigurationPresenter getDefaultInUse() {
        return defaultInUse;
    }

    public static void setDefaultInUse(NetworkConfigurationPresenter defaultInUse) {
        GlobalSetting.defaultInUse = defaultInUse;
    }
}
