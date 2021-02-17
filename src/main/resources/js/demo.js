$(document).ready(function() {
    $("#infoButton").prop('disabled', true);

    $("#formDemo").on('submit', function(event) {
        $("#submitDemo").prop('disabled', true);
        $("#submitDemo").html(
            `Try an other demo`
        );
        $("#infoButton").removeAttr('disabled');
    });

    $("#inputDemo").on('change', function() {
        $("#submitDemo").removeAttr('disabled');
        $("#submitDemo").html(
            `Upload demo`
        );
    });
});