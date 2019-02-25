package com.swift.sandxposed.installer;

import android.content.Context;

import com.swift.sandxposed.installer.sandxposed.SandXposed;
import com.trendmicro.tmas.Module;

public class ModuleEntry implements Module {
    //entry of XposedManager(Self) module
    @Override
    public void moduleInit(String hostPackageName, String attachedProcess, Object hostContext) throws Throwable {
        Context context = (Context) hostContext;
        SandXposed.injectXposedModule(context, hostPackageName, attachedProcess);
    }
}
