$(document).ready(function() {
    var progress_bar1 = $("#progress1");
    var progress_bar2 = $("#progress2");
    var parsingDone = true;

    $("#infoButton").prop('disabled', true);

    $("#formDemo").on('submit', function(event) {
        $("#submitDemo").prop('disabled', true);
        $("#submitDemo").html(
            `Try an other demo`
        );
    });

    $("#inputDemo").on('change', function() {
        $("#submitDemo").removeAttr('disabled');
        $("#submitDemo").html(
            `Upload demo`
        );
    });

    $('#formDemo').ajaxForm({
        beforeSend: function() {
            $("#infoButton").prop('disabled', true);
            progress_bar1.width(0);
            progress_bar2.width(0);
            parsingDone = false;
            getStatus();
        },
        uploadProgress: function(event, position, total, percentComplete) {
            progress_bar1.width(percentComplete + "%");
        },
        complete: function(xhr) {
            progress_bar1.width(100 + "%");
        }
    });

    function getStatus() {
        $.ajax({
            url: '/status',
            success: function(data) {
                if (data >= 100) {
                    parsingDone = true;

                    $("#infoButton").removeAttr('disabled');
                    $("#infoButton").css("display", "block");
                }
                progress_bar2.width(data + "%");
            },
            complete: function() {
                if (!parsingDone) {
                    setTimeout(getStatus, 1000);
                }
            }
        });
    };

});