package com.trendmicro.tmas;

public interface Module {
    void moduleInit(String hostPackageName, String attachedProcess, Object hostContext) throws Throwable;
}
