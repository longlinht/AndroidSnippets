package io.github.longlinht.library.pickle.impl;

import io.github.longlinht.library.pickle.PickleException;
import io.github.longlinht.library.pickle.Storage;
import io.github.longlinht.library.utils.ArrayUtils;
import io.github.longlinht.library.utils.EncryptUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import io.github.longlinht.library.network.utils.IOUtils;

/**
 * 采用文件存储
 *
 * Created by Tao He on 18-4-27.
 * hetaoof@gmail.com
 *
 */
public class FileStorage implements Storage {

    private static final String FILE_PREFIX = "PICKLE_";
    private final File cacheDir;

    public FileStorage(File installDir) {

        if (installDir.exists() && !installDir.isDirectory()) {
            throw new IllegalArgumentException("cacheDir 已经存在，但不是文件夹");
        }

        this.cacheDir = installDir;
    }

    @Override
    public boolean put(String key, String value) {

        makeSureInstallDirExists();

        final String file = trans2FileName(key);

        Writer writer = null;
        try {

            writer = new BufferedWriter(new FileWriter(new File(cacheDir, file)));
            writer.write(value);
            writer.flush();

        } catch (IOException e) {

            throw new PickleException("Storage#put 发生异常", e);

        } finally {
            IOUtils.closeQuietly(writer);
        }

        return true;
    }

    private static final char[] temBuf = new char[1024];

    @SuppressWarnings("unchecked")
    @Override
    public String get(String key) {

        if (!cacheDir.exists()) {
            return null;
        }

        final String fileName = trans2FileName(key);

        final File file = new File(cacheDir, fileName);
        if (!file.exists()) {
            return null;
        }

        Reader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            final StringWriter stringWriter = new StringWriter(1024);

            int size;
            while ((size = reader.read(temBuf)) != -1) {
                stringWriter.write(temBuf, 0, size);
            }

            return stringWriter.toString();

        } catch (IOException e) {

            throw new PickleException("读取文件过程中发生异常 key = " + key, e);

        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    @Override
    public boolean delete(String key) {

        if (!cacheDir.exists()) {
            return false;
        }

        final File file = new File(cacheDir, trans2FileName(key));

        return file.exists() && file.delete();
    }

    @Override
    public boolean deleteAll() {
        if (!cacheDir.exists()) {
            return false;
        }

        final File[] files = cacheDir.listFiles(PICKLE_FILTER);
        if (files == null) {
            return false;
        }

        boolean success = true;
        for (File file : files) {
            final boolean deleted = file.delete();
            
            if (!deleted) {
                success = false;
            }
        }

        return success;
    }

    @Override
    public long count() {

        if (!cacheDir.exists()) {
            return 0;
        }

        final String[] fileList = cacheDir.list(PICKLE_FILTER);
        return fileList == null ? 0 : fileList.length;
    }

    @Override
    public boolean contains(String key) {
        if (!cacheDir.exists()) return false;

        final String[] fileList = cacheDir.list(PICKLE_FILTER);
        return ArrayUtils.indexOf(fileList, trans2FileName(key)) != -1;
    }

    private void makeSureInstallDirExists() {
        if (!cacheDir.exists()) {
            if (!cacheDir.mkdir()) {
                // 创建单层文件夹失败，尝试创建parent文件夹
                if (!cacheDir.mkdirs()) {
                    throw new PickleException("创建文件夹失败 path: " + cacheDir.getAbsolutePath());
                }
            }
        }
    }

    private static final FilenameFilter PICKLE_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String filename) {
            return filename.startsWith(FILE_PREFIX);
        }
    };

    private static String trans2FileName(String key) {
        return FILE_PREFIX + EncryptUtils.encryptMD5ToString(key);
    }
}
