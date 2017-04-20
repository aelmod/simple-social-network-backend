angular
    .module('ssnApp.user')
    .controller('UserIgnoreController', UserIgnoreController);

function UserIgnoreController($http, $stateParams, $window) {
    $http
        .put('/api/users/' + $stateParams.userId + '/ignore', {})
        .then(
            () => {
                $window.history.back();
            }
        )
}
