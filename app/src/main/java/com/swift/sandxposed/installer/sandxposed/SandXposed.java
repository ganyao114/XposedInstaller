package com.swift.sandxposed.installer.sandxposed;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.swift.sandhook.SandHook;
import com.swift.sandhook.wrapper.HookErrorException;
import com.swift.sandhook.xposedcompat.XposedCompat;
import com.swift.sandxposed.installer.innerhookers.XposedAppHooker;

import java.io.File;
import java.util.List;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.installer.XposedApp;

import static com.swift.sandxposed.installer.sandxposed.SandHookHelper.MD5;
import static com.swift.sandxposed.installer.sandxposed.SandHookHelper.initHookPolicy;


public class SandXposed {

    public static void initForXposedInstaller(Context context) {
        SandHook.disableVMInline();
        XposedApp.getXposedProp();
        try {
            SandHook.addHookClass(XposedAppHooker.class);
        } catch (HookErrorException e) {
            e.printStackTrace();
        }
    }

    public static void injectXposedModule(Context context, String packageName, String processName) {

//        List<InstalledAppInfo> appInfos = VirtualCore.get().getInstalledApps(0);
//        ClassLoader classLoader = context.getClassLoader();
//
//        for (InstalledAppInfo module:appInfos) {
//            if (TextUtils.equals(packageName, module.packageName)) {
//                Log.d("injectXposedModule", "injectSelf : " + processName);
//            }
//            XposedCompat.loadModule(module.apkPath, module.getOdexFile().getParent(), module.libPath, XposedBridge.class.getClassLoader());
//        }

        XposedCompat.context = context;
        XposedCompat.packageName = packageName;
        XposedCompat.processName = processName;
        XposedCompat.cacheDir = new File(context.getCacheDir(), MD5(processName));
        XposedCompat.classLoader = XposedCompat.getSandHookXposedClassLoader(null, XposedBridge.class.getClassLoader());
        XposedCompat.isFirstApplication = true;

        initHookPolicy();
        EnvironmentSetup.init(context, packageName, processName);

        try {
            XposedCompat.callXposedModuleInit();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }



}
