angular
    .module('ssnApp')
    .controller('MicroblogController', MicroblogController);

function MicroblogController($scope, $http) {
    $scope.init = (microblogId) => {
        $http
            .get('/api/microblog/' + microblogId, {headers: {"X-Token": localStorage.token}})
            .then((res) => {
                $scope.microblog = res.data;
            });
    };
}