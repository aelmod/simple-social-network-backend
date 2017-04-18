angular
    .module('ssnApp.user')
    .controller('UserIgnoreController', UserIgnoreController);

function UserIgnoreController($scope, $http, $stateParams) {
    $http
        .put('/api/users/' + $stateParams.userId + '/ignore', {headers: {"X-Token": localStorage.token}})
}
