angular
    .module('ssnApp.user')
    .controller('UserListController', UserListController);

function UserListController($scope, $http) {
    $http
        .get('/api/users', {headers: {"X-Token": localStorage.token}})
        .then((res) => {
            $scope.users = res.data;
        });
}