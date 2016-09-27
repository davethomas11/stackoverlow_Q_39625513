var player, mTrack, media, seekBarInterval, waveForm;
var updatingSeekBar = false;

$(function () {

    SC.initialize({
        client_id: 'cUa40O3Jg3Emvp6Tv4U6ymYYO50NUGpJ'
    });

    player = document.getElementById("SoundCloudPlayer");

    checkQueryURLForTrackId();
    loadTrackEnteredInInput();

    $("form button").button();
});

function loadTrackEnteredInInput() {

    loadTrack(getTrackId());
}

function loadTrack(trackId) {

    SC.get('/tracks/' + trackId).then(function (track) {

        // Inspect for info on track you want:
        console.log(track);
        mTrack = track;

        renderTrack(track);
        streamTrack(track);

        waveForm = new WaveForm(track.waveform_url);
        waveForm.load();

    }, function () {

        alert("Sorry no track found for track id: "+ trackId)
    });
}

function renderTrack(track) {

    $(player).find(".track_artist").text(track.user.permalink);
    $(player).find(".track_title").text(track.title);
    $(player).find(".track_artwork").attr('src', track.artwork_url);
    $(player).find(".track_seek_bar").slider(
        {
            orientation: "horizontal",
            range: "min",
            max: track.duration,
            value: 0,
            change: seek
        });

}

function streamTrack(track) {

    SC.stream('/tracks/' + track.id).then(function (mediaPlayer) {
        media = mediaPlayer;

        console.log(media);

        play();
    });
}

function play() {
    if (!media) {
        return;
    }

    $(player).find(".track_play").hide();
    $(player).find(".track_pause").fadeIn();

    media.play();

    seekBarInterval = setInterval(updateSeekBar, 500);
}

function pause() {
    if (!media) {
        return;
    }

    $(player).find(".track_pause").hide();
    $(player).find(".track_play").fadeIn();
    media.pause();

    clearInterval(seekBarInterval);
}

function seek() {
    if (!media) {
        return;
    }

    if (!updatingSeekBar) {
        media.seek($(player).find(".track_seek_bar").slider("value"));
    }
}

function updateSeekBar() {
    if (!media) {
        return;
    }

    waveForm.setProgress(media.currentTime() / mTrack.duration);

    updatingSeekBar = true;
    $(player).find(".track_seek_bar").slider("value", media.currentTime());
    updatingSeekBar = false;
}

/**
 * Loads a different track id based on
 * url query
 */
function checkQueryURLForTrackId() {
    var query = getUrlVars();
    if (query.trackId) {
        $('[name=trackId]').val(query.trackId);
    }
}

//http://stackoverflow.com/questions/4656843/jquery-get-querystring-from-url
// Read a page's GET URL variables and return them as an associative array.
function getUrlVars()
{
    var vars = {}, hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars[hash[0]] = hash[1];
    }
    return vars;
}

function getTrackId() {
    return trackId = $('[name=trackId]').val();
}