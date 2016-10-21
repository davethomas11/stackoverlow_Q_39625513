function WaveForm(waveformPngUrl) {

	$('.track_waveform').append("<img src=\""+waveformPngUrl+"\" />");
	$('.track_waveform').append("<div class='wvprogress'></div>")

	this.setProgress = function (newProgress) {

		var width = $('.track_waveform').width();
		var progressPoint =  width - ((1 - newProgress) * width);
		$('.wvprogress').css({ width: "" + progressPoint + "px" });

	}
}