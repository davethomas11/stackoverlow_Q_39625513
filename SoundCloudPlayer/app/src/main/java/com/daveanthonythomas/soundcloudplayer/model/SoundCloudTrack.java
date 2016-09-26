package com.daveanthonythomas.soundcloudplayer.model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

/**
 * Created by dave on 2016-09-25.
 * <p>
 * Simple backing class for data retrieved from
 * sound cloud api track endpoint
 */

public class SoundCloudTrack {

    private String kind;

    private long id;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("user_id")
    private String userId;

    private int duration;

    @SerializedName("commentable")
    private boolean canHaveComments;

    private String state;

    @SerializedName("original_content_size")
    private long originalContentSize;

    @SerializedName("last_modified")
    private String lastModified;

    private String sharing;

    @SerializedName("tag_list")
    private String tagList;

    private String permalink;

    @SerializedName("streamable")
    private boolean canBeStreamed;

    @SerializedName("embeddable_by")
    private String embeddableBy;

    @SerializedName("downloadable")
    private boolean canBeDownloaded;

    @SerializedName("purchase_url")
    private String purchaseUrl;

    @SerializedName("label_id")
    private String labelId;

    @SerializedName("purchase_title")
    private String purchaseTitle;

    private String genre;

    private String title;

    private String description;

    @SerializedName("label_name")
    private String labelName;

    private String release;

    @SerializedName("track_type")
    private String trackType;

    @SerializedName("key_signature")
    private String keySignature;

    private String isrc;

    @SerializedName("video_url")
    private String videoUrl;

    private String bpm;

    @SerializedName("release_year")
    private String releaseYear;

    @SerializedName("original_format")
    private String originalFormat;

    private String license;

    private String uri;

    private SoundCloudUser user;

    @SerializedName("permalink_url")
    private String permalinkUrl;

    @SerializedName("artwork_url")
    private String artworkUrl;

    @SerializedName("waveform_url")
    private String waveformUrl;

    @SerializedName("stream_url")
    private String streamUrl;

    @SerializedName("download_count")
    private long downloadCount;

    @SerializedName("playback_count")
    private long playbackCount;

    @SerializedName("favoritings_count")
    private long favoriteCount;

    @SerializedName("comment_count")
    private long commentCount;

    @SerializedName("reposts_count")
    private long rePostCount;

    @SerializedName("attachments_uri")
    private String attachmentsUri;

    private String policy;

    @SerializedName("monetization_model")
    private String monetizationModel;

    public String getKind() {
        return kind;
    }

    public long getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isCanHaveComments() {
        return canHaveComments;
    }

    public String getState() {
        return state;
    }

    public long getOriginalContentSize() {
        return originalContentSize;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getSharing() {
        return sharing;
    }

    public String getTagList() {
        return tagList;
    }

    public String getPermalink() {
        return permalink;
    }

    public boolean isCanBeStreamed() {
        return canBeStreamed;
    }

    public String getEmbeddableBy() {
        return embeddableBy;
    }

    public boolean isCanBeDownloaded() {
        return canBeDownloaded;
    }

    public String getPurchaseUrl() {
        return purchaseUrl;
    }

    public String getLabelId() {
        return labelId;
    }

    public String getPurchaseTitle() {
        return purchaseTitle;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLabelName() {
        return labelName;
    }

    public String getRelease() {
        return release;
    }

    public String getTrackType() {
        return trackType;
    }

    public String getKeySignature() {
        return keySignature;
    }

    public String getIsrc() {
        return isrc;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getBpm() {
        return bpm;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getOriginalFormat() {
        return originalFormat;
    }

    public String getLicense() {
        return license;
    }

    public String getUri() {
        return uri;
    }

    public SoundCloudUser getUser() {
        return user;
    }

    public String getPermalinkUrl() {
        return permalinkUrl;
    }

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public String getWaveformUrl() {
        return waveformUrl;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public long getDownloadCount() {
        return downloadCount;
    }

    public long getPlaybackCount() {
        return playbackCount;
    }

    public long getFavoriteCount() {
        return favoriteCount;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public long getRePostCount() {
        return rePostCount;
    }

    public String getAttachmentsUri() {
        return attachmentsUri;
    }

    public String getPolicy() {
        return policy;
    }

    public String getMonetizationModel() {
        return monetizationModel;
    }
}
