package com.troncodroide.heroadventurehelper.utils;

import android.content.res.AssetManager;

import com.troncodroide.heroadventurehelper.APP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FIleUtil {

    public static void writeFileOnCache(String sFileName, String sBody) {
        try {
            File root = new File(APP.getContext().getCacheDir(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromCache(String sFileName) {
        String aBuffer = "";
        try {
            File root = new File(APP.getContext().getCacheDir(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileInputStream fIn;
            fIn = new FileInputStream(gpxfile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(
                    fIn));
            String aDataRow = "";
            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow;
            }
            myReader.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return aBuffer;
    }

    public static String readAsset(AssetManager mgr, String path) {
        String contents = "";
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = mgr.open(path);
            reader = new BufferedReader(new InputStreamReader(is));
            contents = reader.readLine();
            String line = null;
            while ((line = reader.readLine()) != null) {
                contents += '\n' + line;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
        return contents;
    }
}
