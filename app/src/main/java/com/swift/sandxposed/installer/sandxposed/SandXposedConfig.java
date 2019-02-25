package com.swift.sandxposed.installer.sandxposed;

import com.trend.lazyinject.annotation.Component;
import com.trend.lazyinject.annotation.Provide;

import java.util.List;

import de.robv.android.xposed.installer.util.ModuleUtil;

@Component
public interface SandXposedConfig {

    //providers
    @Provide
    boolean xposedEnabled();
    @Provide
    List<ModuleUtil.InstalledModule> modules();

    //ipc calls

}
