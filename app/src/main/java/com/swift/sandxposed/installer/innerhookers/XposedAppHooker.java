package com.swift.sandxposed.installer.innerhookers;

import android.os.Build;

import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.ThisObject;

import de.robv.android.xposed.installer.XposedApp;
import de.robv.android.xposed.installer.util.FrameworkZips;
import de.robv.android.xposed.installer.util.InstallZipUtil;

@HookClass(XposedApp.class)
public class XposedAppHooker {

    @HookMethod("getInstalledXposedVersion")
    public static int hookXposedVersion() {
        return 99;
    }

    @HookMethod("getActiveXposedVersion")
    public static int hookXposedActiveVersion() {
        return 99;
    }

    @HookMethod("reloadXposedProp")
    public static void hookReloadXposedProp(@ThisObject XposedApp thiz) {
        thiz.mXposedProp = new InstallZipUtil.XposedProp();
        thiz.mXposedProp.mVersionInt = hookXposedVersion();
        thiz.mXposedProp.mArch = FrameworkZips.getArch();
        thiz.mXposedProp.mVersion = hookXposedVersion() + "";
        thiz.mXposedProp.mMinSdk = Build.VERSION_CODES.LOLLIPOP;
        thiz.mXposedProp.mMaxSdk = 28;
    }

}
