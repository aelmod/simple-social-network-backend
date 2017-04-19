angular.module('ssnApp')

    .controller('MyCtrl', ['$scope', 'Upload', '$timeout', function ($scope, Upload, $timeout) {
        $scope.upload = function (original, dataUrl, name) {
            Upload.upload({
                url: '/api/pictures',
                method: 'POST',
                data: {
                    picture: Upload.dataUrltoBlob(dataUrl, name)
                },
            }).then(function (response) {
                $timeout(function () {
                    $scope.result = response.data;
                });
            }, function (response) {
                if (response.status > 0) $scope.errorMsg = response.status
                    + ': ' + response.data;
            }, function (evt) {
                $scope.progress = parseInt(100.0 * evt.loaded / evt.total);
            });
        }
    }]);