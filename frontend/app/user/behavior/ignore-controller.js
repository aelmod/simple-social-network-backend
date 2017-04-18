angular
    .module('ssnApp.user')
    .controller('UserIgnoreController', UserIgnoreController);

function UserIgnoreController($scope, $http, $stateParams, $location, $state, $rootScope, $window) {
    $http
        .put('/api/users/' + $stateParams.userId + '/ignore', {}, {headers: {"X-Token": localStorage.token}})
        .then(
            () => {
                $window.history.back();
            }
        )
}
