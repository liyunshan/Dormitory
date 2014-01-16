var IMAGE_STATU_EMPTY = 0;
var IMAGE_STATU_SAVE = 1;
var IMAGE_STATU_DELETE = 2;
var uploadImageBind = function(url, itemID, index, element) {
    $(element).after('<input type="file" name="image_' + index + '" id="image_upload_' + index + '"/>');
    var inputField = $(element).next();
    inputField.change(function () {
        var options = {
            url: url + "?itemID=" + itemID,
            secureuri: false,
            fileElementId: 'image_upload_' + index,
            dataType: 'xml',
            beforeSend:function()
            {
            //alert(1);
            //$("#loading").show();
            },
            complete:function()
            {

            //$("#loading").hide();
            }, 
            success: function (data, status)
            {
                var status = data.getElementsByTagName('status')[0].firstChild.nodeValue;
                var imageURL = data.getElementsByTagName('imageURL')[0].firstChild.nodeValue;
                var errorMessage = data.getElementsByTagName('errorMessage')[0].firstChild.nodeValue;
                
                if (status == 'SUCCESS') {
                    var date = new Date();
                    $(element).attr('src',imageURL + "? "+date.toLocaleString());
                    $("#image_upload_status_" + index).val(IMAGE_STATU_SAVE);
                } else {
                    alert(errorMessage);
                }
                //uploadImageBind(inputId);
            },
            error: function (data, status, e)
            {
                alert(e);
            }
        };
        $.ajaxFileUpload(options);
    });
}