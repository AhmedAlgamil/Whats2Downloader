package com.algamil.whatsdownloader.models;

import java.io.File;

/**
 * Created by hsn on 06/11/2018.
 */

public class StatusWhatsapp {
    public Boolean playinig;
    private File file;
    private int viewType;

    public File getFile() {
        return file;
    }

    public StatusWhatsapp setFile(File file) {
        this.file = file;
        return this;
    }

    public int getViewType() {
        return viewType;
    }

    public StatusWhatsapp setViewType(int viewType) {
        this.viewType = viewType;
        return this;
    }
}
