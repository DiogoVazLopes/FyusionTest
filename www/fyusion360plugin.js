/** 
    Innovagency - Team Mobile
    Pedro Remedios
*/
function Fyusion360() {
}

exports.initFyuse = function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "Fyusion360Plugin", "InitFyuse");
};
exports.startCaptureSession = function (successCallback, errorCallback, options) {
    cordova.exec(successCallback, errorCallback, "Fyusion360Plugin", "startSession", [options]);
};

exports.showFyuse = function (successCallback, errorCallback, fyuseID) {
    cordova.exec(successCallback, errorCallback, "Fyusion360Plugin", "showFyuse", [fyuseID]);
}

exports.getFyuseThumbnail = function(successCallback, errorCallback, fyuseID) {
    cordova.exec(successCallback, errorCallback, "Fyusion360Plugin", "getFyuseThumbnail", [fyuseID]);
}

exports.getDetailPhotos = function(successCallback, errorCallback, sessionID, resolution) {
    cordova.exec(successCallback, errorCallback, "Fyusion360Plugin", "getDetailPhotos", [sessionID, resolution]);
}
