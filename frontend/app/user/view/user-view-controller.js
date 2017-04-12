angular
    .module('ssnApp.user')
    .controller('UserViewController', UserViewController);


function UserViewController($scope, $http, $stateParams) {
    $http
        .get('/api/users/' + $stateParams.userId, {headers: {"X-Token": localStorage.token}})
        .then((res) => {
            $scope.user = res.data;
        });
}