angular
    .module('ssnApp.user')
    .controller('CurrentUserController', CurrentUserController);

function CurrentUserController($scope, $http) {
    $http
        .get('/api/users/currentUser')
        .then((res) => {
            $scope.currentUser = res.data;
        });
}