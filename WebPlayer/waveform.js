/**
 * Created by dave on 16-09-26.
 */
function WaveForm(waveformPngUrl) {

    var self = this;

    var heightRatio;
    var waveformUrl = waveformPngUrl.replace(/png$/, "json");
    waveformUrl = waveformUrl.replace(/:\/\/w1/,"://wis");

    var canvas = $('#SoundCloudPlayer .track_waveform');
    var heightPX = canvas.get(0).height;
    var data;
    var progress = 0;

    this.load = function () {

        $.get(waveformUrl, function (response) {

            heightRatio = heightPX / response.height;
            data = response;
            canvas.css({ height: "" + heightPX + "px" });
            self.draw();
        });

    };

    this.draw = function () {

        var ctx = canvas.get(0).getContext("2d");
        ctx.clearRect(0, 0, canvas.get(0).width, canvas.get(0).height);

        var x = 0;


        var lineWidth = data.samples.length / canvas.width();
        var progressPoint =  canvas.get(0).width - ((1 - progress) * canvas.get(0).width);

        ctx.beginPath();
        ctx.lineWidth = lineWidth / 2;
        ctx.strokeStyle = "#ff8000";
        var progressFound = false;

        for (var i = 0; i < data.samples.length; i++) {

            if (x > progressPoint && !progressFound) {
                ctx.stroke();
                ctx.closePath();
                ctx.beginPath();
                ctx.strokeStyle = "#000";
                progressFound = true;
            }

            var ratio = (data.samples[i] * heightRatio) / heightPX;
            var drawTo = heightPX - ratio * heightPX;

            ctx.moveTo(x, heightPX);
            ctx.lineTo(x, drawTo);

            x += lineWidth;
        }

        ctx.stroke();
        ctx.closePath();

    };

    this.setProgress = function (newProgress) {
        progress = newProgress;
        self.draw();
    }
}