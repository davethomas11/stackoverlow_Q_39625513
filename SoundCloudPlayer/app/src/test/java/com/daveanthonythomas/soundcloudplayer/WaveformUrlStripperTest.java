package com.daveanthonythomas.soundcloudplayer;

import com.daveanthonythomas.soundcloudplayer.api.WaveformUrlStripper;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by dave on 2016-09-26.
 */

@RunWith(JUnit4.class)
public class WaveformUrlStripperTest {

    @Test
    public void stripWaveFormUrl() {

        WaveformUrlStripper stripper = new WaveformUrlStripper(null);
        String url = stripper.stripWaveformUrl(response);

        Assert.assertEquals("https://wis.sndcdn.com/sTEoteC5oW3r_m.json", url);
    }

    String response = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><link " +
            "rel=\"dns-prefetch\" href=\"//api-widget.soundcloud.com\"><link rel=\"dns-prefetch\"" +
            " href=\"//sb.scorecardresearch.com\"><link rel=\"dns-prefetch\" href=\"//api" +
            ".soundcloud.com\"><link rel=\"dns-prefetch\" href=\"//visuals.soundcloud.com\"><link" +
            " rel=\"dns-prefetch\" href=\"//ssl.google-analytics.com\"><link rel=\"dns-prefetch\"" +
            " href=\"//va.sndcdn.com\"><link rel=\"dns-prefetch\" href=\"//i1.sndcdn.com\"><link " +
            "rel=\"dns-prefetch\" href=\"//i2.sndcdn.com\"><link rel=\"dns-prefetch\" href=\"//i3" +
            ".sndcdn.com\"><link rel=\"dns-prefetch\" href=\"//i4.sndcdn.com\"><link " +
            "rel=\"dns-prefetch\" href=\"//w1.sndcdn.com\"><link rel=\"dns-prefetch\" " +
            "href=\"//wis.sndcdn.com\"><link rel=\"dns-prefetch\" href=\"//style.sndcdn" +
            ".com\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"><meta " +
            "name=\"viewport\" content=\"width=device-width,minimum-scale=1,maximum-scale=1," +
            "user-scalable=no\"><title>SoundCloud Widget</title><base " +
            "target=\"_blank\"></head><body style=\"margin: 0\"><div id=\"widget\" class=\"widget" +
            " g-background-default g-shadow-inset\" style=\"height: 100%\"></div><link " +
            "rel=\"stylesheet\" href=\"//style.sndcdn" +
            ".com/css/interstate-0ab59479718c8235122cad6b16a66953d725c43a.css\"><link " +
            "rel=\"stylesheet\" href=\"//style.sndcdn" +
            ".com/css/sc-7b3e97754c8c95a1df78186772c64e7fddbb9448.css\"><script>var " +
            "webpackManifest = {\"0\":\"widget-dc77d738.js\",\"1\":\"error-698b3c1d.js\"," +
            "\"2\":\"multi-sounds-8b3fe4e9.js\",\"3\":\"single-sound-8b3fe4e9.js\"," +
            "\"4\":\"visual-multi-sounds-8b3fe4e9.js\",\"5\":\"visual-single-sound-8b3fe4e9" +
            ".js\"};</script><script>!function(e){function t(n){if(r[n])return r[n].exports;var " +
            "a=r[n]={exports:{},id:n,loaded:!1};return e[n].call(a.exports,a,a.exports,t),a" +
            ".loaded=!0,a.exports}var n=window.webpackJsonp;window.webpackJsonp=function(o,p){for" +
            "(var c,i,l=0,s=[];l<o.length;l++)i=o[l],a[i]&&s.push.apply(s,a[i]),a[i]=0;for(c in " +
            "p)e[c]=p[c];for(n&&n(o,p);s.length;)s.shift().call(null,t);if(p[0])return r[0]=0,t" +
            "(0)};var r={},a={6:0};t.e=function(e,n){if(0===a[e])return n.call(null,t);if(void " +
            "0!==a[e])a[e].push(n);else{a[e]=[n];var r=document.getElementsByTagName(\"head\")" +
            "[0],o=document.createElement(\"script\");o.type=\"text/javascript\",o" +
            ".charset=\"utf-8\",o.async=!0,o.src=t.p+window.webpackManifest[e],r.appendChild(o)" +
            "}},t.m=e,t.c=r,t.p=\"\"}([]);;</script><script src=\"https://w.soundcloud" +
            ".com/player/widget-dc77d738.js\"></script><script " +
            "src=\"/player/single-sound-8b3fe4e9.js\"></script><script>webpackJsonp([]," +
            "{0:function(e,t,n){function r(e){return i.every(function(t){return t in e" +
            ".prototype})}function a(e){var t=r(e);return t?function(t){var n,r=o;t" +
            ".lastFetchTime&&(r=t.lastFetchTime,delete t.lastFetchTime),n=new e(t,{parse:!0}),n" +
            ".lastFetchTime=r,n.release()}:e}var c=[{\"id\":59," +
            "\"data\":[{\"artwork_url\":\"https://i1.sndcdn" +
            ".com/artworks-000169258529-6odvu9-large.jpg\",\"commentable\":true," +
            "\"comment_count\":4,\"created_at\":\"2016-06-28T07:17:42Z\",\"description\":\"Buy on" +
            " Beatport:\\nhttp://www.beatport" +
            ".com/release/thinking-fast-and-slow/1802863\\n\\nHidden Vibes on " +
            "Facebook:\\nhttp://www.facebook.com/hiddenvibesmusic\\n\\nFREE.D is dropping his new" +
            " killer EP on Moscow based label Hidden Vibes. Just after the success of the heavy " +
            "tech release «Liberate Your Mind» remixed by Rhadow FREE.D is pushing again live " +
            "instruments in his track. Her Majesty Cello this time recorded live and mixed with a" +
            " variety of deep synth grooves. Take a deep breath before you jump is just about " +
            "«Thinking Fast\\u0026Slow» EP - the atmospheric synth vibe overflows through your " +
            "brain then suddenly interrupted with groove-heavy synth and live cello - all these " +
            "for welcoming you on the dance floors.\\n\\nBrilliant remix support from Mario Aureo" +
            " who redesigned the whole composition delivering absolutely amazing new vision of " +
            "the track and then St.Petersburg talent Ki.Mi shoots with a very detailed work full " +
            "of mystic samples\\u0026fx - a really state of art remix with great swung melodies " +
            "inside.\",\"downloadable\":false,\"download_count\":0,\"download_url\":null," +
            "\"duration\":388607,\"full_duration\":388607,\"embeddable_by\":\"all\"," +
            "\"genre\":\"Deep House\",\"has_downloads_left\":true,\"id\":271188615," +
            "\"kind\":\"track\",\"label_name\":\"Hidden Vibes\"," +
            "\"last_modified\":\"2016-07-11T14:44:11Z\",\"license\":\"all-rights-reserved\"," +
            "\"likes_count\":163,\"permalink\":\"freed-thinking-fast-and-slow-original-mix\"," +
            "\"permalink_url\":\"https://soundcloud" +
            ".com/hiddenvibesmusic/freed-thinking-fast-and-slow-original-mix\"," +
            "\"playback_count\":4177,\"public\":true," +
            "\"publisher_metadata\":{\"urn\":\"soundcloud:tracks:271188615\"}," +
            "\"purchase_title\":null,\"purchase_url\":\"https://www.beatport" +
            ".com/release/thinking-fast-and-slow/1802863\"," +
            "\"release_date\":\"2016-07-11T00:00:00Z\",\"reposts_count\":31," +
            "\"secret_token\":null,\"sharing\":\"public\",\"state\":\"finished\"," +
            "\"streamable\":true,\"tag_list\":\"\\\"Tech House\\\" House Techno Electronica Cello" +
            " FREE.D \\\"Hidden Vibes\\\"\",\"title\":\"FREE.D — Thinking Fast \\u0026 Slow " +
            "(Original Mix)\",\"uri\":\"https://api.soundcloud.com/tracks/271188615\"," +
            "\"urn\":\"soundcloud:tracks:271188615\",\"user_id\":169212468,\"visuals\":null," +
            "\"waveform_url\":\"https://wis.sndcdn.com/sTEoteC5oW3r_m.json\"," +
            "\"domain_lockings\":[],\"monetization_model\":\"NOT_APPLICABLE\"," +
            "\"policy\":\"ALLOW\",\"user\":{\"avatar_url\":\"https://i1.sndcdn" +
            ".com/avatars-000166880799-kuehvp-large.jpg\",\"first_name\":\"\",\"full_name\":\"\"," +
            "\"id\":169212468,\"kind\":\"user\",\"last_modified\":\"2016-09-12T13:41:10Z\"," +
            "\"last_name\":\"\",\"permalink\":\"hiddenvibesmusic\"," +
            "\"permalink_url\":\"https://soundcloud.com/hiddenvibesmusic\",\"uri\":\"https://api" +
            ".soundcloud.com/users/169212468\",\"urn\":\"soundcloud:users:169212468\"," +
            "\"username\":\"Hidden Vibes\",\"verified\":false,\"city\":\"Moscow\"," +
            "\"country_code\":null}}]}],o=Date.now(),i=[\"resource_type\",\"get\",\"set\"," +
            "\"addSubmodel\",\"release\"];c.forEach(function(e){try{var t=a(n(e.id));e.data" +
            ".forEach(function(e){t(e)})}catch(r){}})}});</script></body></html>";
}
