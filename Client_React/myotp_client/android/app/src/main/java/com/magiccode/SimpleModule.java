package main.java.com.magiccode;

import android.Manifest;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

public class SimpleModule extends ReactContextBaseJavaModule{

    public SimpleModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return "SIMPLE MODULE";
    }

    @ReactMethod
    public void sayMayName(String name, Callback cb) {
        try {
            cb.invoke(null, "Ola "+name);
        } catch (Exception e) {
            cb.invoke(e, null);
        }
    }

}