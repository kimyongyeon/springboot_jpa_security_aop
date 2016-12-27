/**
 * ajax 처리 공통 함수
 * @type {{fnAjaxCallback: Comm.fnAjaxCallback}}
 */
var Comm = {
    fnAjaxCallback: function(url, data, callback, type) {
        type = type || 'GET';
        type = type.toUpperCase();

        var deferred = $.Deferred();

        var promise = $.ajax({
            url: url,
            type: type, // default : get
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            cache: false,
            data: (type === "GET") ? jQuery.param(data) : JSON.stringify(data),
            success: function(d) {
                deferred.resolve(d);
            }
        });

        promise.done(function(data) {
            callback(data);
        });

        promise.fail(function(data){
            callback(data);
        });

        promise.always(function(data){
        });

        promise.progress(function(data){
        });
    }
}