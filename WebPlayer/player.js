var player, mTrack, audio, seekBarInterval, waveForm;
var updatingSeekBar = false;
var clientId = 'cUa40O3Jg3Emvp6Tv4U6ymYYO50NUGpJ';

$(function () {

    SC.initialize({
        client_id: clientId
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

    var trackUrl = track.stream_url + "?client_id=" + clientId;

    audio = new Audio(trackUrl);
    console.log(trackUrl);

    if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
        
        // Sorry can not auto play on mobile =_(
        // http://stackoverflow.com/questions/26066062/autoplay-html5-audio-player-on-mobile-browsers
        $(player).find(".track_pause").hide();
        $(player).find(".track_play").fadeIn();
    } else {
        play();
    }
    
}

function play() {

    $(player).find(".track_play").hide();
    $(player).find(".track_pause").fadeIn();

    audio.play();

    seekBarInterval = setInterval(updateSeekBar, 500);
}

function pause() {

    $(player).find(".track_pause").hide();
    $(player).find(".track_play").fadeIn();

    audio.pause();

    clearInterval(seekBarInterval);
}

function seek(event) {

    if (event.originalEvent) {
        audio.currentTime = $(player).find(".track_seek_bar").slider("value") / 1000;
    }
    waveForm.setProgress((audio.currentTime * 1000) / mTrack.duration); 
}

function updateSeekBar() {

    var time = (audio.currentTime * 1000);
    $(player).find(".track_seek_bar").slider("value", time);
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