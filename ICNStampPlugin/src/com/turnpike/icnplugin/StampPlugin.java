package com.turnpike.icnplugin;

import com.ibm.ecm.extension.Plugin;

public class StampPlugin extends Plugin {
    @Override
    public String getId() { return "StampPlugin"; }
    @Override
    public String getName() { return "Stamp Plugin"; }
    @Override
    public String getVersion() { return "1.0"; }
    @Override
    public String getDojoModule() { return "stampPlugin"; }
}