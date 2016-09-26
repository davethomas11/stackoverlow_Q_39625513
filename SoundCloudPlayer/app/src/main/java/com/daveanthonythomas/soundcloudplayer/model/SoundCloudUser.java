package com.daveanthonythomas.soundcloudplayer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dave on 2016-09-25.
 */

public class SoundCloudUser {

    private long id;
    private String kind;
    private String permalink;
    private String username;
    private String uri;

    @SerializedName("last_modified")
    private String lastModified;

    @SerializedName("permalink_url")
    private String permalinkUrl;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public long getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getUsername() {
        return username;
    }

    public String getUri() {
        return uri;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getPermalinkUrl() {
        return permalinkUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
