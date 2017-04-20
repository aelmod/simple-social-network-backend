angular
    .module('ssnApp.user')
    .controller('UserListController', UserListController);

function UserListController($scope, $http) {
    $http
        .get('/api/users')
        .then((res) => {
            $scope.users = res.data;
        });
}