angular
    .module('ssnApp.user')
    .controller('CurrentUserController', CurrentUserController);

function CurrentUserController($scope, $http) {
    $http
        .get('/api/users/currentUser', {headers: {"X-Token": localStorage.token}})
        .then((res) => {
            $scope.currentUser = res.data;
        });
}