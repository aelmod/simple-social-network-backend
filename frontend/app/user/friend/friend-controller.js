angular
    .module('ssnApp.user')
    .controller('UserFriendController', UserFriendController);

function UserFriendController($scope, $http, $stateParams) {
    $http
        .get('/api/users/' + $stateParams.userId + '/friends', {headers: {"X-Token": localStorage.token}})
        .then((res) => {
            $scope.friends = res.data;
        });
}